package com.mine.tool.orm.mybatis.generator;

import com.bruce.tool.common.util.DateUtils;
import com.bruce.tool.common.util.SpringBeanUtils;
import com.bruce.tool.common.util.string.JsonUtils;
import io.jsonwebtoken.lang.Assert;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
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
public class UpdateTest extends AbstractJUnit4SpringContextTests {

//    @Autowired
//    private AdminMenuMapper adminMenuMapper;

    @Before
    public void init(){
        SpringBeanUtils.setApplicationContext(applicationContext);
    }

//    @Test
//    public void update(){
//        String json = "{\"id\":2,\"parentId\":1,\"menuName\":\"用户管理\",\"href\":\"auth/user/index\",\"isDelete\":0}";
//        AdminMenu menu = JsonUtils.strToObj(json,AdminMenu.class);
//        Integer count = adminMenuMapper.update(menu);
//        Assert.isTrue(count > 0);
//    }
//
//    @Test
//    public void updateBySelective(){
//        String json = "{\"id\":2,\"parentId\":1,\"menuName\":\"用户管理\",\"href\":\"auth/user/index\",\"isDelete\":0}";
//        AdminMenu menu = JsonUtils.strToObj(json,AdminMenu.class);
//        Integer count = adminMenuMapper.updateBySelective(menu);
//        Assert.isTrue(count > 0);
//    }
//
//    @Test
//    public void updateAll(){
//        List<AdminMenu> menus = Lists.newArrayList();
//        String json = "{\"id\":2,\"parentId\":1,\"menuName\":\"用户管理\",\"href\":\"auth/user/index\",\"isDelete\":0}";
//        AdminMenu menu = JsonUtils.strToObj(json,AdminMenu.class);
//        menus.add(menu);
//        Integer count = adminMenuMapper.updateAll(menus);
//        Assert.isTrue(count > 0);
//    }
//
//    @Test
//    public void updateBySQL(){
//        String sql = "update admin_menu set is_delete="+ DateUtils.create().getMillis()/1000 +" where id=1";
//        Integer count = adminMenuMapper.updateBySQL(sql);
//        Assert.isTrue(count > 0);
//    }

}
