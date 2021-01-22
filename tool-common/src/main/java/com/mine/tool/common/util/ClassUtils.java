package com.mine.tool.common.util;

import com.mine.tool.common.exception.ExceptionUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;

import java.beans.PropertyDescriptor;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.util.*;

/**
 * 功能 :
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ClassUtils {

    private static final String OBJECT_NAME = "java.lang.Object";

    /**基本数据类型**/
    private static final List<Class<?>> BASE_CLASS = Lists.newArrayList(
            Integer.class, int.class,
            Long.class, long.class,
            Short.class, short.class,
            Byte.class, short.class,
            Float.class, float.class,
            Double.class, double.class,
            BigDecimal.class,
            String.class,
            Date.class,
            Integer[].class, int[].class,
            Long[].class, long[].class,
            Short[].class, short[].class,
            Byte[].class, short[].class,
            Float[].class, float[].class,
            Double[].class, double[].class,
            BigDecimal[].class,
            String[].class,
            Map.class, Collection.class
    );

    private static final List<Class<?>> NUMBER_CLASS = Lists.newArrayList(
            Integer.class, int.class,
            Long.class, long.class,
            Short.class, short.class,
            Byte.class, short.class,
            Float.class, float.class,
            Double.class, double.class,
            BigDecimal.class
    );

    private static final List<Class<?>> DECIMAL_CLASS = Lists.newArrayList(
            Float.class, float.class,
            Double.class, double.class,
            BigDecimal.class
    );

    private static final List<Class<?>> INTEGER_CLASS = Lists.newArrayList(
            Integer.class, int.class,
            Long.class, long.class,
            Short.class, short.class,
            Byte.class, short.class
    );

    private static final Map<String,Class<?>> KEY_CLASS = Maps.newHashMap();

    static{
        for (Class<?> clazz : BASE_CLASS) {
            KEY_CLASS.put(clazz.getSimpleName(),clazz);
            KEY_CLASS.put(clazz.getSimpleName().toLowerCase(),clazz);
        }
    }

    /**是否是Java基础数据类型**/
    public static boolean isBaseDataType(Class<?> clazz){
        return BASE_CLASS.contains(clazz);
    }

    /**是否是Java基础数据类型**/
    public static boolean isExtendDataType(Object data){
        for (Class<?> clazz : BASE_CLASS) {
            if( clazz.isInstance(data) ){
                return true;
            }
        }
        return false;
    }

    public static boolean isNumberType(Class<?> clazz){
        return NUMBER_CLASS.contains(clazz);
    }

    /**是否是Java基础数据类型**/
    public static boolean isExtendNumberType(Object data){
        for (Class<?> clazz : NUMBER_CLASS) {
            if( clazz.isInstance(data) ){
                return true;
            }
        }
        return false;
    }

    public static boolean isDecimalType(Class<?> clazz){
        return DECIMAL_CLASS.contains(clazz);
    }

    /**是否是Java基础数据类型**/
    public static boolean isExtendDecimalType(Object data){
        for (Class<?> clazz : DECIMAL_CLASS) {
            if( clazz.isInstance(data) ){
                return true;
            }
        }
        return false;
    }

    public static boolean isIntegerType(Class<?> clazz){
        return INTEGER_CLASS.contains(clazz);
    }

    /**是否是Java基础数据类型**/
    public static boolean isExtendIntegerType(Object data){
        for (Class<?> clazz : INTEGER_CLASS) {
            if( clazz.isInstance(data) ){
                return true;
            }
        }
        return false;
    }

    public static boolean isStringType(Class<?> clazz){
        return String.class.equals(clazz);
    }

    /**是否是Java基础数据类型**/
    public static boolean isExtendStringType(Object data){
        if( data instanceof String ){
            return true;
        }
        return false;
    }

    /**获取表单对象属性(包含父类属性)**/
    public static List<Field> getAllFields(Class<?> clazz) {
        // todo 这里增加本地缓存,提高速度
        List<Field> fields = Lists.newArrayList(clazz.getDeclaredFields());

        Class<?> superClass = clazz.getSuperclass();
        while( null != superClass ){
            if( OBJECT_NAME.equalsIgnoreCase(superClass.getName()) ){
                break;
            }
            getFields(superClass, fields);
            superClass = superClass.getSuperclass();
        }

        fields.removeIf(field -> Modifier.isStatic(field.getModifiers()));
        return fields;
    }

    public static Method getMethod(Class<?> clazz,String methodName){
        Method[] methods = clazz.getDeclaredMethods();
        for (Method method : methods) {
            if(method.getName().equals(methodName)){
                return method;
            }
        }
        return null;
    }

    /**根据注解获取方法**/
    public static List<Method> getMethodsByAnnotation(Class<?> clazz, Class<? extends Annotation> annotation){
        Method[] methods = clazz.getDeclaredMethods();
        List<Method> annotationMethods = Lists.newArrayList();
        for (Method method : methods) {
            if(Objects.nonNull(method.getAnnotation(annotation))){
                annotationMethods.add(method);
            }
        }
        return annotationMethods;
    }

    /**获取单元格值类型**/
    public static Class<?> getFieldType(Class<?> clazz, String fieldName) {
        Field field = getField(clazz,fieldName);
        if( Objects.nonNull(field) ){
            return field.getType();
        }
        return null;
    }

    public static Field getField(Class<?> clazz, String fieldName){
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            if( field.getName().equals(fieldName) ){
                return field;
            }
        }
        return null;
    }

    /**是否包含指定字段属性**/
    public static boolean hasField(Class<?> clazz,String fieldName){
        Field field = getField(clazz,fieldName);
        return Objects.nonNull(field);
    }

    /**是否包含至少一个字段属性**/
    public static boolean hasField(Object data){
        Field[] fields = data.getClass().getDeclaredFields();
        return fields.length > 0;
    }

    /**设置类属性值**/
    public static boolean setValue(Object entity, String fieldName, Object fieldValue){
        try {
            PropertyDescriptor propertyDescriptor = PropertyUtils.getPropertyDescriptor(entity, fieldName);
            Class<?> fieldType = propertyDescriptor.getPropertyType();
            fieldValue = transfer(fieldType,fieldValue);
            PropertyUtils.setSimpleProperty(entity, fieldName, fieldValue);
            return true;
        } catch (Exception e) {
            ExceptionUtils.printStackTrace(e);
        }
        return false;
    }

    /**根据key获取value**/
    public static <E,V> V getValue(E entity,String fieldName) {
        Object value = null;
        if( StringUtils.isBlank(fieldName) ){ return null; }
        try {
            if( entity instanceof Map ){
                value = ((Map)entity).get(fieldName);
            }else{
                String methodName = getGetterMethodName(fieldName);
                if( StringUtils.isBlank(methodName) ){ return null; }
                Method method = entity.getClass().getMethod(methodName);
                value = method.invoke(entity);
            }
        } catch (Exception e) {
            ExceptionUtils.printStackTrace(e);
        }
        return (V)value;
    }

    /**
     * 根据key获取value
     * @deprecated {@link ClassUtils#getValue(Object, String)}
     */
    @Deprecated
    public static <E,V> V getValue(String fieldName, E entity) {
        return getValue(entity,fieldName);
    }

    /**根据类名,获取类**/
    public static Class<?> getBaseClass(String simpleClassName){
        return KEY_CLASS.get(simpleClassName);
    }

    /*
     ****************************************私有方法区*******************************************
                   _               _                           _    _                 _
                  (_)             | |                         | |  | |               | |
      _ __   _ __  _ __   __ __ _ | |_  ___   _ __ ___    ___ | |_ | |__    ___    __| |
     | '_ \ | '__|| |\ \ / // _` || __|/ _ \ | '_ ` _ \  / _ \| __|| '_ \  / _ \  / _` |
     | |_) || |   | | \ V /| (_| || |_|  __/ | | | | | ||  __/| |_ | | | || (_) || (_| |
     | .__/ |_|   |_|  \_/  \__,_| \__|\___| |_| |_| |_| \___| \__||_| |_| \___/  \__,_|
     | |
     |_|
     ****************************************私有方法区*******************************************
     */


    /**获取类字段**/
    private static void getFields(Class<?> clazz, List<Field> fields) {

        if( null == clazz ){ return; }

        if( OBJECT_NAME.equalsIgnoreCase(clazz.getSimpleName()) ){
            return;
        }

        fields.addAll(Arrays.asList(clazz.getDeclaredFields()));
    }

    /**获取getter方法**/
    private static String getGetterMethodName(String fieldName){
        if(StringUtils.isBlank(fieldName) ){
            return null;
        }
        char[] chars = fieldName.toCharArray();
        if (chars[0] >= 'a' && chars[0] <= 'z') {
            chars[0] = (char)(chars[0] - 32);
        }
        return "get"+new String(chars);
    }

    /**根据值类型,转换对应的字段值**/
    private static Object transfer(Class<?> fieldType, Object value) {
        if( Objects.isNull(value) ){
            return null;
        }
        if (fieldType.equals(Integer.class)) {
            return (Integer.valueOf(value.toString()));
        } else if (fieldType.equals(Long.class)) {
            return (Long.valueOf(value.toString()));
        } else if (fieldType.equals(Float.class)) {
            return Float.valueOf(value.toString());
        } else if (fieldType.equals(Double.class)) {
            return Double.valueOf(value.toString());
        } else if (fieldType.equals(Byte.class)) {
            return Byte.valueOf(value.toString());
        } else if (fieldType.equals(Boolean.class)) {
            return Boolean.valueOf(value.toString());
        } else if (fieldType.equals(String.class)) {
            return value;
        } else if (fieldType.equals(BigDecimal.class)) {
            return new BigDecimal(value.toString());
        } else if (fieldType.equals(Date.class)) {
            return DateUtils.format(DateUtils.create(((Date)value).getTime()),DateUtils.Parttern.FORMAT_YYMMDDHMS_MID);
        }
        return value;
    }
}
