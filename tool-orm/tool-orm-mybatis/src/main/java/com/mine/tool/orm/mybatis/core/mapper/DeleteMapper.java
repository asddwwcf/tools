package com.mine.tool.orm.mybatis.core.mapper;

import com.mine.tool.orm.mybatis.core.provider.DeleteProvider;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.UpdateProvider;

/**
 * 功能 :
 * mybatis删除数据功能
 * 1.deleteById(id)
 * 2.deleteByColumn(domain) //domain中有的值,都作为条件
 * 3.deleteByColumn(Class,key,value)
 * 4.deleteBySQL(sql)
 * 5.markDeleteById(id)
 * 6.markDeleteById(id,mark)
 * @author : Bruce(刘正航) 21:19 2019-01-20
 */
@SuppressWarnings("all")
public interface DeleteMapper<T> {

    /**
     * 物理删除一条记录
     */
    @DeleteProvider(type = DeleteProvider.class, method = "deleteById")
    Integer deleteById(@Param("clazz") Class<T> clazz,@Param("id") Object id);

    /**
     * 逻辑删除一条记录(默认标记字段:is_delete)
     */
    @UpdateProvider(type = DeleteProvider.class, method = "markDeleteById")
    Integer markDeleteById(@Param("clazz") Class<T> clazz, @Param("id") Object id);

    /**
     * 逻辑删除一条记录(自定义标记字段)
     */
    @UpdateProvider(type = DeleteProvider.class, method = "markDeleteByIdDiy")
    Integer markDeleteByIdDiy(@Param("clazz") Class<T> clazz, @Param("id") Object id, @Param("mark") String mark);

}
