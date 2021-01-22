package com.mine.tool.orm.mybatis.generator.service;

import com.mine.tool.orm.mybatis.core.mapper.BaseMapper;
import com.mine.tool.orm.mybatis.core.service.AbstractBaseService;
import com.mine.tool.orm.mybatis.generator.domain.Region;
import com.mine.tool.orm.mybatis.generator.mapper.RegionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RegionServiceImpl extends AbstractBaseService<Region> implements RegionService {
    @Autowired
    private RegionMapper regionMapper;

    @Override
    protected Class<Region> clazz() {
        return Region.class;
    }

    @Override
    protected BaseMapper<Region> baseMapper() {
        return regionMapper;
    }
}