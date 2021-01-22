package com.mine.tool.orm.mybatis.generator.mapper;

import com.mine.tool.orm.mybatis.core.mapper.BaseMapper;
import com.mine.tool.orm.mybatis.generator.domain.RegionCopy;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface RegionCopyMapper extends BaseMapper<RegionCopy> {
}