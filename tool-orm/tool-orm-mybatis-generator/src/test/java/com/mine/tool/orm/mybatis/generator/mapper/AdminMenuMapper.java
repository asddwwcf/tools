package com.mine.tool.orm.mybatis.generator.mapper;

import com.bruce.tool.orm.mybatis.core.annotation.ReturnExecutableSQL;
import com.bruce.tool.orm.mybatis.core.mapper.BaseMapper;
import com.mine.tool.orm.mybatis.generator.domain.AdminMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface AdminMenuMapper extends BaseMapper<AdminMenu> {

    @Select(" select \n" +
            "menu_name \n" +
            "from \n" +
            "admin_menu ")
    @ReturnExecutableSQL
    String fetchAllMenu();

    @Select(" select \n\n" +
            "menu_name \n\n\n" +
            "from \n" +
            "admin_menu \nwhere menu_name \nlike '%${name}%'")
    List<AdminMenu> selectMenusLike(@Param("name") String name);

//    @Select(" select \n\n" +
//            "menu_name \n\n\n" +
//            "from \n" +
//            "admin_menu \nwhere menu_name \n=#{name}")
//    List<AdminMenu> selectMenusLike2(String name);
}