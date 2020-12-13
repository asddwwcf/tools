package com.mine.tool.orm.mybatis.core.builder;

/**
 * Created by wuyujia on 17/4/5.
 */

import com.mine.tool.orm.mybatis.core.util.ColumnUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * 拼接批量插入语句工具类第二版
 * 使用注意:
 *  1. 只支持javaBean, 不支持其他任何java类, 否则报错
 *  2. 工具根据反射读取Bean对象字段属性 (第一版读取的是get方法), 如果需要设置默认值, 请在字段直接设定
 *  3. 批量插入时, 请注意数据匹配, 每个对象有值得字段必须一样, 如果不一样则可能出现插入失败 (失败原因: 数据库不支持null值插入)
 */
@SuppressWarnings("all")
public class BatchInsertSQLBuilder<T> {

    /**
     * 无参构造
     */
    public BatchInsertSQLBuilder() {
    }

    /**
     * 多参构造方法
     * @param tableName
     * @param dataList
     * @param selective
     */
    public BatchInsertSQLBuilder(String tableName, LinkedList<T> dataList, boolean selective) {
        this.setInsertTable(tableName);
        this.setData(dataList);
        this.setSelective(selective);
    }

    /**
     * 待插入数据的表名
     */
    private String tableName;

    /**
     * 待读取的字段
     */
    private Field[] fields = new Field[0];

    /**
     * 数据集
     */
    private LinkedList<T> dataList = new LinkedList<>();

    /**
     * 是否为全量更新标志
     * true: 非空值更新
     * false: 全量更新
     */
    private boolean selective = true;

    /**
     * 插入语句起始
     */
    private static final String INSERT_INTO = "INSERT INTO ";

    private static final String VALUES = "VALUES";

    /**
     * 括号开启
     */
    private static final String QUOTE_OPEN = " (";

    /**
     * 括号关闭
     */
    private static final String QUOTE_CLOSE = ") ";

    /**
     * 逗号连接符
     */
    private static final String COMMA = ",";

    /**
     * 引号
     */
    private static final String QUOTATION_MARKS = "'";

    /**
     * 数据库字段引号
     */
    private static final String QUOTATION_MARKS_OPEN = "`";

    /**
     * 数据库字段引号
     */
    private static final String QUOTATION_MARKS_CLOSE = "`";

    /**
     * 设置待插入数据的表名
     *
     * @param tableName
     * @return
     */
    public BatchInsertSQLBuilder setInsertTable(String tableName) {
        this.tableName = tableName;
        return this;
    }

    /**
     * 设置是否为全量更新
     *
     * @param selective 选择更新
     * @return
     */
    public BatchInsertSQLBuilder setSelective(boolean selective) {
        this.selective = selective;
        return this;
    }

    /**
     * 插入数据集
     *
     * @param obj
     * @return
     */
    @SuppressWarnings("all")
    public BatchInsertSQLBuilder setData(T obj) {
        if (!selective) {
            // 如果是全量更新, 就存储全部字段
            if (fields.length == 0) {
                List<Field> fieldList = new LinkedList<>();
                Field[] declaredFields = obj.getClass().getDeclaredFields();
                for (Field field : declaredFields) {
                    field.setAccessible(true);
                    if( ColumnUtils.needIgnore(field) ){ continue; }
                    if( Modifier.isStatic(field.getModifiers()) ) { continue; }
                    fieldList.add(field);
                }
                fields = fieldList.toArray(fields);
            }
            // 插入一条数据到数据集
            this.dataList.add(obj);
            // 返回
            return this;
        }
        // 如果不是全量更新, 则存储有值得字段, 以第一个为准
        if (fields.length == 0) {
            Field[] declaredFields = obj.getClass().getDeclaredFields();
            List<Field> fieldList = new LinkedList<>();
            for (Field field : declaredFields) {
                field.setAccessible(true);
                if( ColumnUtils.needIgnore(field) ){ continue; }
                if( Modifier.isStatic(field.getModifiers()) ) { continue; }
                if (ColumnUtils.fieldHasValue(obj, field)) {
                    fieldList.add(field);
                }
            }
            fields = fieldList.toArray(fields);
        }
        // 插入一条数据到数据集
        this.dataList.add(obj);
        return this;
    }

    /**
     * 批量插入数据集
     * @param list
     * @return
     */
    public BatchInsertSQLBuilder setData(Collection<T> list) {
        if (list == null || list.isEmpty()) {
            throw new RuntimeException("待插入数据为空");
        }
        for (T t : list) {
            this.setData(t);
        }
        return this;
    }

    @Override
    public String toString() {
        // 必要参数校验
        paramCheck();
        StringBuilder sb = new StringBuilder();
        try {
            sb.append(INSERT_INTO).append(tableName);
            // 获取字段映射
            sb.append(getFieldString());
            sb.append(VALUES);
            for (T t : dataList) {
                // 获取对象值映射
                sb.append(getValueString(t));
                sb.append(COMMA);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return sb.substring(0, sb.length() - 1);
    }

    /**
     * 获取字段拼接的字符串
     *
     * @return
     */
    private String getFieldString() {
        StringBuilder sb = new StringBuilder();
        sb.append(QUOTE_OPEN);
        for (Field field : fields) {
            field.setAccessible(true);
            if( ColumnUtils.needIgnore(field) ){ continue; }
            String columnName = ColumnUtils.fetchColumnName(field);
            if( !StringUtils.isEmpty(columnName) ){
                sb.append(columnName);
                sb.append(COMMA);
            }
        }
        if( sb.toString().endsWith(COMMA) ){
            sb.replace(sb.length()-1, sb.length(), "");
        }
        sb.append(QUOTE_CLOSE);
        return sb.toString();
    }

    /**
     * 获取值得拼接字符串
     *
     * @param t
     * @return
     */
    private String getValueString(T t) throws IllegalAccessException {
        StringBuilder sb = new StringBuilder();
        sb.append(QUOTE_OPEN);
        for (Field field : fields) {
            field.setAccessible(true);
            Object value = field.get(t);
            if (value != null) {
                sb.append(QUOTATION_MARKS);
                value = value.toString().replaceAll("\\\\", "");
                value = value.toString().replaceAll("'", "\\\\\'");
                value = value.toString().replaceAll("\"", "\\\\\"");
                sb.append(value);
                sb.append(QUOTATION_MARKS);
            } else {
                sb.append(value);
            }
            sb.append(COMMA);
        }
        sb.replace(sb.lastIndexOf(COMMA), sb.lastIndexOf(COMMA) + 1, "");
        sb.append(QUOTE_CLOSE);
        return sb.toString();
    }

    /**
     * 必要参数校验
     */
    private void paramCheck() {
        if (tableName == null || tableName.isEmpty()) {
            throw new RuntimeException("插入表名为空");
        }
        if (dataList.isEmpty()) {
            throw new RuntimeException("待插入数据集为空");
        }
        if (fields.length == 0) {
            throw new RuntimeException("待插入字段为空");
        }
    }

}
