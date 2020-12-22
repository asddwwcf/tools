package com.mine.tool.orm.mybatis.core.annotation;

import java.lang.annotation.*;

/**
 * 功能 :
 * 忽略指定字段
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Documented
@Inherited
public @interface Transient {
}