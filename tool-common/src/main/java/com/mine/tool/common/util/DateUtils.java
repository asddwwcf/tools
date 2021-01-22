package com.mine.tool.common.util;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.PeriodType;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Date;

/**
 * 功能点:
 * 基本功能:获取当前时间
 * 一.日期的格式转换
 * 1.日期对象转字符串
 * 2.日期对象转时间戳
 * 3.时间戳转日期对象
 * 4.时间戳转字符串
 * 二.日期的加减
 * 1.当前日期加减N天
 * 2.当前日期加减N周
 * 3.当前日期加减N月
 * 4.当前日期加减N年
 * 三.日期坐标转换
 * 1.指定日期是本周的周几
 * 2.指定日期是本年的第几周
 * 四.日期单位的特殊点
 * 1.根据日期,获取当前的起止时间戳
 * 2.根据日期,获取本周的第一天的0点时间戳
 * 3.根据日期,获取本周的最后一天的23:59:59的时间戳
 * 4.根据日期,获取本月的第一天的0点时间戳
 * 5.根据日期,获取本月的最后一天的23:59:59的时间戳
 * 6.根据日期,获取本年的第一天的0点时间戳
 * 7.根据日期,获取本年的最后一天的23:59:59的时间戳
 * 五.日期大小的比较
 * 1.两个日期,比较返回大小
 * 2.两个日期,判断日期是否在2个日期之间
 * 3.计算两个日期之间的差额(按年算)
 * 4.计算两个日期之间的差额(按月算)
 * 5.计算两个日期之间的差额(按天算)
 * 6.计算两个日期之间的差额(按小时算)
 * 7.计算两个日期之间的差额(按分钟算)
 * 8.计算两个日期之间的差额(按秒算)
 * 9.计算两个日期之间的差额(按毫秒算)
 * 六.是否同一天,同一周,同一月,同一年
 * 1.是否同一个小时
 * 2.是否同一天
 * 3.是否同一周
 * 4.是否同一月
 * 5.是否同一年
 * 七.日期截断
 * 1.获取指定日期的天的毫秒数
 * 2.获取指定日期的小时的毫秒数
 * 3.获取指定日期的分的毫秒数
 * 4.获取指定日期的秒的毫秒数
 * 八.获取时间段
 * 1.获取起止日期之间的所有日期列表
 * 2.获取起止日期之间的所有周列表
 * 3.获取起止日期之间的所有月列表
 * <p>
 */
public class DateUtils {

    /**
     * 日期格式枚举类
     */
    public enum Parttern {
        /**
         * 特殊格式
         **/
        FORMAT_HM_MID("HH:mm", "^(([01][0-9])|([2][0-3])):[0-5][0-9]$"),
        FORMAT_HMS_MID("HH:mm:ss", "^(([01][0-9])|([2][0-3])):[0-5][0-9]:[0-5][0-9]$"),
        FORMAT_HMSS_MID("HH:mm:ss:SSS", "^(([01][0-9])|([2][0-3])):[0-5][0-9]:[0-5][0-9]:[0-9][0-9][0-9]$"),

        /**
         * 中杠分隔符
         **/
        FORMAT_YYMM_MID("yyyy-MM", "^[1-5]([0-9]){3}[-](([0][1-9])|([1][012]))$"),
        FORMAT_YYWW_MID("yyyy-ww", "^[1-5]([0-9]){3}[-](([0][1-7]))$"),
        FORMAT_YYMMDD_MID("yyyy-MM-dd", "^[1-5]([0-9]){3}[-](([0][1-9])|([1][012]))[-](([0][1-9])|([12][0-9])|([3][01]))$"),
        FORMAT_YYMMDDH_MID("yyyy-MM-dd HH", "^[1-5]([0-9]){3}[-](([0][1-9])|([1][012]))[-](([0][1-9])|([12][0-9])|([3][01]))[ ](([01][0-9])|([2][0-3]))$"),
        FORMAT_YYMMDDHM_MID("yyyy-MM-dd HH:mm", "^[1-5]([0-9]){3}[-](([0][1-9])|([1][012]))[-](([0][1-9])|([12][0-9])|([3][01]))[ ](([01][0-9])|([2][0-3])):[0-5][0-9]$"),
        FORMAT_YYMMDDHMS_MID("yyyy-MM-dd HH:mm:ss", "^[1-5]([0-9]){3}[-](([0][1-9])|([1][012]))[-](([0][1-9])|([12][0-9])|([3][01]))[ ](([01][0-9])|([2][0-3])):[0-5][0-9]:[0-5][0-9]$"),
        FORMAT_YYMMDDHMSS_MID("yyyy-MM-dd HH:mm:ss:SSS", "^[1-5]([0-9]){3}[-](([0][1-9])|([1][012]))[-](([0][1-9])|([12][0-9])|([3][01]))[ ](([01][0-9])|([2][0-3])):[0-5][0-9]:[0-5][0-9]:[0-9][0-9][0-9]$"),

