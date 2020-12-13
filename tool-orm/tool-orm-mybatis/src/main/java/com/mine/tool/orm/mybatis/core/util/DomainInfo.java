package com.mine.tool.orm.mybatis.core.util;

import com.bruce.tool.common.exception.BaseRuntimeException;
import com.mine.tool.orm.mybatis.core.annotation.Id;
import com.mine.tool.orm.mybatis.core.constant.SQLCode;
import com.google.common.collect.Lists;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 功能 :
 * 表对象属性缓存
 * @author : Bruce(刘正航) 6:02 PM 2018/11/14
 */
public class DomainInfo {

    /**
     * 字节码对象
     */
    private Class<?> clazz;

    /**
     * 表名, 由Class的名称得来, 根据倍全的命名规范, 驼峰转下划线
     */
    private String tableName;

    /**
     * 字段映射, id AS id, user_name AS userName
     * 根据Bean的字段, 驼峰转下划线而来
     */
    private String columnMapping;

    /**
     * Class的字段集合
     */
    private List<Field> fields = Lists.newArrayList();

    /**
     * 字段映射
     */
    private Map<String, Field> fieldMap = new HashMap<>();

    /**
     * 字段映射
     */
    private Map<String, Field> columnMap = new HashMap<>();

    /**
     * 主键字段
     */
    private Field primary;

    /**
     * 主键字段
     */
    private String primaryKey;

    /**
     * 构造方法
     *
     * @param clazz
     */
    public DomainInfo(Class clazz) {
        this.clazz = clazz;
        init();
    }

    /**
     * 获取表名
     */
    public String getTableName() {
        return this.tableName;
    }

    /**
     * 获取字段映射
     */
    public String getColumnMapping() {
        return this.columnMapping;
    }

    /**
     * 获取主键名称
     */
    public Field getPrimary() {
        if (null == primary) {
            throw new BaseRuntimeException(SQLCode.FE005.getCode(),"没有主键@Id标识");
        }
        return this.primary;
    }

    /**
     * 获取主键名称
     */
    public String getPrimaryKey() {
        if (null == primaryKey) {
            throw new BaseRuntimeException(SQLCode.FE005.getCode(),"没有主键@Id标识");
        }
        return this.primaryKey;
    }

    /**
     * 获取字段
     */
    public Field getField(String fieldName) {
        Field field = this.fieldMap.get(fieldName);
        if (null == field) {
            throw new BaseRuntimeException(SQLCode.FE005.getCode(),fieldName + "不存在");
        }
        return field;
    }

    /**
     * 获取字段
     */
    public Field getFieldByColumn(String columnName) {
        Field field = this.columnMap.get(columnName);
        if (null == field) {
            throw new BaseRuntimeException(SQLCode.FE005.getCode(),columnName + "不存在");
        }
        return field;
    }

    /**获取所有字段**/
    public List<Field> getAllFields(){
        return Lists.newArrayList(this.fields);
    }

    /**
     * 初始化, 生成字段映射,
     */
    private void init() {
        this.tableName = ColumnUtils.fetchTableName(this.clazz);
        final String alias = " as ";
        final String comma = ", ";
        StringBuilder sb = new StringBuilder();
        Field[] declaredFields = clazz.getDeclaredFields();
        for (Field field : declaredFields) {
            if( ColumnUtils.needIgnore(field) ){ continue; }
            if( Modifier.isStatic(field.getModifiers()) ) { continue; }
            this.fields.add(field);
            String columnName = ColumnUtils.fetchColumnName(field);
            /**设置表主键:部分表可能没有主键**/
            setPrimaryKey(field, columnName);
            this.fieldMap.put(field.getName(), field);
            this.columnMap.put(columnName, field);
            String fieldName = ColumnUtils.fetchFieldName(field.getName());
            sb.append(columnName).append(alias).append(fieldName);
            sb.append(comma);
        }
        this.columnMapping = sb.substring(0, sb.lastIndexOf(comma));
    }

    /**设置表主键**/
    private void setPrimaryKey(Field field, String columnName) {
        Id id = field.getAnnotation(Id.class);
        if( null == id ){
            return;
        }
        if (null != this.primaryKey) {
            throw new BaseRuntimeException(SQLCode.FE005.getCode(),"Class存在2个或2个以上@Id标注主键");
        }
        this.primary = field;
        this.primaryKey = columnName;
    }

}
