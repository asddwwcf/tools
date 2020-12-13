package com.mine.tool.orm.mybatis.core.mapper;

/**
 * 功能 :
 *
 * @author : Bruce(刘正航) 01:02 2019-01-23
 */
public interface BaseMapper<T> extends SaveMapper<T>, DeleteMapper<T>, FindMapper<T>, UpdateMapper<T>{
}
