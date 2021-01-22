package com.mine.tool.common.arithmetic;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * 功能 :
 * base64字符串转换
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Base64 {

    public static String encode(byte[] key) {
        return new String(java.util.Base64.getEncoder().encode(key), StandardCharsets.UTF_8);
    }

    public static byte[] decode(String key) {
        return java.util.Base64.getDecoder().decode(key);
    }

    private static char[] var2 = new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    /**转16进制**/
    public static String byte2Hex(byte[] bytes, int offset, int length){
        if(Objects.isNull(bytes)){ return null; }
        int size = length;
        if(bytes.length < length){
            size = bytes.length;
        }
        StringBuilder result = new StringBuilder();
        for (int i = offset; i < size; i++) {
            byte item = bytes[i];
            int var3 = (item & 240) >> 4;
            int var4 = item & 15;
            result.append(var2[var3]);
            result.append(var2[var4]);
        }
        return result.toString();
    }
}
