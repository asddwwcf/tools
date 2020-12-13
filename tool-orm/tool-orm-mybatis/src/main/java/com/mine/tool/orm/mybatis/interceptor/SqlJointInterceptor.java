package com.mine.tool.orm.mybatis.interceptor;

import com.bruce.tool.common.util.ClassUtils;
import com.mine.tool.orm.mybatis.core.annotation.ReturnExecutableSQL;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.type.TypeHandlerRegistry;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.text.DateFormat;
import java.util.*;

/**
 * 功能 :
 * 1.借用Mybatis的xml组装可执行的SQL.
 * 2.将可执行的远程SQL,作为方法结果返回.
 * @author : Bruce(刘正航) 08:44 2019-09-05
 */
@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})})
public class SqlJointInterceptor implements Interceptor {

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
    }

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        StatementHandler statementHandler = (StatementHandler) invocation.getTarget();

        MetaObject metaObject = MetaObject.forObject(statementHandler, SystemMetaObject.DEFAULT_OBJECT_FACTORY, SystemMetaObject.DEFAULT_OBJECT_WRAPPER_FACTORY,new DefaultReflectorFactory());
        MappedStatement mappedStatement = (MappedStatement) metaObject.getValue("delegate.mappedStatement");

        String methodId = mappedStatement.getId();
        String methodName = StringUtils.reverse(StringUtils.reverse(methodId).replaceFirst("\\..*",""));
        String className = methodId.replaceAll("."+methodName,"");
        Class<?> aClass = Class.forName(className);
        Method method = ClassUtils.getMethod(aClass, methodName);
        if(Objects.isNull(method)){
            return invocation.proceed();
        }
        if(Objects.isNull(method.getAnnotation(ReturnExecutableSQL.class))){
            return invocation.proceed();
        }

        //获取sql语句
        String remoteSql = fillDataToSQL(statementHandler, mappedStatement);
        String executeSql = createNewSql(remoteSql);

        // 设置新的SQL
        resetSql2Invocation(statementHandler, executeSql);

        return invocation.proceed();
    }

    /**填充参数到SQL中**/
    private String fillDataToSQL(StatementHandler statementHandler, MappedStatement mappedStatement) {

        BoundSql boundSql = statementHandler.getBoundSql();

        Configuration configuration = mappedStatement.getConfiguration();

        Object parameterObject = boundSql.getParameterObject();

        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();

        String sql = boundSql.getSql().replaceAll("[\\s]+", " ");

        if(CollectionUtils.isEmpty(parameterMappings) || null == parameterObject){
            return sql;
        }

        TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();

        if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
            sql = sql.replaceFirst("\\?", getParameterValue(parameterObject));
        } else {
            MetaObject metaObject = configuration.newMetaObject(parameterObject);
            for (ParameterMapping parameterMapping : parameterMappings) {
                String propertyName = parameterMapping.getProperty();
                if (metaObject.hasGetter(propertyName)) {
                    Object obj = metaObject.getValue(propertyName);
                    sql = sql.replaceFirst("\\?", getParameterValue(obj));
                } else if (boundSql.hasAdditionalParameter(propertyName)) {
                    Object obj = boundSql.getAdditionalParameter(propertyName);
                    sql = sql.replaceFirst("\\?", getParameterValue(obj));
                }
            }
        }
        return sql;
    }

    private String getParameterValue(Object obj) {
        String value;
        if (obj instanceof String) {
            value = "'" + obj.toString() + "'";
        } else if (obj instanceof Date) {
            DateFormat formatter = DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT, Locale.CHINA);
            value = "'" + formatter.format(obj) + "'";
        } else {
            if (obj != null) {
                value = obj.toString();
            } else {
                value = "";
            }

        }
        return value;
    }

    /**创建返回远程SQL的本地SQL**/
    public String createNewSql(String sql){
        sql = "\""+sql.replaceAll("\"","\\\\\"")+"\"";
        return "select " + sql + " as remoteSql;";
    }

    /**
     * 将新的sql,设置到invocation对象中,让旧的SQL失效
     */
    private void resetSql2Invocation(StatementHandler statementHandler, String sql) throws IllegalAccessException, NoSuchFieldException {
        BoundSql boundSql = statementHandler.getBoundSql();
        //通过反射修改sql语句
        Field field = boundSql.getClass().getDeclaredField("sql");
        field.setAccessible(true);
        field.set(boundSql, sql);
    }

}