        /**
         * 无隔符
         **/
        FORMAT_YYMM_NON("yyyyMM", "^[1-5]([0-9]){3}(([0][1-9])|([1][012]))$"),
        FORMAT_YYWW_NON("yyyyww", "^[1-5]([0-9]){3}(([0][1-7]))$"),
        FORMAT_YYMMDD_NON("yyyyMMdd", "^[1-5]([0-9]){3}(([0][1-9])|([1][012]))(([0][1-9])|([12][0-9])|([3][01]))$"),
        FORMAT_YYMMDDH_NON("yyyyMMddHH", "^[1-5]([0-9]){3}(([0][1-9])|([1][012]))(([0][1-9])|([12][0-9])|([3][01]))(([01][0-9])|([2][0-3]))$"),
        FORMAT_YYMMDDHM_NON("yyyyMMddHHmm", "^[1-5]([0-9]){3}(([0][1-9])|([1][012]))(([0][1-9])|([12][0-9])|([3][01]))(([01][0-9])|([2][0-3]))[0-5][0-9]$"),
        FORMAT_YYMMDDHMS_NON("yyyyMMddHHmmss", "^[1-5]([0-9]){3}(([0][1-9])|([1][012]))(([0][1-9])|([12][0-9])|([3][01]))(([01][0-9])|([2][0-3]))[0-5][0-9][0-5][0-9]$"),
        FORMAT_YYMMDDHMSS_NON("yyyyMMddHHmmssSSS", "^[1-5]([0-9]){3}(([0][1-9])|([1][012]))(([0][1-9])|([12][0-9])|([3][01]))(([01][0-9])|([2][0-3]))[0-5][0-9][0-5][0-9][0-9][0-9][0-9]$"),

        /**
         * 斜杠分隔符
         **/
        FORMAT_YYMM_SLASH("yyyy/MM", "^[1-5]([0-9]){3}[\\/](([0][1-9])|([1][012]))$"),
        FORMAT_YYWW_SLASH("yyyy/ww", "^[1-5]([0-9]){3}[\\/](([0][1-7]))$"),
        FORMAT_YYMMDD_SLASH("yyyy/MM/dd", "^[1-5]([0-9]){3}[\\/](([0][1-9])|([1][012]))[\\/](([0][1-9])|([12][0-9])|([3][01]))$"),
        FORMAT_YYMMDDH_SLASH("yyyy/MM/dd HH", "^[1-5]([0-9]){3}[\\/](([0][1-9])|([1][012]))[\\/](([0][1-9])|([12][0-9])|([3][01]))[ ](([01][0-9])|([2][0-3]))$"),
        FORMAT_YYMMDDHM_SLASH("yyyy/MM/dd HH:mm", "^[1-5]([0-9]){3}[\\/](([0][1-9])|([1][012]))[\\/](([0][1-9])|([12][0-9])|([3][01]))[ ](([01][0-9])|([2][0-3])):[0-5][0-9]$"),
        FORMAT_YYMMDDHMS_SLASH("yyyy/MM/dd HH:mm:ss", "^[1-5]([0-9]){3}[\\/](([0][1-9])|([1][012]))[\\/](([0][1-9])|([12][0-9])|([3][01]))[ ](([01][0-9])|([2][0-3])):[0-5][0-9]:[0-5][0-9]$"),
        FORMAT_YYMMDDHMSS_SLASH("yyyy/MM/dd HH:mm:ss:SSS", "^[1-5]([0-9]){3}[\\/](([0][1-9])|([1][012]))[\\/](([0][1-9])|([12][0-9])|([3][01]))[ ](([01][0-9])|([2][0-3])):[0-5][0-9]:[0-5][0-9]:[0-9][0-9][0-9]$"),

