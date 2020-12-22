package com.mine.tool.orm.mybatis.core.service;

import com.mine.tool.orm.mybatis.core.query.Example;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 功能 :
 */
public interface BaseService<T> {

    T findById(Integer id);

    int deleteById(Integer id);

    int markDeleteById(Integer id);

    T findByExample(Example example);

    int save(T domain);

    int saveBySelective(T domain);

    int saveAll(List<T> domains);

    int update(T domain);

    int updateBySelective(T domain);

    int updateAll(List<T> domains);

    long countByExample(Example<T> example);

    List<T> findsByExample(Example<T> example);

    List<T> limitByExample(Example<T> example, Integer limit);

    List<T> nextByExample(Example<T> example, Integer offset, Integer limit);

    Page<T> pageByExample(Example<T> example, Integer pageNum, Integer pageSize);

    PageInfo<T> pageInfoByExample(Example<T> example, Integer pageNum, Integer pageSize);

}
