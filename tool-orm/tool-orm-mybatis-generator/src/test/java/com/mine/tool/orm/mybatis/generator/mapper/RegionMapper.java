package com.mine.tool.orm.mybatis.generator.mapper;

import com.bruce.tool.orm.mybatis.core.mapper.BaseMapper;
import com.mine.tool.orm.mybatis.generator.domain.Region;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface RegionMapper extends BaseMapper<Region> {
}