        /**
         * 中文
         **/
        FORMAT_YYMM_ZH("yyyy年MM月", "^[1-5]([0-9]){3}[年](([0][1-9])|([1][012]))[月]$"),
        FORMAT_YYWW_ZH("yyyy年ww周", "^[1-5]([0-9]){3}[年](([0][1-7]))[周]$"),
        FORMAT_YYMMDD_ZH("yyyy年MM月dd日", "^[1-5]([0-9]){3}[年](([0][1-9])|([1][012]))[月](([0][1-9])|([12][0-9])|([3][01]))[日]$"),
        FORMAT_YYMMDDH_ZH("yyyy年MM月dd日 HH点", "^[1-5]([0-9]){3}[年](([0][1-9])|([1][012]))[月](([0][1-9])|([12][0-9])|([3][01]))日[ ](([01][0-9])|([2][0-3]))点$"),
        FORMAT_YYMMDDHM_ZH("yyyy年MM月dd日 HH点mm分", "^[1-5]([0-9]){3}[年](([0][1-9])|([1][012]))[月](([0][1-9])|([12][0-9])|([3][01]))日[ ](([01][0-9])|([2][0-3]))点[0-5][0-9]分$"),
        FORMAT_YYMMDDHMS_ZH("yyyy年MM月dd日 HH点mm分ss秒", "^[1-5]([0-9]){3}[年](([0][1-9])|([1][012]))[月](([0][1-9])|([12][0-9])|([3][01]))日[ ](([01][0-9])|([2][0-3]))点[0-5][0-9]分[0-5][0-9]秒$"),
        FORMAT_YYMMDDHMSS_ZH("yyyy年MM月dd日 HH点mm分ss秒SSS毫秒", "^[1-5]([0-9]){3}[年](([0][1-9])|([1][012]))[月](([0][1-9])|([12][0-9])|([3][01]))日[ ](([01][0-9])|([2][0-3]))点[0-5][0-9]分[0-5][0-9]秒[0-9][0-9][0-9]毫秒$"),

        ;
        @Getter
        private String fmt;
        @Getter
        private String regex;

        Parttern(String fmt, String regex) {
            this.fmt = fmt;
            this.regex = regex;
        }
    }

    /**
     * 新建时间对象
     **/
    public static DateTime create() {
        return DateTime.now();
    }

    public static Date createDate() {
        return DateTime.now().toDate();
    }

    /**
     * 新建时间对象
     **/
    public static DateTime create(Date date) {
        if (null == date) {
            return null;
        }
        return new DateTime(date);
    }

    /**
     * 注意:timestamp为13位,否则新建出来的日期是1970年1月18号
     **/
    public static DateTime create(long timestamp) {
        return new DateTime(timestamp);
    }

    /**
     * 注意:timestamp为13位,否则新建出来的日期是1970年1月18号
     **/
    public static Date createDate(long timestamp) {
        return new DateTime(timestamp).toDate();
    }

    /**
     * 基本功能:获取当前时间
     * 一.日期的格式转换
     * 1.日期对象转字符串 {@link #format(DateTime)}
     * 2.日期对象转时间戳 {@link #create().getMillis() }
     * 3.时间戳转日期对象 {@link #create(long timestamp) }
     * 4.时间戳转字符串 {@link #create(long timestamp)} --> {@link #format(DateTime)}
     */

    /**
     * 默认日期转换格式:yyyy-MM-dd HH:mm:ss
     **/
    public static String format(DateTime dateTime) {
        if (null == dateTime) {
            return null;
        }
        return format(dateTime, Parttern.FORMAT_YYMMDDHMS_MID);
    }

    /**
     * 默认日期转换格式:yyyy-MM-dd HH:mm:ss
     **/
    public static String format(Date date) {
        if (null == date) {
            return null;
        }
        return format(date, Parttern.FORMAT_YYMMDDHMS_MID);
    }

    public static String format(DateTime dateTime, Parttern fmt) {
        if (null == dateTime) {
            return null;
        }
        if (null == fmt) {
            fmt = Parttern.FORMAT_YYMMDDHMS_MID;
        }
        return dateTime.toString(getFormatter(fmt));
    }

    public static String format(Date date, Parttern fmt) {
        DateTime dateTime = create(date);
        if (null == dateTime) {
            return null;
        }
        if (null == fmt) {
            fmt = Parttern.FORMAT_YYMMDDHMS_MID;
        }
        return dateTime.toString(getFormatter(fmt));
    }

    public static String format(DateTime dateTime, String format) {
        if (null == dateTime) {
            return null;
        }
        if (StringUtils.isBlank(format)) {
            format = Parttern.FORMAT_YYMMDDHMS_MID.getFmt();
        }
        return dateTime.toString(getFormatter(format));
    }

