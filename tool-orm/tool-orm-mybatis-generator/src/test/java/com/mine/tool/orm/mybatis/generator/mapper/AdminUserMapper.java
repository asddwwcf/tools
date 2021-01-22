package com.mine.tool.orm.mybatis.generator.mapper;

import com.mine.tool.orm.mybatis.core.mapper.BaseMapper;
import com.mine.tool.orm.mybatis.core.provider.SaveProvider;
import com.mine.tool.orm.mybatis.generator.domain.AdminUser;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface AdminUserMapper extends BaseMapper<AdminUser> {
    @Override
    @InsertProvider(type = SaveProvider.class, method = "save")
    @Options(useGeneratedKeys = true,keyColumn = "user_id",keyProperty = "userId")
    Integer save(AdminUser domain);

    @Override
    @InsertProvider(type = SaveProvider.class, method = "saveBySelective")
    @Options(useGeneratedKeys = true,keyColumn = "user_id",keyProperty = "userId")
    Integer saveBySelective(AdminUser domain);
}