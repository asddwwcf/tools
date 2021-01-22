package com.mine.tool.orm.mybatis.generator.service;

import com.mine.tool.orm.mybatis.core.mapper.BaseMapper;
import com.mine.tool.orm.mybatis.core.service.AbstractBaseService;
import com.mine.tool.orm.mybatis.generator.domain.AdminMenu;
import com.mine.tool.orm.mybatis.generator.mapper.AdminMenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AdminMenuServiceImpl extends AbstractBaseService<AdminMenu> implements AdminMenuService {
    @Autowired
    private AdminMenuMapper adminMenuMapper;

    @Override
    protected Class<AdminMenu> clazz() {
        return AdminMenu.class;
    }

    @Override
    protected BaseMapper<AdminMenu> baseMapper() {
        return adminMenuMapper;
    }
}