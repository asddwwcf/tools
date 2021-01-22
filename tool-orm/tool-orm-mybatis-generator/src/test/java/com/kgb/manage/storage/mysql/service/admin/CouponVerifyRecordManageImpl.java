package com.kgb.manage.storage.mysql.service.admin;

import com.mine.tool.orm.mybatis.core.mapper.BaseMapper;
import com.mine.tool.orm.mybatis.core.service.AbstractBaseService;
import com.kgb.manage.storage.mysql.domain.admin.CouponVerifyRecord;
import com.kgb.manage.storage.mysql.mapper.admin.CouponVerifyRecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CouponVerifyRecordManageImpl extends AbstractBaseService<CouponVerifyRecord> implements CouponVerifyRecordManage {
    @Autowired
    private CouponVerifyRecordMapper couponVerifyRecordMapper;

    @Override
    protected Class<CouponVerifyRecord> clazz() {
        return CouponVerifyRecord.class;
    }

    @Override
    protected BaseMapper<CouponVerifyRecord> baseMapper() {
        return couponVerifyRecordMapper;
    }
}