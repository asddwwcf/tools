package com.mine.tool.orm.mybatis.core.provider;

import com.bruce.tool.common.exception.BaseRuntimeException;
import com.mine.tool.orm.mybatis.core.builder.BatchInsertSQLBuilder;
import com.mine.tool.orm.mybatis.core.constant.SQLCode;
import com.mine.tool.orm.mybatis.core.util.ColumnUtils;
import com.mine.tool.orm.mybatis.core.util.TableUtils;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 功能 :
 * mybatis保存数据接口
 * 1.save(domain)
 * 2.saveAll(List<domain> domains)
 * 3.saveByColumn(sql)
 * @author : Bruce(刘正航) 00:33 2019-01-23
 */
@SuppressWarnings("all")
public class SaveProvider {

    public String save(Object domain) {
        return save(domain, true);
    }

    public String saveBySelective(Object domain) {
        return save(domain, false);
    }

    /**批量保存**/
    public String saveAll(Map<String,Object> params){
        List list = (List) params.get("list");
        if(CollectionUtils.isEmpty(list)){
            return null;
        }
        Object item = list.get(0);
        Class clazz = item.getClass();
        String tableName = TableUtils.getTableName(clazz);
        BatchInsertSQLBuilder builder = new BatchInsertSQLBuilder();
        builder.setInsertTable(tableName);
        builder.setSelective(false);
        builder.setData(list);
        return builder.toString();
    }


    private String save(Object bean, boolean saveNull) {
        Class<?> beanClass = bean.getClass();
        String tableName = TableUtils.getTableName(beanClass);
        StringBuilder insertSql = new StringBuilder();
        List<String> insertParams = new ArrayList<>();
        List<String> insertParamNames = new ArrayList<>();
        insertSql.append("insert into ").append(tableName).append("(");
        try {
            List<Field> fields = TableUtils.getFields(beanClass);
            for (Field field : fields){
                String columnName = ColumnUtils.fetchColumnName(field);
                field.setAccessible(true);
                Object value = field.get(bean);
                if (Objects.nonNull(value)) {
                    insertParamNames.add(columnName);
                    insertParams.add("#{" + field.getName() + "}");
                    continue;
                }
                if(saveNull){
                    insertParamNames.add(columnName);
                    insertParams.add("#{" + field.getName() + "}");
                }
            }
        } catch (Exception e) {
            throw new BaseRuntimeException(SQLCode.FE004.getCode(),"插入语句设置异常:" + e.getMessage());
        }
        for (int i = 0; i < insertParamNames.size(); i++) {
            insertSql.append(insertParamNames.get(i));
            if (i != insertParamNames.size() - 1){
                insertSql.append(",");
            }
        }
        insertSql.append(")").append(" values(");
        for (int i = 0; i < insertParams.size(); i++) {
            insertSql.append(insertParams.get(i));
            if (i != insertParams.size() - 1){
                insertSql.append(",");
            }
        }
        insertSql.append(")");
        return insertSql.toString();
    }
}
