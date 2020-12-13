package com.mine.tool.orm.mybatis.core.query;

/**
 * 功能 :
 * @author : Bruce(刘正航) 2:24 下午 2019/12/30
 */
public interface Example<T> {
    /**获取拼接好的查询条件**/
    String getCondition();
}
