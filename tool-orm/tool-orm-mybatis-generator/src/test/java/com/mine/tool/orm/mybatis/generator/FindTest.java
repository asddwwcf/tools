package com.mine.tool.orm.mybatis.generator;

import com.mine.tool.common.util.SpringBeanUtils;
import com.mine.tool.common.util.string.JsonUtils;
import com.mine.tool.logger.core.annotation.EnableSQLFormat;
import com.mine.tool.orm.mybatis.core.annotation.EnableReturnExecutableSQL;
import com.mine.tool.orm.mybatis.generator.domain.*;
import com.mine.tool.orm.mybatis.generator.mapper.AdminMenuMapper;
import com.mine.tool.orm.mybatis.generator.service.AdminMenuService;
import com.mine.tool.orm.mybatis.generator.service.AdminRoleService;
import com.mine.tool.orm.mybatis.generator.service.AdminUserService;
import com.github.pagehelper.PageInfo;
import com.mine.tool.orm.mybatis.generator.domain.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Lists;
import org.hamcrest.core.Is;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.generator.api.FullyQualifiedTable;
import org.mybatis.generator.config.DomainObjectRenamingRule;
import org.mybatis.generator.internal.util.JavaBeansUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.CollectionUtils;

import java.util.List;

import static org.junit.Assert.assertThat;

/**
 * 功能 :
 *
 */
@Slf4j
@EnableSQLFormat
@EnableReturnExecutableSQL
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MybatisGeneratorApplication.class)
public class FindTest extends AbstractJUnit4SpringContextTests {

    @Autowired
    private AdminMenuMapper adminMenuMapper;

    @Autowired
    private AdminMenuService adminMenuService;

    @Autowired
    private AdminRoleService adminRoleService;

    @Before
    public void init(){
        SpringBeanUtils.setApplicationContext(applicationContext);
    }

    @Test
    public void findById2(){
        AdminMenuExample example = new AdminMenuExample().createCriteria()
                .andIdEqualTo(1)
                .example();
        AdminMenu adminMenu = adminMenuMapper.findByExample(example);
        System.out.println(JsonUtils.objToStr(adminMenu));
    }

    @Test
    public void findsByExample(){
        AdminMenuExample example = new AdminMenuExample().createCriteria()
                .andMenuNameLike("%测试%")
                .andHrefLike("http")
                .andIdNotBetween(1,100)
                .andLevelEqualTo(1)
                .example();
        example.or().andMenuNameLike("%测试%")
                .andHrefLike("http")
                .andIdNotBetween(1,100)
                .andLevelEqualTo(1)
                .example();
        StringBuilder condition = new StringBuilder();
        for (AdminMenuExample.Criteria criteria : example.getOredCriteria()){
            List<AdminMenuExample.Criterion> criterions = criteria.getAllCriteria();
            if(CollectionUtils.isEmpty(criterions)){ continue; }
            int index = example.getOredCriteria().indexOf(criteria);
            if(index > 0){
                condition.append(" or (");
            }else{
                condition.append("(");
            }
            for (AdminMenuExample.Criterion criterion : criterions){
                if(criterions.indexOf(criterion) > 0){
                    condition.append(" and ");
                }
                if(criterion.isSingleValue() && !criterion.isNoValue()){
                    condition.append(criterion.getCondition())
                            .append(" ")
                            .append(transfer(criterion.getValue()));
                }
                if(criterion.isBetweenValue()){
                    condition.append(criterion.getCondition())
                            .append(" ")
                            .append(criterion.getValue())
                            .append(" and ")
                            .append(criterion.getSecondValue());
                }
                if(criterion.isListValue()){
                    condition.append(criterion.getCondition())
                            .append("(")
                            .append(StringUtils.join(criterion.getValue(), ","))
                            .append(")");
                }
            }
            condition.append(")");
        }
        System.out.println(condition);
    }

    private Object transfer(Object value) {
        if(value instanceof String){
            return "\""+value+"\"";
        }
        if(value instanceof List){
            List line = (List) value;
            Object first = line.get(0);
            if(first instanceof String){
                return StringUtils.join(value,"\",\"");
            }else{
                return StringUtils.join(value,",");
            }
        }
        return value;
    }

    @Test
    public void findByExample2(){
        AdminMenuExample example = new AdminMenuExample().createCriteria()
                .andMenuNameLike("%管理%")
                .example();
        example.or().andMenuNameLike("")
                .andLevelIn(Lists.newArrayList())
                .example();
        List<AdminMenu> adminMenus = adminMenuMapper.findsByExample(example);
        System.out.println(JsonUtils.objToStr(adminMenus));
    }

    @Test
    public void findByExample(){
        AdminMenuExample menuExample = new AdminMenuExample();
        long total = adminMenuService.countByExample(menuExample);
        System.out.println(total);
        PageInfo<AdminMenu> pageInfo = adminMenuService.pageInfoByExample(menuExample, 2, 10);
        System.out.println(JsonUtils.objToStr(pageInfo));
        List<AdminMenu> adminMenus = adminMenuService.limitByExample(menuExample, 5);
        System.out.println(JsonUtils.objToStr(adminMenus));

        AdminRoleExample roleExample = new AdminRoleExample();
        roleExample.setOrderByClause(AdminRole.Fields.ROLE_ID.column() + "desc");
        List<AdminRole> adminRoles = adminRoleService.limitByExample(roleExample, 10);
        System.out.println(JsonUtils.objToStr(adminRoles));

        List<AdminMenu> nextMenus = adminMenuService.nextByExample(menuExample, 5, 5);
        System.out.println(JsonUtils.objToStr(nextMenus));
    }

