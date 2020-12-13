package com.kgb.manage.storage.mysql.service.admin;

import com.bruce.tool.orm.mybatis.core.mapper.BaseMapper;
import com.bruce.tool.orm.mybatis.core.service.AbstractBaseService;
import com.kgb.manage.storage.mysql.domain.admin.PartnerThirdMatch;
import com.kgb.manage.storage.mysql.mapper.admin.PartnerThirdMatchMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PartnerThirdMatchManageImpl extends AbstractBaseService<PartnerThirdMatch> implements PartnerThirdMatchManage {
    @Autowired
    private PartnerThirdMatchMapper partnerThirdMatchMapper;

    @Override
    protected Class<PartnerThirdMatch> clazz() {
        return PartnerThirdMatch.class;
    }

    @Override
    protected BaseMapper<PartnerThirdMatch> baseMapper() {
        return partnerThirdMatchMapper;
    }
}