    public static String format(Date date, String format) {
        DateTime dateTime = create(date);
        if (null == dateTime) {
            return null;
        }
        if (StringUtils.isBlank(format)) {
            format = Parttern.FORMAT_YYMMDDHMS_MID.getFmt();
        }
        return dateTime.toString(getFormatter(format));
    }

    public static DateTime parse(String date) {
        if (StringUtils.isBlank(date)) {
            return null;
        }
        return parse(date, Parttern.FORMAT_YYMMDDHMS_MID);
    }

    public static Date parseDate(String date) {
        if (StringUtils.isBlank(date)) {
            return null;
        }
        DateTime dateTime = parse(date);
        if (null == dateTime) {
            return null;
        }
        return dateTime.toDate();
    }

    /**
     * 转换日期
     **/
    public static DateTime parse(String date, Parttern fmt) {
        if (StringUtils.isBlank(date)) {
            return null;
        }
        if (null == fmt) {
            fmt = Parttern.FORMAT_YYMMDDHMS_MID;
        }
        DateTimeFormatter formatter = getFormatter(fmt);
        if (null == formatter) {
            return null;
        }
        return formatter.parseDateTime(date);
    }

    public static Date parseDate(String date, Parttern fmt) {
        if (StringUtils.isBlank(date)) {
            return null;
        }
        if (null == fmt) {
            fmt = Parttern.FORMAT_YYMMDDHMS_MID;
        }
        DateTimeFormatter formatter = getFormatter(fmt);
        if (null == formatter) {
            return null;
        }
        return formatter.parseDateTime(date).toDate();
    }

    /**
     * 转换日期
     **/
    public static DateTime parse(String date, String format) {
        if (StringUtils.isBlank(date)) {
            return null;
        }
        if (StringUtils.isBlank(format)) {
            format = Parttern.FORMAT_YYMMDDHMS_MID.getFmt();
        }
        DateTimeFormatter formatter = getFormatter(format);
        if (null == formatter) {
            return null;
        }
        return formatter.parseDateTime(date);
    }

    /**
     * 转换日期
     **/
    public static Date parseDate(String date, String format) {
        if (StringUtils.isBlank(date)) {
            return null;
        }
        if (StringUtils.isBlank(format)) {
            format = Parttern.FORMAT_YYMMDDHMS_MID.getFmt();
        }
        DateTimeFormatter formatter = getFormatter(format);
        if (null == formatter) {
            return null;
        }
        return formatter.parseDateTime(date).toDate();
    }

    /**
     * 二.秒的加减
     * 0.当前日期加减N秒
     */
    public static DateTime addSeconds(DateTime dateTime, int seconds) {
        if (null == dateTime) {
            return null;
        }
        return dateTime.plusSeconds(seconds);
    }

    public static Date addSeconds(Date dateTime, int seconds) {
        if (null == dateTime) {
            return null;
        }
        return addSeconds(create(dateTime), seconds).toDate();
    }

    /**
     * 二.分钟的加减
     * 0.当前日期加减N分钟
     */
    public static DateTime addMinutes(DateTime dateTime, int minutes) {
        if (null == dateTime) {
            return null;
        }
        return dateTime.plusMinutes(minutes);
    }

    public static Date addMinutes(Date dateTime, int minutes) {
        if (null == dateTime) {
            return null;
        }
        return addMinutes(create(dateTime), minutes).toDate();
    }

    /**
     * 二.小时的加减
     * 0.当前日期加减N小时
     */
    public static DateTime addHours(DateTime dateTime, int hours) {
        if (null == dateTime) {
            return null;
        }
        return dateTime.plusHours(hours);
    }

    public static Date addHours(Date dateTime, int hours) {
        if (null == dateTime) {
            return null;
        }
        return addHours(create(dateTime), hours).toDate();
    }

    /**
     * 二.日期的加减
     * 1.当前日期加减N天
     */
    public static DateTime addDays(DateTime dateTime, int days) {
        if (null == dateTime) {
            return null;
        }
        return dateTime.plusDays(days);
    }

    public static Date addDays(Date dateTime, int days) {
        if (null == dateTime) {
            return null;
        }
        return addDays(create(dateTime), days).toDate();
    }

    /**
     * 2.当前日期加减N周
     */
    public static DateTime addWeeks(DateTime dateTime, int weeks) {
        if (null == dateTime) {
            return null;
        }
        return dateTime.plusWeeks(weeks);
    }

    public static Date addWeeks(Date dateTime, int weeks) {
        if (null == dateTime) {
            return null;
        }
        return addWeeks(create(dateTime), weeks).toDate();
    }

