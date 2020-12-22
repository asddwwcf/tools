package com.mine.tool.orm.mybatis.generator;

import com.bruce.tool.common.util.SpringBeanUtils;
import com.bruce.tool.common.util.string.JsonUtils;
//import com.bruce.tool.orm.mybatis.generator.domain.AdminMenu;
//import com.bruce.tool.orm.mybatis.generator.mapper.AdminMenuMapper;
import io.jsonwebtoken.lang.Assert;
import lombok.extern.slf4j.Slf4j;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 功能 :
 *
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MybatisGeneratorApplication.class)
public class SaveTest extends AbstractJUnit4SpringContextTests {
//
//    @Autowired
//    private AdminMenuMapper adminMenuMapper;
//
//    @Before
//    public void init(){
//        SpringBeanUtils.setApplicationContext(applicationContext);
//    }
//
//    @Test
//    public void save(){
//        String json = "{\"parentId\":1,\"menuName\":\"用户管理\",\"href\":\"auth/user/index\",\"isDelete\":0}";
//        AdminMenu menu = JsonUtils.strToObj(json,AdminMenu.class);
//        Integer count = adminMenuMapper.save(menu);
//        Assert.isTrue(count > 0);
//    }
//
//    @Test
//    public void saveAll(){
//        List<AdminMenu> menus = Lists.newArrayList();
//        String json = "{\"parentId\":1,\"menuName\":\"用户管理\",\"href\":\"auth/user/index\",\"isDelete\":0}";
//        AdminMenu menu = JsonUtils.strToObj(json,AdminMenu.class);
//        menus.add(menu);
//        Integer count = adminMenuMapper.saveAll(menus);
//        Assert.isTrue(count > 0);
//    }
//
//    @Test
//    public void saveBySQL(){
//        String sql = "insert into admin_menu(parent_id,menu_name,href)values(1,'用户管理测试','auth/user/index/test')";
//        Integer count = adminMenuMapper.saveBySQL(sql);
//        Assert.isTrue(count > 0);
//    }

}
