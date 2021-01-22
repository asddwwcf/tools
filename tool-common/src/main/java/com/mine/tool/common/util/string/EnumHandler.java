package com.mine.tool.common.util.string;

import com.mine.tool.common.util.ClassUtils;
import com.google.common.collect.Lists;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 功能 :
 *
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class EnumHandler {

    /**单个枚举转json**/
    public static String toJson(Object object){
        return JsonUtils.objToStr(toMap(object));
    }

    /**枚举类型-转json字符串**/
    public static String toJson(Object[] enums){
        return JsonUtils.objToStr(toList(enums));
    }

    /**单个枚举转map**/
    public static Map toMap(Object object){

        if( Objects.isNull(object) ){ return null; }

        Class<?> clazz = object.getClass();
        if( !clazz.isEnum() ){ return null; }

        // 获取自定义属性
        List<Field> selfFields = fetchSelfFields(clazz);
        // 获取自定义属性值
        return fetchFieldValues(selfFields, object);
    }

    /**枚举类型-转json字符串**/
    public static List<Object> toList(Object[] enums){

        if(Objects.isNull(enums) ){ return Lists.newArrayList();}

        if(enums.length == 0){ return Lists.newArrayList(); }

        Class<?> clazz = enums[0].getClass();
        if( !clazz.isEnum() ){ return Lists.newArrayList(); }

        // 获取自定义属性
        List<Field> selfFields = fetchSelfFields(clazz);

        if( selfFields.size() == 0 ){
            return Lists.newArrayList(enums);
        }

        List<Object> jsons = Lists.newArrayList();

        for (Object item : enums) {
            if( selfFields.size() == 1 ){
                jsons.add(fetchValues(selfFields, item));
            }
            jsons.add(fetchFieldValues(selfFields, item));
        }

        return jsons;
    }

    /**枚举类型-转json字符串**/
    public static List<Object> toList(Object[] enums,String fieldName){

        if(Objects.isNull(enums) ){ return Lists.newArrayList();}

        if(enums.length == 0){ return Lists.newArrayList(); }

        Class<?> clazz = enums[0].getClass();
        if( !clazz.isEnum() ){ return Lists.newArrayList(); }

        // 获取自定义属性
        List<Field> selfFields = fetchSelfFields(clazz);

        if( selfFields.size() == 0 ){
            return Lists.newArrayList(enums);
        }

        List<Object> jsons = Lists.newArrayList();

        for (Object item : enums) {
            if( selfFields.size() == 1 ){
                jsons.add(fetchValues(selfFields, item));
            }
            jsons.add(fetchFieldValues(selfFields, item, fieldName));
        }

        return jsons;
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


    /**获取枚举的自定义属性**/
    private static List<Field> fetchSelfFields(Class<?> clazz) {
        List<Field> fields = ClassUtils.getAllFields(clazz);
        List<Field> selfFields = Lists.newArrayList();
        for (Field field : fields) {

            int modifiers = field.getModifiers();

            // 静态的属性不要
            if(Modifier.isStatic(modifiers)){ continue; }

            // final的属性不要
            if(Modifier.isFinal(modifiers)){ continue; }

            selfFields.add(field);
        }
        return selfFields;
    }

    /**获取自定义属性值**/
    private static Object fetchFieldValues(List<Field> selfFields, Object item, String fieldName) {
        if( StringUtils.isBlank(fieldName) ){
            return null;
        }
        for (Field field : selfFields) {
            String name = field.getName();
            if( name.equals(fieldName) ){
                return ClassUtils.getValue(item,name);
            }
        }
        return null;
    }

    /**获取自定义属性值**/
    private static MapHandler fetchFieldValues(List<Field> selfFields, Object item) {
        MapHandler handler = MapHandler.build();
        for (Field field : selfFields) {
            String name = field.getName();
            Object value = ClassUtils.getValue(item,name);
            handler.add(name, value);
        }
        return handler;
    }

    /**获取自定义属性值**/
    private static Object fetchValues(List<Field> selfFields, Object item) {
        Field field = selfFields.get(0);
        String name = field.getName();
        return ClassUtils.getValue(item,name);
    }
    
}
