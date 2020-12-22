package com.mine.tool.orm.mybatis.core.annotation;

import java.lang.annotation.*;

/**
 * 功能 :
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface ReturnExecutableSQL {
}