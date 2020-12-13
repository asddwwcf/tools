package com.mine.tool.orm.mybatis.core.provider;

import com.bruce.tool.common.exception.BaseRuntimeException;
import com.bruce.tool.common.util.string.StringUtils;
import com.mine.tool.orm.mybatis.core.constant.SQLCode;
import com.mine.tool.orm.mybatis.core.exception.OrmNullException;
import com.mine.tool.orm.mybatis.core.query.Example;
import com.mine.tool.orm.mybatis.core.util.TableUtils;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;
import java.util.Objects;

/**
 * 功能 :
 * mybatis基础查询接口
 * 1.findByDomainId(domain)
 * 2.findById(Class,id)
 * 3.findByColumn(Class,key,value)
 * 4.findsByColumn(Class,key,value)
 * 5.findByColumns(domain) //domain中有的值,都算查询条件,可以有多个字段,可以只有一个字段
 * 6.findsByColumns(domain) //domain中有的值,都算查询条件,可以有多个字段,可以只有一个字段
 *
 * 7.findsByCriteria(Class,CriteriaQuery)
 * 8.findsByCriteria(column,table,CriteriaQuery)
 * 9.findsByCriteria(column,table,CriteriaQuery)
 * 10.findsByCriteria(sql,CriteriaQuery)
 * 11.findsByCriteria(sql)
 *
 * @author : Bruce(刘正航) 17:01 2019-01-20
 */
@SuppressWarnings("all")
public class FindProvider {

    private static final String ID_PLACEHOLDER = " = #{id}";
    public static final String BEGIN_PLACEHOLDER = " = #{";
    public static final String END_PLACEHOLDER = "}";

    private Class<?> getParamClass(Map<String, Object> params) {
        return (Class<?>) params.get("clazz");
    }

    /**
     * 根据ID查询一条记录
     */
    public String findById(Map<String, Object> params) {
        Class<?> clazz = getParamClass(params);
        if( Objects.isNull(clazz) ){
            throw new OrmNullException();
        }
        String tableName = TableUtils.getTableName(clazz);
        String columnMapping = TableUtils.getColumnMapping(clazz);
        String primary = TableUtils.getPrimaryKey(clazz);
        if(StringUtils.isBlank(primary)){
            throw new BaseRuntimeException(SQLCode.FE001.getCode(),SQLCode.FE001.getMessage());
        }
        SQL sql = new SQL();
        sql.SELECT(columnMapping);
        sql.FROM(tableName);
        sql.AND().WHERE(primary + ID_PLACEHOLDER);
        return sql.toString();
    }

    public String findByExample(Example example){
        return example.getCondition() + " limit 1";
    }

    public String findsByExample(Example example){
        return example.getCondition();
    }

}
