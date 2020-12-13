package com.mine.tool.orm.mybatis.core.service;

import com.mine.tool.orm.mybatis.core.mapper.BaseMapper;
import com.mine.tool.orm.mybatis.core.query.Example;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 功能 :
 * @author : Bruce(刘正航) 7:20 下午 2020/1/1
 */
@SuppressWarnings("all")
public abstract class AbstractBaseService<T> implements BaseService<T>{

    protected abstract Class<T> clazz();

    protected abstract BaseMapper<T> baseMapper();

    @Override
    public T findById(Integer id) {
        return baseMapper().findById(clazz(),id);
    }

    @Override
    public int deleteById(Integer id) {
        return baseMapper().deleteById(clazz(),id);
    }

    @Override
    public int markDeleteById(Integer id) {
        return baseMapper().markDeleteById(clazz(),id);
    }

    @Override
    public int save(T domain) {
        return baseMapper().save(domain);
    }

    @Override
    public int saveBySelective(T domain) {
        return baseMapper().saveBySelective(domain);
    }

    @Override
    public int saveAll(List<T> domains) {
        return baseMapper().saveAll(domains);
    }

    @Override
    public int update(T domain) {
        return baseMapper().update(domain);
    }

    @Override
    public int updateBySelective(T domain) {
        return baseMapper().updateBySelective(domain);
    }

    @Override
    public int updateAll(List<T> domains) {
        return baseMapper().updateAll(domains);
    }

    @Override
    public long countByExample(final Example example) {
        return PageHelper.count(()-> baseMapper().findsByExample(example));
    }

    @Override
    public List<T> limitByExample(final Example example, Integer limit) {
        return (List<T>) PageHelper.startPage(1, limit)
                .setCount(false)
                .doSelectPage(()-> baseMapper().findsByExample(example))
                .getResult();
    }

    @Override
    public List<T> nextByExample(Example example, Integer offset, Integer limit) {
        return (List<T>) PageHelper.offsetPage(offset, limit,false)
                .doSelectPage(()-> baseMapper().findsByExample(example))
                .getResult();
    }

    @Override
    public T findByExample(Example example) {
        return baseMapper().findByExample(example);
    }

    @Override
    public List<T> findsByExample(Example example) {
        return baseMapper().findsByExample(example);
    }

    @Override
    public Page<T> pageByExample(final Example example, Integer pageNum, Integer pageSize) {
        return PageHelper.startPage(pageNum, pageSize).doSelectPage(()-> baseMapper().findsByExample(example));
    }

    @Override
    public PageInfo<T> pageInfoByExample(final Example example, Integer pageNum, Integer pageSize) {
        return PageHelper.startPage(pageNum, pageSize).doSelectPageInfo(()-> baseMapper().findsByExample(example));
    }

}
