package com.mine.tool.orm.mybatis.generator.mapper;

import com.bruce.tool.orm.mybatis.core.mapper.BaseMapper;
import com.bruce.tool.orm.mybatis.core.provider.SaveProvider;
import com.mine.tool.orm.mybatis.generator.domain.AdminRole;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface AdminRoleMapper extends BaseMapper<AdminRole> {
    @Override
    @InsertProvider(type = SaveProvider.class, method = "save")
    @Options(useGeneratedKeys = true,keyColumn = "role_id",keyProperty = "roleId")
    Integer save(AdminRole domain);

    @Override
    @InsertProvider(type = SaveProvider.class, method = "saveBySelective")
    @Options(useGeneratedKeys = true,keyColumn = "role_id",keyProperty = "roleId")
    Integer saveBySelective(AdminRole domain);
}