    /**
     * 3.当前日期加减N月
     */
    public static DateTime addMonths(DateTime dateTime, int months) {
        if (null == dateTime) {
            return null;
        }
        return dateTime.plusMonths(months);
    }

    public static Date addMonths(Date dateTime, int months) {
        if (null == dateTime) {
            return null;
        }
        return addMonths(create(dateTime), months).toDate();
    }

    /**
     * 4.当前日期加减N年
     */
    public static DateTime addYears(DateTime dateTime, int years) {
        if (null == dateTime) {
            return null;
        }
        return dateTime.plusYears(years);
    }

    public static Date addYears(Date dateTime, int years) {
        if (null == dateTime) {
            return null;
        }
        return addYears(create(dateTime), years).toDate();
    }

    /**
     * 三.日期坐标转换
     * 1.指定日期是本周的周几
     * 2.指定日期是本年的第几周
     */
    public static Integer getDayOfWeek(DateTime dateTime) {
        if (null == dateTime) {
            return null;
        }
        return dateTime.getDayOfWeek();
    }

    public static Integer getDayOfWeek(Date dateTime) {
        if (null == dateTime) {
            return null;
        }
        return getDayOfWeek(create(dateTime));
    }

    public static Integer getWeekOfYear(DateTime dateTime) {
        if (null == dateTime) {
            return null;
        }
        return dateTime.getWeekOfWeekyear();
    }

    public static Integer getWeekOfYear(Date dateTime) {
        if (null == dateTime) {
            return null;
        }
        return getWeekOfYear(create(dateTime));
    }


    /**
     * 四.日期单位的特殊点
     * 1.根据日期,获取本年的第一天的0点时间 {@link #getYearOfBegin(DateTime)}
     * 2.根据日期,获取本年的最后一天的23:59:59的时间 {@link #getYearOfEnd(DateTime)}
     * 3.根据日期,获取本月的第一天的0点时间 {@link #getMonthOfBegin(DateTime)}
     * 4.根据日期,获取本月的最后一天的23:59:59的时间 {@link #getMonthOfEnd(DateTime)}
     * 5.根据日期,获取本周的第一天的0点时间 {@link #getWeekOfBegin(DateTime)}
     * 6.根据日期,获取本周的最后一天的23:59:59的时间戳 {@link #getWeekOfEnd(DateTime)}
     * 7.根据日期,获取当前的起止时间 {@link #getDayOfBegin(DateTime)} {@link #getDayOfEnd(DateTime)}
     */

    public static DateTime getYearOfBegin(DateTime dateTime) {
        if (null == dateTime) {
            return null;
        }
        return dateTime.dayOfYear().withMinimumValue().dayOfYear().roundFloorCopy();
    }

    public static Date getYearOfBegin(Date dateTime) {
        if (null == dateTime) {
            return null;
        }
        return getYearOfBegin(create(dateTime)).toDate();
    }

    public static DateTime getYearOfEnd(DateTime dateTime) {
        if (null == dateTime) {
            return null;
        }
        return dateTime.dayOfYear().withMaximumValue().dayOfYear().roundCeilingCopy().minusMillis(1);
    }

    public static Date getYearOfEnd(Date dateTime) {
        if (null == dateTime) {
            return null;
        }
        return getYearOfEnd(create(dateTime)).toDate();
    }

    public static DateTime getMonthOfBegin(DateTime dateTime) {
        if (null == dateTime) {
            return null;
        }
        return dateTime.dayOfMonth().withMinimumValue().dayOfMonth().roundFloorCopy();
    }

    public static Date getMonthOfBegin(Date dateTime) {
        if (null == dateTime) {
            return null;
        }
        return getMonthOfBegin(create(dateTime)).toDate();
    }

    public static DateTime getMonthOfEnd(DateTime dateTime) {
        if (null == dateTime) {
            return null;
        }
        return dateTime.dayOfMonth().withMaximumValue().dayOfMonth().roundCeilingCopy().minusMillis(1);
    }

    public static Date getMonthOfEnd(Date dateTime) {
        if (null == dateTime) {
            return null;
        }
        return getMonthOfEnd(create(dateTime)).toDate();
    }

    public static DateTime getWeekOfBegin(DateTime dateTime) {
        if (null == dateTime) {
            return null;
        }
        return dateTime.dayOfWeek().withMinimumValue().dayOfWeek().roundFloorCopy();
    }

    public static Date getWeekOfBegin(Date dateTime) {
        if (null == dateTime) {
            return null;
        }
        return getWeekOfBegin(create(dateTime)).toDate();
    }

