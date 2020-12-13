package com.mine.tool.orm.mybatis.generator.service;

import com.bruce.tool.orm.mybatis.core.mapper.BaseMapper;
import com.bruce.tool.orm.mybatis.core.service.AbstractBaseService;
import com.mine.tool.orm.mybatis.generator.domain.RegionCopy;
import com.mine.tool.orm.mybatis.generator.mapper.RegionCopyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RegionCopyServiceImpl extends AbstractBaseService<RegionCopy> implements RegionCopyService {
    @Autowired
    private RegionCopyMapper regionCopyMapper;

    @Override
    protected Class<RegionCopy> clazz() {
        return RegionCopy.class;
    }

    @Override
    protected BaseMapper<RegionCopy> baseMapper() {
        return regionCopyMapper;
    }
}