package com.mine.tool.common.util.regex;

import com.mine.tool.common.util.DateUtils;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.lang.NonNull;

import java.util.List;

/**
 * 正则表达式
 * 功能:
 * 1.valid系列,判断是否匹配.
 * 2.contains系列,判断是否包含.
 * 3.matchs系列,返回所有匹配的子串.
 * 4.matchCount系列,返回匹配的次数.
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RegexUtils {

    /**非法字符校验**/
    public static boolean validLegalChar(String source){
        return RegexCoreUtils.doMatch(source,Regex.LEGAL_CHAR.getValue());
    }

    /**校验整数**/
    public static boolean validInteger(String source){
        return RegexCoreUtils.doMatch(source,Regex.INTEGER.getValue());
    }

    /**校验电话号码**/
    public static boolean validPhone(String source){
        return RegexCoreUtils.doMatch(source,Regex.PHONE.getValue());
    }

    /**校验用户名**/
    public static boolean validUserName(String source){
        return RegexCoreUtils.doMatch(source,Regex.USER_NAME_ONE.getValue());
    }

    /**校验字母数字**/
    public static boolean validAlphaAndNumber(String source){
        return RegexCoreUtils.doMatch(source,Regex.ALPHA_NUM.getValue());
    }

    /**日期校验**/
    public static boolean validDate(String source, DateUtils.Parttern parttern){
        return RegexCoreUtils.doMatch(source,parttern.getRegex());
    }

    /**日期校验**/
    public static boolean validDate(@NonNull String source){
        if( RegexCoreUtils.doMatch(source,DateUtils.Parttern.FORMAT_YYMM_MID.getRegex()) ){
            return true;
        }
        if( RegexCoreUtils.doMatch(source,DateUtils.Parttern.FORMAT_YYMMDD_MID.getRegex()) ){
            return true;
        }
        if( RegexCoreUtils.doMatch(source,DateUtils.Parttern.FORMAT_YYMMDDH_MID.getRegex()) ){
            return true;
        }
        if( RegexCoreUtils.doMatch(source,DateUtils.Parttern.FORMAT_YYMMDDHM_MID.getRegex()) ){
            return true;
        }
        if( RegexCoreUtils.doMatch(source,DateUtils.Parttern.FORMAT_YYMMDDHMS_MID.getRegex()) ){
            return true;
        }
        if( RegexCoreUtils.doMatch(source,DateUtils.Parttern.FORMAT_YYMMDDHMSS_MID.getRegex()) ){
            return true;
        }
        return false;
    }

    /**日期校验**/
    public static boolean validTimeStamp(String source){
        if( validInteger(source) && source.length() == 10 ){
            return true;
        }
        if( validInteger(source) && source.length() == 13 ){
            return true;
        }
        return false;
    }

    /**是否包含魔技表情**/
    public static boolean containMoji(String source){
        return RegexCoreUtils.doMatch(source,Regex.MOJI_CHAR.getValue());
    }

    /**是否包含中文的校验**/
    public static boolean containChinese(String source) {
        return RegexCoreUtils.doMatch(source,Regex.HAS_CHINESE.getValue());
    }

    /**是否包含空格**/
    public static boolean containWhiteSpace(String source) {
        return RegexCoreUtils.doMatch(source,Regex.HAS_WHITESPACE.getValue());
    }

    /**统计问号的匹配次数**/
    public static Integer matchQuestionCount(String source){
        return RegexCoreUtils.matchCount(source,Regex.QUESTION_CHAR.getValue());
    }

    /**统计字符在文本中出现的次数**/
    public static List<String> matchsReturnChar(String source) {
        return RegexCoreUtils.findMatchs(source,Regex.RETURN_CHAR.getValue());
    }
}
