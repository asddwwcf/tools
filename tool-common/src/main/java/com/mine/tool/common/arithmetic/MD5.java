package com.mine.tool.common.arithmetic;

import com.google.common.hash.Hashing;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;

/**
 * 功能 :
 * 用guava实现的md5工具类
 *
 * @Deprecated 因为MD5安全性太低, 不建议使用, 建议使用AES对称加密
 */
@Slf4j
public class MD5 {

    public enum Case {
        //
        LOWER, UPPER;
    }

    public static String encode(String src) {
        return encode(src, Case.LOWER);
    }

    public static String encode(String src, Case cases) {
        String result = Hashing.md5().hashString(src, StandardCharsets.UTF_8).toString();
        switch (cases) {
            case LOWER:
                result = result.toLowerCase();
                break;
            case UPPER:
                result = result.toUpperCase();
                break;
            default:
                result = result.toUpperCase();
        }
        return result;
    }

}
