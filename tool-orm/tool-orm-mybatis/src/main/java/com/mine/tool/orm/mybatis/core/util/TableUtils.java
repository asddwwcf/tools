package com.mine.tool.orm.mybatis.core.util;

import com.bruce.tool.common.util.ClassUtils;
import com.google.common.collect.Lists;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 功能 :
 * 表对象属性操作工具类
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class TableUtils {

    private static final Map<Class, DomainInfo> cache = new HashMap<>();

    /**获取主键注解**/
    public static Field getPrimary(Class clazz) {
        return cache.computeIfAbsent(clazz, DomainInfo::new).getPrimary();
    }

    /**获取主键注解**/
    public static String getPrimaryKey(Class clazz) {
        return cache.computeIfAbsent(clazz, DomainInfo::new).getPrimaryKey();
    }

    /**
     * 获取字节码对象, 以Bean Class名称转下划线为表名
     */
    public static String getTableName(Class clazz) {
        return cache.computeIfAbsent(clazz, DomainInfo::new).getTableName();
    }

    /**
     * 获取字段映射
     */
    public static String getColumnMapping(Class clazz) {
        return cache.computeIfAbsent(clazz, DomainInfo::new).getColumnMapping();
    }

    /**获取类的所有字段**/
    public static List<Field> getFields(Class<?> beanClass) {
        return cache.computeIfAbsent(beanClass, DomainInfo::new).getAllFields();
    }

    /**获取字段**/
    public static Field getField(Class clazz, String fieldName) {
        return cache.computeIfAbsent(clazz, DomainInfo::new).getField(fieldName);
    }

    /**获取字段**/
    public static Field getFieldByColumn(Class clazz, String columnName) {
        return cache.computeIfAbsent(clazz, DomainInfo::new).getFieldByColumn(columnName);
    }

    /**获取有值的字段**/
    public static List<Field> getFieldsWithValue(Object domain){
        Class<?> clazz = domain.getClass();
        List<Field> fields = cache.computeIfAbsent(clazz, DomainInfo::new).getAllFields();
        List<Field> results = Lists.newArrayList();
        for (Field field : fields) {
            Object value = ClassUtils.getValue(domain,field.getName());
            if(Objects.nonNull(value) ){
                results.add(field);
            }
        }
        return results;
    }
}
