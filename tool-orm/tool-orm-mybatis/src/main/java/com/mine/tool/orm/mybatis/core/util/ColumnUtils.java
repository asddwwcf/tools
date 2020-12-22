package com.mine.tool.orm.mybatis.core.util;

import com.bruce.tool.common.exception.BaseRuntimeException;
import com.bruce.tool.common.exception.ExceptionUtils;
import com.mine.tool.orm.mybatis.core.annotation.Column;
import com.mine.tool.orm.mybatis.core.annotation.Id;
import com.mine.tool.orm.mybatis.core.annotation.Table;
import com.mine.tool.orm.mybatis.core.annotation.Transient;
import com.mine.tool.orm.mybatis.core.constant.SQLCode;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 功能 :
 * 数据库表字段工具类
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ColumnUtils {

    /**MySQL的SQL语句中,需要特殊处理的关键字**/
    private static final String[] keywords = new String[]{
            "add","all","alter","analyze","and","as","asc","asensitive","before","between","bigint","binary","blob","both","by","call","cascade","case","change","char","character","check","collate","column","condition","connection","constraint","continue","convert","create","cross","current_date","current_time","current_timestamp","current_user","cursor","database","databases","day_hour","day_microsecond","day_minute","day_second","dec","decimal","declare","default","delayed","delete","desc","describe","deterministic","distinct","distinctrow","div","double","drop","dual","each","else","elseif","enclosed","escaped","exists","exit","explain","false","fetch","float","float4","float8","for","force","foreign","from","fulltext","goto","grant","group","having","high_priority","hour_microsecond","hour_minute","hour_second","if","ignore","in","index","infile","inner","inout","insensitive","insert","int","int1","int2","int3","int4","int8","integer","interval","into","is","iterate","join","key","keys","kill","label","leading","leave","left","like","limit","linear","lines","load","localtime","localtimestamp","lock","long","longblob","longtext","loop","low_priority","match","mediumblob","mediumint","mediumtext","middleint","minute_microsecond","minute_second","mod","modifies","natural","not","no_write_to_binlog","null","numeric","on","optimize","option","optionally","or","order","out","outer","outfile","precision","primary","procedure","purge","raid0","range","read","reads","real","references","regexp","release","rename","repeat","replace","require","restrict","return","revoke","right","rlike","schema","schemas","second_microsecond","select","sensitive","separator","set","show","smallint","spatial","specific","sql","sqlexception","sqlstate","sqlwarning","sql_big_result","sql_calc_found_rows","sql_small_result","ssl","starting","straight_join","table","terminated","then","tinyblob","tinyint","tinytext","to","trailing","trigger","true","undo","union","unique","unlock","unsigned","update","usage","use","using","utc_date","utc_time","utc_timestamp","values","varbinary","varchar","varcharacter","varying","when","where","while","with","write","x509","xor","year_month","zerofill"
    };

    private static final List<String> keywordlist = new ArrayList<>();

    /**
     * 数据库字段引号
     */
    private static final String QUOTATION_MARKS = "`";

    /**
     * 允许的数据类型
     */
    private static final Set<Class> allowDataClassType;

    static{
        keywordlist.addAll(Arrays.asList(keywords));

        allowDataClassType = new HashSet<>();
        allowDataClassType.add(Byte.class);
        allowDataClassType.add(Short.class);
        allowDataClassType.add(Integer.class);
        allowDataClassType.add(Long.class);
        allowDataClassType.add(Character.class);
        allowDataClassType.add(Float.class);
        allowDataClassType.add(Double.class);
        allowDataClassType.add(String.class);
        allowDataClassType.add(BigDecimal.class);
    }

    /**初始化表名:注解+驼峰**/
    public static String fetchTableName(Class<?> clazz) {
        String tableName = fetchTableNameFromAnnotation(clazz);
        if(StringUtils.isEmpty(tableName)){
            tableName = humpToUnderline(clazz.getSimpleName());
        }
        return tableName;
    }

    /**从注解获取表名**/
    public static String fetchTableNameFromAnnotation(Class<?> clazz) {
        Table table = clazz.getAnnotation(Table.class);
        String tableName = "";
        if( null != table ){
            tableName = table.value();
        }
        return tableName;
    }

    /**获取表字段名**/
    public static String fetchColumnName(Field field){
        String columnName = fetchColumnNameFromAnnotation(field);
        if( StringUtils.isEmpty(columnName) ){
            columnName = humpToUnderline(field.getName());
        }
        return fetchColumnName(columnName);
    }

    /**获取表字段名**/
    public static String fetchColumnName(String columnName){
        // mysql关键字的特殊处理
        if( keywordlist.contains(columnName.toLowerCase()) ){
            columnName = QUOTATION_MARKS + columnName + QUOTATION_MARKS;
        }
        return columnName;
    }

    /**获取属性名**/
    public static String fetchFieldName(Field field){
        String fieldName = field.getName();
        return fetchFieldName(fieldName);
    }

    /**获取属性名**/
    public static String fetchFieldName(String fieldName){
        if( keywordlist.contains(fieldName.toLowerCase()) ){
            fieldName = QUOTATION_MARKS + fieldName + QUOTATION_MARKS;
        }
        return fieldName;
    }

    /**从注解获取字段名**/
    public static String fetchColumnNameFromAnnotation(Field field) {
        Id id = field.getAnnotation(Id.class);
        String columnName = "";
        if( null != id ){
            columnName = id.value();
        }
        if( !StringUtils.isEmpty(columnName) ){
            return columnName;
        }
        Column column = field.getAnnotation(Column.class);
        if( null != column ){
            columnName = column.value();
        }
        return columnName;
    }

    /**驼峰转下划线**/
    @SuppressWarnings("all")
    public static String humpToUnderline(String humpString) {
        if (humpString == null || humpString.isEmpty()) {
            return "";
        }
        String prefix = "";
        if (humpString.charAt(0) == '_') {
            humpString = humpString.substring(1, humpString.length());
            prefix = "_";
        }
        String regexStr = "[A-Z0-9]";
        Matcher matcher = Pattern.compile(regexStr).matcher(humpString);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            String g = matcher.group();
            matcher.appendReplacement(sb, "_" + g.toLowerCase());
        }
        matcher.appendTail(sb);
        if (sb.charAt(0) == '_') {
            sb.delete(0, 1);
        }
        sb.insert(0, prefix);
        return sb.toString();
    }

    /**判断是否是主键**/
    public static boolean isPrimaryKey(Field field){
        Id id = field.getAnnotation(Id.class);
        return Objects.nonNull(id);
    }

    /**是否需要忽略该字段**/
    public static boolean needIgnore(Field field){
        return Objects.nonNull(field.getAnnotation(Transient.class));
    }

    /**判断字段是否有值**/
    public static <T> boolean fieldHasValue(T obj, Field field) {
        Object value;
        if (!isAllowClassType(field)) {
            throw new BaseRuntimeException(SQLCode.FE005.getCode(),"不允许的字段类型");
        }
        try {
            field.setAccessible(true);
            // 获取字段值, 判断是否为空
            value = field.get(obj);
        } catch (IllegalAccessException e) {
            ExceptionUtils.printStackTrace(e);
            throw new BaseRuntimeException(SQLCode.FE005.getCode(),"获取字段值错误");
        }
        return Objects.nonNull(value);
    }

    /**判断字段是否为允许的数据类型**/
    private static boolean isAllowClassType(Field field) {
        return allowDataClassType.contains(field.getType());
    }
}
