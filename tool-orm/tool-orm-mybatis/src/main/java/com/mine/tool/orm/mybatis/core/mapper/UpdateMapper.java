package com.mine.tool.orm.mybatis.core.mapper;

import com.mine.tool.orm.mybatis.core.provider.UpdateProvider;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.UpdateProvider;

import java.util.List;

/**
 * 功能 :
 * mybatis数据更新功能
 * 1.update(domain)
 * 2.updateSelective(domain)
 * 3.updateAll(List<domain> domains)
 * 4.updateBySQL(sql)
 */
@SuppressWarnings("all")
public interface UpdateMapper<T> {

    @UpdateProvider(type = UpdateProvider.class, method = "update")
    Integer update(T domain);

    @UpdateProvider(type = UpdateProvider.class, method = "updateBySelective")
    Integer updateBySelective(T domain);

    @UpdateProvider(type = UpdateProvider.class, method = "updateAll")
    Integer updateAll(@Param("list") List<T> list);
}
