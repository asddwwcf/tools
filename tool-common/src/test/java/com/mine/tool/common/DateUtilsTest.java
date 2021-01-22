package com.mine.tool.common;

import com.mine.tool.common.util.DateUtils;
import com.mine.tool.common.util.LogUtils;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.junit.Test;

/**
 * 功能 :
 */
@Slf4j
public class DateUtilsTest {

    @Test
    public void date() {
        LogUtils.info(log, DateUtils.parse("2018-12-28", DateUtils.Parttern.FORMAT_YYMMDD_MID).getWeekOfWeekyear());
        LogUtils.info(log,DateUtils.parse("2018-12-28",DateUtils.Parttern.FORMAT_YYMMDD_MID).getWeekyear());
        LogUtils.info(log,DateUtils.create().withTimeAtStartOfDay().getMillis());
        LogUtils.info(log,DateUtils.format(DateUtils.create().withTimeAtStartOfDay(),DateUtils.Parttern.FORMAT_YYMMDDHMS_MID));
        LogUtils.info(log,DateUtils.format(DateUtils.create().withDayOfWeek(7),DateUtils.Parttern.FORMAT_YYMMDDHMS_MID));
        LogUtils.info(log,DateUtils.format(DateUtils.create().dayOfWeek().withMinimumValue(),DateUtils.Parttern.FORMAT_YYMMDDHMS_MID));
        LogUtils.info(log,DateUtils.compare(DateUtils.create(),DateUtils.addDays(DateUtils.create(),-1)));
        LogUtils.info(log,DateUtils.between(DateUtils.create(),DateUtils.addDays(DateUtils.create(),0),DateUtils.addDays(DateUtils.create(),1)));
        LogUtils.info(log,DateUtils.format(DateUtils.create(),DateUtils.Parttern.FORMAT_YYMMDDHMSS_MID));
        LogUtils.info(log,DateUtils.format(DateUtils.getDayOfBegin(DateUtils.create()),DateUtils.Parttern.FORMAT_YYMMDDHMSS_MID));
        LogUtils.info(log,DateUtils.format(DateUtils.getSecondOfBegin(DateUtils.create()),DateUtils.Parttern.FORMAT_YYMMDDHMSS_MID));
        LogUtils.info(log,DateUtils.format(new DateTime(1500480000L), DateUtils.Parttern.FORMAT_YYMMDD_NON));
    }
}
