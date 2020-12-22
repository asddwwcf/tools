package com.mine.tool.orm.mybatis.core.mapper;

import com.mine.tool.orm.mybatis.core.provider.SaveProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 功能 :
 * mybatis保存数据接口
 * 1.save(domain)
 * 2.saveAll(List<domain> domains)
 * 3.saveBySQL(sql)
 */
@SuppressWarnings("all")
public interface SaveMapper<T> {
    @InsertProvider(type = SaveProvider.class, method = "save")
    @Options(useGeneratedKeys=true)
    Integer save(T domain);

    @InsertProvider(type = SaveProvider.class, method = "saveBySelective")
    @Options(useGeneratedKeys=true)
    Integer saveBySelective(T domain);

    @InsertProvider(type = SaveProvider.class, method = "saveAll")
    Integer saveAll(@Param("list") List<T> list);

}
