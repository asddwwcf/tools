package com.mine.shardingjdbc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.mine.shardingjdbc.mapper")
@SpringBootApplication
public class SpringBootShardingJdbcApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootShardingJdbcApplication.class, args);
    }

}
