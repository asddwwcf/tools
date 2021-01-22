package com.mine.tool.common.util.random;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * 随机字符串工具类
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Random {

    private static final String NUM_DICTIONARY = "0123456789";
    private static final String CHAR_DICTIONARY = "0123456789abcdefghijklmnopqrstuvwxyz";

    /**随机字符串,可传入指定长度**/
    public static String character(int n) {
        return randomByDictionary(n, CHAR_DICTIONARY);
    }

    /**随机数字,可传入指定长度**/
    public static String number(int n) {
        return randomByDictionary(n, NUM_DICTIONARY);
    }

    /**根据不同的词典,随机不同的内容**/
    private static String randomByDictionary(int n, String numDictionary) {
        int dictSpace = numDictionary.length();
        StringBuilder buffer = new StringBuilder();
        for (int i = 0; i < n; i++) {
            char selectChar = numDictionary.charAt((int) ((Math.random() * 1000000) % dictSpace));
            buffer.append(selectChar);
        }
        return buffer.toString();
    }

}
