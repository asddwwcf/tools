package com.mine.tool.orm.mybatis.core.provider;

import com.mine.tool.common.exception.BaseRuntimeException;
import com.mine.tool.orm.mybatis.core.constant.SQLCode;
import com.mine.tool.orm.mybatis.core.util.TableUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

/**
 * 功能 :
 *
 */
@SuppressWarnings("all")
public class DeleteProvider {

    private static final String ID_PLACEHOLDER = " = #{id}";
    public static final String BEGIN_PLACEHOLDER = " = #{";
    public static final String END_PLACEHOLDER = "}";

    private Class getParamClass(Map<String, Object> params) {
        return (Class) params.get("clazz");
    }

    public String deleteById(Map<String, Object> params) {
        Class clazz = getParamClass(params);
        String tableName = TableUtils.getTableName(clazz);
        String id = TableUtils.getPrimaryKey(clazz);
        if(StringUtils.isBlank(id)){
            throw new BaseRuntimeException(SQLCode.FE001.getCode(),SQLCode.FE001.getMessage());
        }
        SQL sql = new SQL();
        sql.DELETE_FROM(tableName);
        sql.WHERE(id + ID_PLACEHOLDER);
        return sql.toString();
    }

    public String markDeleteById(Map<String, Object> params) {
        Class clazz = getParamClass(params);
        String tableName = TableUtils.getTableName(clazz);
        String id = TableUtils.getPrimaryKey(clazz);
        if(StringUtils.isBlank(id)){
            throw new BaseRuntimeException(SQLCode.FE001.getCode(),SQLCode.FE001.getMessage());
        }
        SQL sql = new SQL();
        sql.UPDATE(tableName);
        sql.SET("is_delete = "+ System.currentTimeMillis());
        sql.WHERE(id + ID_PLACEHOLDER);
        return sql.toString();
    }

    public String markDeleteByIdDiy(Map<String, Object> params) {
        Class clazz = getParamClass(params);
        String mark = (String) params.get("mark");
        if(StringUtils.isBlank(mark) ){
            throw new BaseRuntimeException(SQLCode.FE004.getCode(),"标记删除字段不能为空!");
        }
        String tableName = TableUtils.getTableName(clazz);
        String id = TableUtils.getPrimaryKey(clazz);
        if(StringUtils.isBlank(id)){
            throw new BaseRuntimeException(SQLCode.FE001.getCode(),SQLCode.FE001.getMessage());
        }
        SQL sql = new SQL();
        sql.UPDATE(tableName);
        sql.SET("is_delete = "+ System.currentTimeMillis());
        sql.WHERE(id + ID_PLACEHOLDER);
        return sql.toString();
    }

}