    public static DateTime getWeekOfEnd(DateTime dateTime) {
        if (null == dateTime) {
            return null;
        }
        return dateTime.dayOfWeek().withMaximumValue().dayOfWeek().roundCeilingCopy().minusMillis(1);
    }

    public static Date getWeekOfEnd(Date dateTime) {
        if (null == dateTime) {
            return null;
        }
        return getWeekOfEnd(create(dateTime)).toDate();
    }

    public static DateTime getDayOfBegin(DateTime dateTime) {
        if (null == dateTime) {
            return null;
        }
        return dateTime.millisOfDay().withMinimumValue().millisOfDay().roundFloorCopy();
    }

    public static Date getDayOfBegin(Date dateTime) {
        if (null == dateTime) {
            return null;
        }
        return getDayOfBegin(create(dateTime)).toDate();
    }

    public static DateTime getDayOfEnd(DateTime dateTime) {
        if (null == dateTime) {
            return null;
        }
        return dateTime.millisOfDay().withMaximumValue().millisOfDay().roundCeilingCopy();
    }

    public static Date getDayOfEnd(Date dateTime) {
        if (null == dateTime) {
            return null;
        }
        return getDayOfEnd(create(dateTime)).toDate();
    }

    public static DateTime getHourOfBegin(DateTime dateTime) {
        if (null == dateTime) {
            return null;
        }
        return dateTime.hourOfDay().roundFloorCopy();
    }

    public static Date getHourOfBegin(Date dateTime) {
        if (null == dateTime) {
            return null;
        }
        return getHourOfBegin(create(dateTime)).toDate();
    }

    public static DateTime getHourOfEnd(DateTime dateTime) {
        if (null == dateTime) {
            return null;
        }
        return dateTime.hourOfDay().roundCeilingCopy().minusMillis(1);
    }

    public static Date getHourOfEnd(Date dateTime) {
        if (null == dateTime) {
            return null;
        }
        return getHourOfEnd(create(dateTime)).toDate();
    }

    public static DateTime getMinuteOfBegin(DateTime dateTime) {
        if (null == dateTime) {
            return null;
        }
        return dateTime.minuteOfDay().roundFloorCopy();
    }

    public static Date getMinuteOfBegin(Date dateTime) {
        if (null == dateTime) {
            return null;
        }
        return getMinuteOfBegin(create(dateTime)).toDate();
    }

    public static DateTime getMinuteOfEnd(DateTime dateTime) {
        if (null == dateTime) {
            return null;
        }
        return dateTime.minuteOfDay().roundCeilingCopy().minusMillis(1);
    }

    public static Date getMinuteOfEnd(Date dateTime) {
        if (null == dateTime) {
            return null;
        }
        return getMinuteOfEnd(create(dateTime)).toDate();
    }

    public static DateTime getSecondOfBegin(DateTime dateTime) {
        if (null == dateTime) {
            return null;
        }
        return dateTime.secondOfDay().roundFloorCopy();
    }

    public static Date getSecondOfBegin(Date dateTime) {
        if (null == dateTime) {
            return null;
        }
        return getSecondOfBegin(create(dateTime)).toDate();
    }

    public static DateTime getSecondOfEnd(DateTime dateTime) {
        if (null == dateTime) {
            return null;
        }
        return dateTime.secondOfDay().roundCeilingCopy().minusMillis(1);
    }

    public static Date getSecondOfEnd(Date dateTime) {
        if (null == dateTime) {
            return null;
        }
        return getSecondOfEnd(create(dateTime)).toDate();
    }

    /**
     * 五.日期大小的比较
     * 1.两个日期,比较返回大小{@link #compare(DateTime, DateTime)}
     * 2.两个日期,判断日期是否在2个日期之间{@link #between(DateTime, DateTime, DateTime)}
     * 2.两个日期,判断日期是否在2个日期之间{@link #between(Date, Date, Date)}
     * 3.计算两个日期之间的差额(按年算){@link #distanceYears(DateTime, DateTime)}
     * 4.计算两个日期之间的差额(按月算){@link #distanceMonths(DateTime, DateTime)}
     * 5.计算两个日期之间的差额(按天算){@link #distanceDays(DateTime, DateTime)}
     * 6.计算两个日期之间的差额(按小时算){@link #distanceHours(DateTime, DateTime)}
     * 7.计算两个日期之间的差额(按分钟算){@link #distanceMinutes(DateTime, DateTime)}
     * 8.计算两个日期之间的差额(按秒算){@link #distanceSeconds(DateTime, DateTime)}
     * 9.计算两个日期之间的差额(按毫秒算){@link #distanceMillis(DateTime, DateTime)}
     * <p>
     * left < right = 1
     * left = right = 0
     * left > right = -1
     */
    public static Integer compare(DateTime left, DateTime right) {
        if (null == left || null == right) {
            return 0;
        }
        if (left.isBefore(right)) {
            return 1;
        }
        if (left.isEqual(right)) {
            return 0;
        }
        return -1;
    }