    @Test
    public void testDomainObjectName(){
        String tableName = "perequipmentrelation";
        String domainName = JavaBeansUtil.getCamelCaseString(tableName,true);
        String newDomainName = JavaBeansUtil.getCamelCaseString(domainName,true);
        System.out.println(domainName);
        System.out.println(newDomainName);
    }

    @Test
    public void testNormalCase() {
        FullyQualifiedTable fqt = new FullyQualifiedTable(null, "myschema", "mytable", null, null, false, null, null, null, false, null, null);

        assertThat(fqt.getDomainObjectName(), Is.is("Mytable"));
    }

    @Test
    public void testNormalCaseWithPrefix() {
        FullyQualifiedTable fqt = new FullyQualifiedTable(null, "myschema", "sys_mytable", null, null, false, null, null, null, false, null, null);

        assertThat(fqt.getDomainObjectName(), Is.is("SysMytable"));
    }

    @Test
    public void testRenamingRule() {
        DomainObjectRenamingRule renamingRule = new DomainObjectRenamingRule();
        renamingRule.setSearchString("^Sys");
        renamingRule.setReplaceString("");
        FullyQualifiedTable fqt = new FullyQualifiedTable(null, "myschema", "sys_mytable", null, null, false, null, null, null, false, renamingRule, null);

        assertThat(fqt.getDomainObjectName(), Is.is("Mytable"));
    }

    @Test
    public void testRenamingRuleNoUnderscore() {
        DomainObjectRenamingRule renamingRule = new DomainObjectRenamingRule();
        renamingRule.setSearchString("^Sys");
        renamingRule.setReplaceString("");
        FullyQualifiedTable fqt = new FullyQualifiedTable(null, "myschema", "sysmytable", null, null, false, null, null, null, false, renamingRule, null);

        assertThat(fqt.getDomainObjectName(), Is.is("Mytable"));
    }

    @Test
    public void testRenamingRuleWithUnderscore() {
        DomainObjectRenamingRule renamingRule = new DomainObjectRenamingRule();
        renamingRule.setSearchString("^Sys");
        renamingRule.setReplaceString("");
        FullyQualifiedTable fqt = new FullyQualifiedTable(null, "myschema", "sys_mytable_nice", null, null, false, null, null, null, false, renamingRule, null);

        assertThat(fqt.getDomainObjectName(), Is.is("MytableNice"));
    }

    @Autowired
    private AdminUserService adminUserService;

    @Test
    public void testLikeListValue(){
        AdminUserExample example = new AdminUserExample().createCriteria()
                .andUNameLike(Lists.newArrayList("测试","管理员","客户"))
                .example();
        List<AdminUser> adminUsers = adminUserService.findsByExample(example);
        System.out.println(JsonUtils.objToStr(adminUsers));
    }

    @Test
    public void testLikeListValuePrefix(){
        AdminUserExample example = new AdminUserExample().createCriteria()
                .andUNameLikePrefix(Lists.newArrayList("测试","管理员","客户"))
                .example();
        List<AdminUser> adminUsers = adminUserService.findsByExample(example);
        System.out.println(JsonUtils.objToStr(adminUsers));
    }

    @Test
    public void testLikeListValueSuffix(){
        AdminUserExample example = new AdminUserExample().createCriteria()
                .andUNameLikeSuffix(Lists.newArrayList("测试","管理员","客户"))
                .example();
        List<AdminUser> adminUsers = adminUserService.findsByExample(example);
        System.out.println(JsonUtils.objToStr(adminUsers));
    }

    @Test
    public void testLikeValuePrefix(){
        AdminUserExample example = new AdminUserExample().createCriteria()
                .andUNameLikePrefix("客户")
                .example();
        List<AdminUser> adminUsers = adminUserService.findsByExample(example);
        System.out.println(JsonUtils.objToStr(adminUsers));
    }

    @Test
    public void testLikeValue(){
        AdminUserExample example = new AdminUserExample().createCriteria()
                .andUNameLike("客户")
                .example();
        List<AdminUser> adminUsers = adminUserService.findsByExample(example);
        System.out.println(JsonUtils.objToStr(adminUsers));
    }

    @Test
    public void testLikeValueSuffix(){
        AdminUserExample example = new AdminUserExample().createCriteria()
                .andUNameLikeSuffix("客户")
                .example();
        List<AdminUser> adminUsers = adminUserService.findsByExample(example);
        System.out.println(JsonUtils.objToStr(adminUsers));
    }

    @Test
    public void executableSqlTest(){
        String s = adminMenuMapper.fetchAllMenu();
        System.out.println(s);
    }

    @Test
    public void sqlFormatTest(){
        adminMenuMapper.selectMenusLike("测试");
//        adminMenuMapper.selectMenusLike2("测试");
    }

}
