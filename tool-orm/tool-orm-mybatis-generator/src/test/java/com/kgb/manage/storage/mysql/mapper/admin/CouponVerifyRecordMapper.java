package com.kgb.manage.storage.mysql.mapper.admin;

import com.bruce.tool.orm.mybatis.core.mapper.BaseMapper;
import com.kgb.manage.storage.mysql.domain.admin.CouponVerifyRecord;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface CouponVerifyRecordMapper extends BaseMapper<CouponVerifyRecord> {
}