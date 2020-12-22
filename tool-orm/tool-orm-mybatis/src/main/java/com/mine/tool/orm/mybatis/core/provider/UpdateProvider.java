package com.mine.tool.orm.mybatis.core.provider;

import com.bruce.tool.common.exception.BaseRuntimeException;
import com.bruce.tool.common.util.string.StringUtils;
import com.mine.tool.orm.mybatis.core.builder.BatchUpdateSQLBuilder;
import com.mine.tool.orm.mybatis.core.constant.SQLCode;
import com.mine.tool.orm.mybatis.core.util.ColumnUtils;
import com.mine.tool.orm.mybatis.core.util.TableUtils;
import org.springframework.util.CollectionUtils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 功能 :
 *
 */
@SuppressWarnings("all")
public class UpdateProvider {

    /**
     * 更新一条记录,保存空值
     */
    public String update(Object domain) {
        return update(domain,true);
    }

    /**
     * 更新一条记录,不保存空值
     */
    public String updateBySelective(Object domain) {
        return update(domain,false);
    }

    /**
     * 批量更新记录
     */
    public String updateAll(Map<String, Object> params) {
        List list = (List) params.get("list");
        if(CollectionUtils.isEmpty(list)){
            return null;
        }
        Object item = list.get(0);
        Class clazz = item.getClass();
        String tableName = TableUtils.getTableName(clazz);
        String primaryKey = TableUtils.getPrimaryKey(clazz);
        if(StringUtils.isBlank(primaryKey)){
            throw new BaseRuntimeException(SQLCode.FE001.getCode(),SQLCode.FE001.getMessage());
        }
        BatchUpdateSQLBuilder builder = new BatchUpdateSQLBuilder();
        builder.setUpdateTable(tableName, primaryKey);
        builder.setData(list);
        return builder.toString();
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

    /**更新语句**/
    private String update(Object domain,boolean saveNull) {
        Class<?> beanClass = domain.getClass();
        String tableName = TableUtils.getTableName(beanClass);
        List<Field> fields = TableUtils.getFields(beanClass);
        StringBuilder updateSql = new StringBuilder();
        updateSql.append("update ").append(tableName).append(" set ");
        try {
            Field primaryKey = null;
            String primaryColumn = null;
            for (Field field : fields){
                String columnName = ColumnUtils.fetchColumnName(field);
                if(ColumnUtils.isPrimaryKey(field)){
                    primaryKey = field;
                    primaryColumn = columnName;
                    continue;
                }
                field.setAccessible(true);
                Object value = field.get(domain);
                if( Objects.nonNull(value) ){
                    updateSql.append(columnName).append("=").append("#{" + field.getName() + "}").append(",");
                    continue;
                }
                if( saveNull ){
                    updateSql.append(columnName).append("=").append("#{" + field.getName() + "}").append(",");
                }
            }
            if( updateSql.toString().endsWith(",") ){
                updateSql = updateSql.replace(updateSql.length()-1,updateSql.length(),"");
            }
            if(Objects.isNull(primaryKey)){
                throw new BaseRuntimeException(SQLCode.FE001.getCode(),SQLCode.FE001.getMessage());
            }
            updateSql.append(" where ").append(primaryColumn).append("=").append("#{" + primaryKey.getName() + "}");
        } catch (Exception e) {
            throw new BaseRuntimeException(SQLCode.FE004.getCode(),"get update sql has exceptoin:" + e);
        }
        return updateSql.toString();
    }
}
