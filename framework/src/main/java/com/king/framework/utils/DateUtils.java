package com.king.framework.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @创建人 chq
 * @创建时间 2020/4/14
 * @描述
 */
public class DateUtils {

    private static Logger logger = LoggerFactory.getLogger(DateUtils.class);
    private static final Date STANDARD_DATE = new Date(0L);

    private static final long STANDARD_TIME = STANDARD_DATE.getTime();

    /**
     * 把秒数转成时间
     */
    public synchronized static Date parse(Long second) {
        if (second == null)
            return null;
        return new Date(STANDARD_TIME + second.longValue() * 1000L);
    }

    /**
     * 获取时间的秒数
     */
    public synchronized static Long getSecond(Date date) {
        if (date == null)
            return null;
        long l = date.getTime() / 1000;
        return Long.valueOf(l);
    }

    /**
     * 取得月的剩余天数
     *
     * @param date
     * @return
     */
    public static int getRemainDayOfMonth(Date date) {
        int dayOfMonth = getDayOfMonth(date);
        int day = getPassDayOfMonth(date);
        return dayOfMonth - day;
    }

    /**
     * 取得月已经过的天数
     *
     * @param date
     * @return
     */
    public static int getPassDayOfMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 取得月天数
     *
     * @param date
     * @return
     */
    public static int getDayOfMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    /**
     * 取得下月第一天
     *
     * @param date
     * @return
     */
    public static Date getLastFirstDateOfMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.MONTH, 1);
        return calendar.getTime();
    }

    public static String formatSeconds(Long seconds, String pattern) {
        return format(seconds != null ? seconds * 1000l : null, pattern);
    }

    public static String format(Long timeMillis, String pattern) {
        if (timeMillis == null || StringUtils.isEmpty(pattern)) {
            return "";
        }
        Date date = new Date(timeMillis);
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
        return dateFormat.format(date);
    }

    public static String format(Long time) {
        return format(time, "yyyy-MM-dd HH:mm:ss");
    }

    public static String formatSeconds(Long seconds) {
        return formatSeconds(seconds, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 获取前一年的数据
     *
     * @param date
     * @return
     */
    public static String getLastYearDate(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.YEAR, -1);
        return format.format(c.getTime());
    }

    /**
     * 获取当前日期的前一天
     *
     * @param date
     * @return
     */
    public static Date getNextDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        date = calendar.getTime();
        return date;
    }

    /**
     * 获取明天
     *
     * @param date
     * @return
     */
    public static Date getTomorowDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        date = calendar.getTime();
        return date;
    }

    /**
     * 获取指定前几天的日期
     *
     * @param date
     * @return
     */
    public static Date getOtherDay(Date date, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -days);
        date = calendar.getTime();
        return date;
    }

    public static Date calcDate(Date date, int field, int amount) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(field, amount);
        return c.getTime();
    }

    /**
     * 将格式为"yyyy-MM-dd HH:mm:ss"的时间转为长整形
     *
     * @return
     */
    public static long StrTime2Long(String time) {
        SimpleDateFormat myformatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.SIMPLIFIED_CHINESE);
        try {
            return myformatter.parse(time).getTime() / 1000;
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * 把时间 截 到 当日开始， 也就是 00:00:00
     */
    public static long roundToCurrentDay(long d) {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date(d * 1000L));
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime().getTime() / 1000L;
    }

    /**
     * 把时间截到每月的1日0点
     */
    public static long roundToCurrentMonth(long d) {
        Calendar c = Calendar.getInstance();

        c.setTime(new Date(d * 1000L));
        c.set(Calendar.DATE, 1);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);

        return c.getTime().getTime() / 1000L;
    }

    public static long roundToCurrSeason(long d) {

        String ds = DateUtils.formatSeconds(d);
        String seasonOne = ds.substring(0, 5) + "01-01 00:00:00";
        long one = DateUtils.StrTime2Long(seasonOne);
        String seasonTwo = ds.substring(0, 5) + "04-01 00:00:00";
        long two = DateUtils.StrTime2Long(seasonTwo);
        String seasonThree = ds.substring(0, 5) + "07-01 00:00:00";
        long three = DateUtils.StrTime2Long(seasonThree);
        String seasonFour = ds.substring(0, 5) + "10-01 00:00:00";
        long four = DateUtils.StrTime2Long(seasonFour);

        long time = one;
        if (d >= one) time = one;
        if (d >= two) time = two;
        if (d >= three) time = three;
        if (d >= four) time = four;

        return time;
    }

    /**
     * 把时间截到当年的1月1日0点
     */
    public static long roundToCurrentYear(long d) {
        Calendar c = Calendar.getInstance();

        c.setTime(new Date(d * 1000L));
        c.set(Calendar.MONTH, 0);
        c.set(Calendar.DATE, 1);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);

        return c.getTime().getTime() / 1000L;
    }

    // 获得某天最大时间 2018-03-20 23:59:59
    public static Date getEndOfDay(Date date) {
        Calendar calendarEnd = Calendar.getInstance();
        calendarEnd.setTime(date);
        calendarEnd.set(Calendar.HOUR_OF_DAY, 23);
        calendarEnd.set(Calendar.MINUTE, 59);
        calendarEnd.set(Calendar.SECOND, 59);

        //防止mysql自动加一秒,毫秒设为0
        calendarEnd.set(Calendar.MILLISECOND, 0);
        return calendarEnd.getTime();
    }

    /**
     * 将时间转换为当前日期 00:00:00  Date类型
     *
     * @param date
     * @return
     */
    private static Date getZeroPointDay(Date date) {
        Calendar calendarStart = Calendar.getInstance();
        calendarStart.setTime(date);
        calendarStart.set(Calendar.HOUR_OF_DAY, 0);
        calendarStart.set(Calendar.MINUTE, 0);
        calendarStart.set(Calendar.SECOND, 0);

        return calendarStart.getTime();
    }

}
