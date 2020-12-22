package com.mine.tool.spring.example;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.retry.annotation.EnableRetry;

@Configuration
@EnableRetry
@EnableAspectJAutoProxy(proxyTargetClass=true)
public class RetryServiceMain {
    @Bean
    public RetryService retryService(){
        return new RetryService();
    }
    public static void main(String[] args) throws Exception{
        final AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext(RetryServiceMain.class);
        final RetryService retryService = applicationContext.getBean(RetryService.class);
        retryService.retryMethod("111");
    }
}