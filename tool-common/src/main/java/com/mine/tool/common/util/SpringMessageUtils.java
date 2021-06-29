package com.mine.tool.common.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.support.ResourceBundleMessageSource;

import java.util.Locale;

/**
 * 功能 :
 * 错误提示国际化
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SpringMessageUtils {

    private static ResourceBundleMessageSource resourceBundleMessageSource;

    static{
        resourceBundleMessageSource = SpringBeanUtil.getBean(ResourceBundleMessageSource.class);
    }

    /**默认获取中文的错误信息**/
    public static final String getMessage(String resourceKey, Object... args) {
        if (resourceBundleMessageSource == null) {
            return null;
        }
        try {
            return resourceBundleMessageSource.getMessage(resourceKey, args, null);
        } catch (NoSuchMessageException e) {
            return resourceKey;
        }
    }

    /**根据传入的locale获取对应语言的错误信息**/
    public static final String getMessage(String resourceKey, Locale locale, Object... args) {
        if (resourceBundleMessageSource == null) {
            return null;
        }
        try {
            return resourceBundleMessageSource.getMessage(resourceKey, args, locale);
        } catch (NoSuchMessageException e) {
            return resourceKey;
        }
    }
}
