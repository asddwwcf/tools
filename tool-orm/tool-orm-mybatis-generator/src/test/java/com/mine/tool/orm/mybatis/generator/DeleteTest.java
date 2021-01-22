package com.mine.tool.orm.mybatis.generator;

import com.mine.tool.common.util.SpringBeanUtils;
//import .tool.orm.mybatis.generator.domain.AdminMenu;
//import com.mine.tool.orm.mybatis.generator.mapper.AdminMenuMapper;
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
public class DeleteTest extends AbstractJUnit4SpringContextTests {
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
//    public void deleteByDomain(){
//        AdminMenu menu = new AdminMenu();
//        menu.setId(59);
//        Integer count = adminMenuMapper.deleteById(AdminMenu.class,menu);
//        Assert.isTrue(count > 0);
//    }
//
//    @Test
//    public void deleteById(){
//        Integer count = adminMenuMapper.deleteById(AdminMenu.class,58);
//        Assert.isTrue(count > 0);
//    }
//
//    @Test
//    public void deleteByColumn(){
//        Integer count = adminMenuMapper.deleteByColumn(AdminMenu.class,"id",57);
//        Assert.isTrue(count > 0);
//    }
//
//    @Test
//    public void deleteByColumns(){
//        AdminMenu menu = new AdminMenu();
//        menu.setId(56);
//        Integer count = adminMenuMapper.deleteByColumns(menu);
//        Assert.isTrue(count > 0);
//    }
//
//    @Test
//    public void deleteBySQL(){
//        String sql = "delete from admin_menu where id=54";
//        Integer count = adminMenuMapper.deleteBySQL(sql);
//        Assert.isTrue(count > 0);
//    }
//
//    @Test
//    public void markDeleteById(){
//        Integer count = adminMenuMapper.markDeleteById(AdminMenu.class,53);
//        Assert.isTrue(count > 0);
//        CriteriaQuery query = CriteriaQuery.create();
//        adminMenuMapper.findsByCriteria(AdminMenu.class,query);
//    }
//
//    @Test
//    public void markDeleteByIdDiy(){
//        Integer count = adminMenuMapper.markDeleteByIdDiy(AdminMenu.class,52,"is_delete");
//        Assert.isTrue(count > 0);
//    }

}
