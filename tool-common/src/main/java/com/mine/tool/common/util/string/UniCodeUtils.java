package com.mine.tool.common.util.string;

import com.mine.tool.common.util.regex.Regex;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.nio.charset.Charset;
import java.util.regex.Matcher;

/**
 * 功能 : 
 *
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UniCodeUtils {

    /**unicode-->String**/
    public static String toString(String unicode){
        Matcher matcher = Regex.UNICODE_CHAR.getValue().matcher(unicode);
        char ch;
        while (matcher.find()) {
            //group 6728
            String group = matcher.group(2);
            //ch:'木' 26408
            ch = (char) Integer.parseInt(group, 16);
            //group1 \u6728
            String group1 = matcher.group(1);
            unicode = unicode.replace(group1, ch + "");
        }
        return unicode;
    }

    /**String-->unicode**/
    public static String fromString(String string){
        StringBuilder out = new StringBuilder();
        //直接获取字符串的unicode二进制
        byte[] bytes = string.getBytes(Charset.forName("unicode"));
        //然后将其byte转换成对应的16进制表示即可
        for (int i = 0; i < bytes.length - 1; i += 2) {
            out.append("\\u");
            String str = Integer.toHexString(bytes[i + 1] & 0xff);
            for (int j = str.length(); j < 2; j++) {
                out.append("0");
            }
            String str1 = Integer.toHexString(bytes[i] & 0xff);
            out.append(str1);
            out.append(str);
        }
        return out.toString();
    }
}
