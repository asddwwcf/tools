package com.mine.tool.orm.mybatis.core.annotation;

import java.lang.annotation.*;

/**
 * 功能 :
 * 数据库表,普通字段
 * @author : Bruce(刘正航) 上午11:43 2018/4/10
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Documented
@Inherited
public @interface Column {
    String name() default "";
    String value();
}
