package com.mine.tool.orm.mybatis.generator.service;

import com.bruce.tool.orm.mybatis.core.mapper.BaseMapper;
import com.bruce.tool.orm.mybatis.core.service.AbstractBaseService;
import com.mine.tool.orm.mybatis.generator.domain.AdminUser;
import com.mine.tool.orm.mybatis.generator.mapper.AdminUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AdminUserServiceImpl extends AbstractBaseService<AdminUser> implements AdminUserService {
    @Autowired
    private AdminUserMapper adminUserMapper;

    @Override
    protected Class<AdminUser> clazz() {
        return AdminUser.class;
    }

    @Override
    protected BaseMapper<AdminUser> baseMapper() {
        return adminUserMapper;
    }
}