package com.mine.tool.orm.mybatis.core.query;

/**
 * 功能 :
 */
public interface Example<T> {
    /**获取拼接好的查询条件**/
    String getCondition();
}
