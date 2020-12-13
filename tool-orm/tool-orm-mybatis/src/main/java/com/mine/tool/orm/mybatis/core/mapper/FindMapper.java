package com.mine.tool.orm.mybatis.core.mapper;

import com.mine.tool.orm.mybatis.core.provider.FindProvider;
import com.mine.tool.orm.mybatis.core.query.Example;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

/**
 * 功能 :
 * mybatis基础查询接口
 * 1.findById(domain)
 * 2.findById(Class,id)
 * 3.findByColumn(Class,key,value)
 * 4.findsByColumn(Class,key,value)
 * 5.findByColumns(domain) //domain中有的值,都算查询条件,可以有多个字段,可以只有一个字段
 * 6.findsByColumns(domain) //domain中有的值,都算查询条件,可以有多个字段,可以只有一个字段
 * 7.findBySQL(sql)
 *
 * 8.findsByCriteria(Class,CriteriaQuery)
 * 9.findColumnsByCriteria(column,table,CriteriaQuery)
 * 10.findTablesByCriteria(sql,CriteriaQuery)
 * 11.findsBySQL(sql)
 *
 * @author : Bruce(刘正航) 17:01 2019-01-20
 */
@SuppressWarnings("all")
public interface FindMapper<T> {

    @SelectProvider(type = FindProvider.class, method = "findById")
    T findById(@Param("clazz") Class<T> clazz,@Param("id") Number id);

    @SelectProvider(type = FindProvider.class, method = "findByExample")
    T findByExample(Example example);

    @SelectProvider(type = FindProvider.class, method = "findsByExample")
    List<T> findsByExample(Example example);

}
