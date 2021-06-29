package com.mine.tool.common;

import com.mine.tool.common.util.SpringBeanUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

/**
 * 功能 :
 */
@SpringBootApplication
public class CommonApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(CommonApplication.class, args);
        SpringBeanUtil.setApplicationContext(context);
    }

}
