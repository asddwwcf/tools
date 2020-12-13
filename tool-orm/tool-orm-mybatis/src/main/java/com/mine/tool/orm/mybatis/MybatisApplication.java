package com.mine.tool.orm.mybatis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 功能 :
 *
 * @author : Bruce(刘正航) 5:42 PM 2018/12/18
 */
@SpringBootApplication(scanBasePackages = {"com.bruce.tool"})
public class MybatisApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisApplication.class, args);
    }

}
