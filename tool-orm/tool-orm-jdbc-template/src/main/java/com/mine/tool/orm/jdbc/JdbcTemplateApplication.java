package com.mine.tool.orm.jdbc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 功能 :
 *
 */
@SpringBootApplication(scanBasePackages = {"com.mine.tool"})
public class JdbcTemplateApplication {

    public static void main(String[] args) {
        SpringApplication.run(JdbcTemplateApplication.class, args);
    }

}
