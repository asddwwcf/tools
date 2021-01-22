package com.mine.tool.cache.redis.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;

/**
 * 功能 :
 * 全局获取spring容器对象
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SpringBeanUtil {

    private static ApplicationContext applicationContext;

    public static void setApplicationContext(ApplicationContext applicationContext){
        SpringBeanUtil.applicationContext = applicationContext;
    }

    /**获取applicationContext**/
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**通过name获取 Bean.**/
    public static Object getBean(String name){
        try {
            return getApplicationContext().getBean(name);
        } catch (BeansException e) {
            log.error("{}",e.getMessage(),e);
        }
        return null;
    }

    /**通过class获取Bean.**/
    public static <T> T getBean(Class<T> clazz){
        try {
            return getApplicationContext().getBean(clazz);
        } catch (BeansException e) {
            log.error("{}",e.getMessage(),e);
        }
        return null;
    }

    /**通过name,以及Clazz返回指定的Bean**/
    public static <T> T getBean(String name,Class<T> clazz){
        try {
            return getApplicationContext().getBean(name, clazz);
        } catch (BeansException e) {
            log.error("{}",e.getMessage(),e);
        }
        return null;
    }
}
