package com.mine.tool.orm.mybatis.generator.service;

import com.bruce.tool.orm.mybatis.core.mapper.BaseMapper;
import com.bruce.tool.orm.mybatis.core.service.AbstractBaseService;
import com.mine.tool.orm.mybatis.generator.domain.AdminRole;
import com.mine.tool.orm.mybatis.generator.mapper.AdminRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AdminRoleServiceImpl extends AbstractBaseService<AdminRole> implements AdminRoleService {
    @Autowired
    private AdminRoleMapper adminRoleMapper;

    @Override
    protected Class<AdminRole> clazz() {
        return AdminRole.class;
    }

    @Override
    protected BaseMapper<AdminRole> baseMapper() {
        return adminRoleMapper;
    }
}