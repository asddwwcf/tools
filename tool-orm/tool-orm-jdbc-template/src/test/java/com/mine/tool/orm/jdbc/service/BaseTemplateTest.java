package com.mine.tool.orm.jdbc.service;

import com.mine.tool.orm.jdbc.JdbcTemplateApplication;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = JdbcTemplateApplication.class)
public class BaseTemplateTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**抛出异常-并且不回滚了出错记录之前的所有SQL**/
    @Test(expected = DuplicateKeyException.class)
    public void should_throw_exception_when_execute_insert(){
        String[] sqls = new String[]{
                "insert into data_temp(id,name) values(1,'哈哈');",
                "insert into data_temp(id,name) values(1,'哈哈');"
        };
        jdbcTemplate.batchUpdate(sqls);
    }

    /**抛出异常-并且回滚了出错记录之前的所有SQL**/
    @Test(expected = DuplicateKeyException.class)
    @Transactional
    public void should_throw_exception_and_rollback_when_execute_insert(){
        String[] sqls = new String[]{
                "insert into data_temp(id,name) values(1,'哈哈');",
                "insert into data_temp(id,name) values(1,'哈哈');"
        };
        jdbcTemplate.batchUpdate(sqls);
    }

    /**抛出异常-并且回滚了出错记录之前的所有SQL**/
    @Test(expected = DuplicateKeyException.class)
    public void should_throw_exception2_and_rollback_when_execute_insert(){
        String[] sqls = new String[]{
                "insert into data_temp(id,name) values(1,'哈哈'),(1,'哈哈');"
        };
        jdbcTemplate.batchUpdate(sqls);
    }
}
