package com.mine.tool.orm.mybatis.core.annotation;

import com.mine.tool.orm.mybatis.interceptor.SqlJointInterceptor;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 功能 :
 * @author : Bruce(刘正航) 4:08 下午 2020/1/8
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import({SqlJointInterceptor.class})
public @interface EnableReturnExecutableSQL {
}
