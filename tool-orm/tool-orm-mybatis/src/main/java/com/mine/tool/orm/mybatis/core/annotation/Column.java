package com.mine.tool.orm.mybatis.core.annotation;

import java.lang.annotation.*;

/**
 * 功能 :
 * 数据库表,普通字段
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Documented
@Inherited
public @interface Column {
    String name() default "";
    String value();
}
