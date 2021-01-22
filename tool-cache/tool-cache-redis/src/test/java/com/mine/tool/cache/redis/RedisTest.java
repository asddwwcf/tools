package com.mine.tool.cache.redis;

import com.mine.tool.cache.redis.util.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RedisApplication.class)
@ContextConfiguration(classes = {RedisApplication.class})
public class RedisTest {

    @Autowired
    private RedisUtil redis;

    @Test
    public void get(){
        String s = redis.get("");
        System.out.println(s);
    }
}