    public static Integer compare(Date left, Date right) {
        if (null == left || null == right) {
            return 0;
        }
        return compare(create(left), create(right));
    }

    public static Boolean between(DateTime src, DateTime left, DateTime right) {
        if (null == src || null == left || null == right) {
            return false;
        }
        if (src.isAfter(left) && src.isBefore(right)) {
            return true;
        }
        return false;
    }

    public static Boolean between(Date src, Date left, Date right) {
        if (null == src || null == left || null == right) {
            return false;
        }
        return between(create(src), create(left), create(right));
    }

    /**
     * 计算日期之间的差值:按年算
     **/
    public static Integer distanceYears(DateTime left, DateTime right) {
        if (null == left || null == right) {
            return null;
        }
        Period p = new Period(left, right, PeriodType.years());
        return p.getYears();
    }

    public static Integer distanceYears(Date left, Date right) {
        if (null == left || null == right) {
            return null;
        }
        return distanceYears(create(left), create(right));
    }

    /**
     * 计算日期之间的差值:按月算
     **/
    public static Integer distanceMonths(DateTime left, DateTime right) {
        if (null == left || null == right) {
            return null;
        }
        Period p = new Period(left, right, PeriodType.months());
        return p.getMonths();
    }

    public static Integer distanceMonths(Date left, Date right) {
        if (null == left || null == right) {
            return null;
        }
        return distanceMonths(create(left), create(right));
    }

    public static Integer distanceWeeks(DateTime left, DateTime right) {
        if (null == left || null == right) {
            return null;
        }
        Period p = new Period(left, right, PeriodType.weeks());
        return p.getWeeks();
    }

    public static Integer distanceWeeks(Date left, Date right) {
        if (null == left || null == right) {
            return null;
        }
        return distanceWeeks(create(left), create(right));
    }

    /**
     * 计算日期之间的差值:按天算
     **/
    public static Integer distanceDays(DateTime left, DateTime right) {
        if (null == left || null == right) {
            return null;
        }
        Period p = new Period(left, right, PeriodType.days());
        return p.getDays();
    }

    public static Integer distanceDays(Date left, Date right) {
        if (null == left || null == right) {
            return null;
        }
        return distanceDays(create(left), create(right));
    }

    /**
     * 计算日期之间的差值:按小时算
     **/
    public static Integer distanceHours(DateTime left, DateTime right) {
        if (null == left || null == right) {
            return null;
        }
        Period p = new Period(left, right, PeriodType.hours());
        return p.getHours();
    }

    public static Integer distanceHours(Date left, Date right) {
        if (null == left || null == right) {
            return null;
        }
        return distanceHours(create(left), create(right));
    }

    /**
     * 计算日期之间的差值:按分钟算
     **/
    public static Integer distanceMinutes(DateTime left, DateTime right) {
        if (null == left || null == right) {
            return null;
        }
        Period p = new Period(left, right, PeriodType.minutes());
        return p.getMinutes();
    }

    public static Integer distanceMinutes(Date left, Date right) {
        if (null == left || null == right) {
            return null;
        }
        return distanceMinutes(create(left), create(right));
    }

    /**
     * 计算日期之间的差值:按秒算
     **/
    public static Integer distanceSeconds(DateTime left, DateTime right) {
        if (null == left || null == right) {
            return null;
        }
        Period p = new Period(left, right, PeriodType.seconds());
        return p.getSeconds();
    }

    public static Integer distanceSeconds(Date left, Date right) {
        if (null == left || null == right) {
            return null;
        }
        return distanceSeconds(create(left), create(right));
    }

    /**
     * 计算日期之间的差值:按毫秒算
     **/
    public static Integer distanceMillis(DateTime left, DateTime right) {
        if (null == left || null == right) {
            return null;
        }
        Period p = new Period(left, right, PeriodType.millis());
        return p.getMillis();
    }

    public static Integer distanceMillis(Date left, Date right) {
        if (null == left || null == right) {
            return null;
        }
        return distanceMillis(create(left), create(right));
    }

