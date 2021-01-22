package com.mine.tool.common.string;

import com.mine.tool.common.util.DateUtils;
import com.mine.tool.common.util.regex.RegexUtils;
import org.junit.Assert;
import org.junit.Test;

/**
 * 功能 :
 */
public class RegexUtilsTest {

    @Test
    public void should_return_ok_when_match_date(){
        Assert.assertTrue(RegexUtils.validDate("202006", DateUtils.Parttern.FORMAT_YYWW_NON));
        Assert.assertTrue(RegexUtils.validDate("202012", DateUtils.Parttern.FORMAT_YYMM_NON));
        Assert.assertTrue(RegexUtils.validDate("20201130", DateUtils.Parttern.FORMAT_YYMMDD_NON));
        Assert.assertTrue(RegexUtils.validDate("2020113012", DateUtils.Parttern.FORMAT_YYMMDDH_NON));
        Assert.assertTrue(RegexUtils.validDate("202011301210", DateUtils.Parttern.FORMAT_YYMMDDHM_NON));
        Assert.assertTrue(RegexUtils.validDate("20201130121010", DateUtils.Parttern.FORMAT_YYMMDDHMS_NON));
        Assert.assertTrue(RegexUtils.validDate("20201130121010999", DateUtils.Parttern.FORMAT_YYMMDDHMSS_NON));

        Assert.assertTrue(RegexUtils.validDate("23:59", DateUtils.Parttern.FORMAT_HM_MID));
        Assert.assertTrue(RegexUtils.validDate("2020-06", DateUtils.Parttern.FORMAT_YYWW_MID));
        Assert.assertTrue(RegexUtils.validDate("2020-12", DateUtils.Parttern.FORMAT_YYMM_MID));
        Assert.assertTrue(RegexUtils.validDate("2020-11-30", DateUtils.Parttern.FORMAT_YYMMDD_MID));
        Assert.assertTrue(RegexUtils.validDate("2020-11-30 12", DateUtils.Parttern.FORMAT_YYMMDDH_MID));
        Assert.assertTrue(RegexUtils.validDate("2020-11-30 12:10", DateUtils.Parttern.FORMAT_YYMMDDHM_MID));
        Assert.assertTrue(RegexUtils.validDate("2020-11-30 12:10:10", DateUtils.Parttern.FORMAT_YYMMDDHMS_MID));
        Assert.assertTrue(RegexUtils.validDate("2020-11-30 12:10:10:999", DateUtils.Parttern.FORMAT_YYMMDDHMSS_MID));

        Assert.assertTrue(RegexUtils.validDate("2020/06", DateUtils.Parttern.FORMAT_YYWW_SLASH));
        Assert.assertTrue(RegexUtils.validDate("2020/12", DateUtils.Parttern.FORMAT_YYMM_SLASH));
        Assert.assertTrue(RegexUtils.validDate("2020/11/30", DateUtils.Parttern.FORMAT_YYMMDD_SLASH));
        Assert.assertTrue(RegexUtils.validDate("2020/11/30 12", DateUtils.Parttern.FORMAT_YYMMDDH_SLASH));
        Assert.assertTrue(RegexUtils.validDate("2020/11/30 12:10", DateUtils.Parttern.FORMAT_YYMMDDHM_SLASH));
        Assert.assertTrue(RegexUtils.validDate("2020/11/30 12:10:10", DateUtils.Parttern.FORMAT_YYMMDDHMS_SLASH));
        Assert.assertTrue(RegexUtils.validDate("2020/11/30 12:10:10:999", DateUtils.Parttern.FORMAT_YYMMDDHMSS_SLASH));

        Assert.assertTrue(RegexUtils.validDate("2020年06周", DateUtils.Parttern.FORMAT_YYWW_ZH));
        Assert.assertTrue(RegexUtils.validDate("2020年12月", DateUtils.Parttern.FORMAT_YYMM_ZH));
        Assert.assertTrue(RegexUtils.validDate("2020年11月30日", DateUtils.Parttern.FORMAT_YYMMDD_ZH));
        Assert.assertTrue(RegexUtils.validDate("2020年11月30日 12点", DateUtils.Parttern.FORMAT_YYMMDDH_ZH));
        Assert.assertTrue(RegexUtils.validDate("2020年11月30日 12点10分", DateUtils.Parttern.FORMAT_YYMMDDHM_ZH));
        Assert.assertTrue(RegexUtils.validDate("2020年11月30日 12点10分10秒", DateUtils.Parttern.FORMAT_YYMMDDHMS_ZH));
        Assert.assertTrue(RegexUtils.validDate("2020年11月30日 12点10分10秒999毫秒", DateUtils.Parttern.FORMAT_YYMMDDHMSS_ZH));
    }
}
