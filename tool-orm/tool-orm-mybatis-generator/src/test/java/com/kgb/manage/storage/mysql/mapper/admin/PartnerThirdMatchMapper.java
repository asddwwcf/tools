package com.kgb.manage.storage.mysql.mapper.admin;

import com.mine.tool.orm.mybatis.core.mapper.BaseMapper;
import com.kgb.manage.storage.mysql.domain.admin.PartnerThirdMatch;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface PartnerThirdMatchMapper extends BaseMapper<PartnerThirdMatch> {
}