    /**
     * 六.是否同一个小时,同一天,同一周,同一月,同一年
     * 1.是否是同一天
     */
    public static Boolean isSameHour(DateTime left, DateTime right) {
        if (null == left || null == right) {
            return false;
        }
        if (isSameDay(left, right) && left.getHourOfDay() == right.getHourOfDay()) {
            return true;
        }
        return false;
    }

    public static Boolean isSameHour(Date left, Date right) {
        if (null == left || null == right) {
            return false;
        }
        return isSameHour(create(left), create(right));
    }

    public static Boolean isSameDay(DateTime left, DateTime right) {
        if (null == left || null == right) {
            return false;
        }
        if (left.getChronology().equals(right.getChronology())
                && left.getYear() == right.getYear()
                && left.getDayOfYear() == right.getDayOfYear()) {
            return true;
        }
        return false;
    }

    public static Boolean isSameDay(Date left, Date right) {
        if (null == left || null == right) {
            return false;
        }
        return isSameDay(create(left), create(right));
    }

    public static Boolean isSameWeek(DateTime left, DateTime right) {
        if (null == left || null == right) {
            return false;
        }
        if (left.getChronology().equals(right.getChronology())
                && left.getYear() == right.getYear()
                && left.getWeekOfWeekyear() == right.getWeekOfWeekyear()) {
            return true;
        }
        return false;
    }

    public static Boolean isSameWeek(Date left, Date right) {
        if (null == left || null == right) {
            return false;
        }
        return isSameWeek(create(left), create(right));
    }

    public static Boolean isSameMonth(DateTime left, DateTime right) {
        if (null == left || null == right) {
            return false;
        }
        if (left.getChronology().equals(right.getChronology())
                && left.getYear() == right.getYear()
                && left.getMonthOfYear() == right.getMonthOfYear()) {
            return true;
        }
        return false;
    }

    public static Boolean isSameMonth(Date left, Date right) {
        if (null == left || null == right) {
            return false;
        }
        return isSameMonth(create(left), create(right));
    }

    public static Boolean isSameYear(DateTime left, DateTime right) {
        if (null == left || null == right) {
            return false;
        }
        if (left.getChronology().equals(right.getChronology())
                && left.getYear() == right.getYear()) {
            return true;
        }
        return false;
    }

    public static Boolean isSameYear(Date left, Date right) {
        if (null == left || null == right) {
            return false;
        }
        return isSameYear(create(left), create(right));
    }

    /**
     * 七.日期截断
     * 1.获取指定日期的天的毫秒数
     *   2018-04-08 16:33:01 285 -> 2018-04-08 00:00:00 000
     * 2.获取指定日期的小时的毫秒数
     *   2018-04-08 16:33:01 285 -> 2018-04-08 16:00:00 000
     * 3.获取指定日期的分的毫秒数
     *   2018-04-08 16:33:01 285 -> 2018-04-08 16:33:00 000
     * 4.获取指定日期的秒的毫秒数
     *   2018-04-08 16:33:01 285 -> 2018-04-08 16:33:01 000
     */

    /**
     * 八.获取时间段
     * 1.获取起止日期之间的所有日期列表
     * 2.获取起止日期之间的所有周列表
     * 3.获取起止日期之间的所有月列表
     */

    /*
     ****************************************私有方法区*******************************************
                   _               _                           _    _                 _
                  (_)             | |                         | |  | |               | |
      _ __   _ __  _ __   __ __ _ | |_  ___   _ __ ___    ___ | |_ | |__    ___    __| |
     | '_ \ | '__|| |\ \ / // _` || __|/ _ \ | '_ ` _ \  / _ \| __|| '_ \  / _ \  / _` |
     | |_) || |   | | \ V /| (_| || |_|  __/ | | | | | ||  __/| |_ | | | || (_) || (_| |
     | .__/ |_|   |_|  \_/  \__,_| \__|\___| |_| |_| |_| \___| \__||_| |_| \___/  \__,_|
     | |
     |_|
     ****************************************私有方法区*******************************************
     */

    /**
     * 获取格式解析器
     */
    private static DateTimeFormatter getFormatter(Parttern fmt) {
        if (null == fmt) {
            return null;
        }
        return DateTimeFormat.forPattern(fmt.getFmt());
    }

    /**
     * 获取格式解析器
     */
    private static DateTimeFormatter getFormatter(String format) {
        if (StringUtils.isBlank(format)) {
            return null;
        }
        return DateTimeFormat.forPattern(format);
    }
}
