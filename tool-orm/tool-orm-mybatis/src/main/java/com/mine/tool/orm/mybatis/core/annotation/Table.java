package com.mine.tool.orm.mybatis.core.annotation;

import java.lang.annotation.*;

/**
 * 功能 :
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
@Documented
public @interface Table {
    String value();
}