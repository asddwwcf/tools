package com.mine.tool.orm.mybatis.generator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 功能 :
 *
 */
@SpringBootApplication(scanBasePackages = {"com.mine.tool"})
public class MybatisGeneratorApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisGeneratorApplication.class, args);
    }

}
