package com.sztech.springclouduser.utils;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateFormatUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

/**
 * @program: springcloud
 * @description:时间工具类
 * @author: jiefu
 * @create: 2018-09-13 14:00
 **/
public class DateTimeUtils {

    public static final String YYYY_MM_DD_HH24_MM_SS = "yyyy-MM-dd HH:mm:ss";

    public static final String YYYYMMDDHH24MISS = "yyyyMMddHHmmss";

    public static final String YYYY_MM_DD = "yyyy-MM-dd";

    public static final String YYYY_MM = "yyyy-MM";

    public static final String MM_SS = "mm:ss";

    /**
     * 获取标准格式的sdf对象
     * @param pattern
     * @return
     */
    public static SimpleDateFormat getStandSdf(String pattern){
        return new SimpleDateFormat(pattern);
    }

    /**
     * 按照指定格式格式化日期
     * @param pattern
     * @param time
     * @return 根据pattern参数生成不同格式的日期字符串
     */
    public static String formatByPattern(String pattern, Object time){
        return getStandSdf(pattern).format(time);
    }

    /**
     * 按照标准格式格式化时间
     * @param time
     * @return 具有标准格式的时间字符串，如：2018-05-01 01:01:01
     */
    public static String formatStandTime(Object time){
        return getStandSdf(YYYY_MM_DD_HH24_MM_SS).format(time);
    }

    /**
     * 按照标准格式格式化日期
     * @param time
     * @return 具有标准格式的日期字符串，如：2018-05-01
     */
    public static String formatStandDate(Object time){
        return getStandSdf(YYYY_MM_DD).format(time);
    }

    /**
     * 按照标准格式格式化时间，只到月份
     * @param time
     * @return 如：2018-05
     */
    public static String getStandMonth(Object time) {
        return getStandSdf(YYYY_MM).format(time);
    }

    /**
     * 解析具有标准格式的时间字符串
     * @param dateTime 2018-01-01 01:01:01
     * @return 字符串的对应的时间
     */
    public static Date parseStandTime(String dateTime){
        try {
            return getStandSdf(YYYY_MM_DD_HH24_MM_SS).parse(dateTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解析具有标准格式的日期字符串
     * @param date 2018-01-01
     * @return 字符串对应的日期
     */
    public static Date parseStandDate(String date){
        try {
            return getStandSdf(YYYY_MM_DD).parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据一定的格式解析日期字符串
     * @param dateTime 时间字符串
     * @param pattern 格式
     * @return 时间字符串对应的date
     */
    public static Date parseByPattern(String dateTime, String pattern) {
        try {
            return getStandSdf(pattern).parse((dateTime));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取指定日期当天开始时间
     * @param date
     * @return
     */
    public static Date getStartTimeOfDay(Date date) {
        Calendar calendar = getCalendar();
        if (null != date){
            calendar.setTime(date);
        }
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return calendar.getTime();
    }

    /**
     * 获取指定日期当天结束时间
     * @param date
     * @return
     */
    public static Date getEndTimeOfDay(Date date){
        Calendar calendar = getCalendar();
        if(null != date) {
            calendar.setTime(date);
        }
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        return calendar.getTime();
    }
    /**
     * 获取当天开始时间
     * @return
     */
    public static Date getStartTimeOfCurrentDay(){
        return getStartTimeOfDay(null);
    }
    /**
     * 获取当天结束时间
     * @return
     */
    public static Date getEndTimeOfCurrentDay(){
        return getEndTimeOfDay(null);
    }
    /**
     * 获取当前年份
     * @return
     */
    public static String getCurrentYear(){
        Calendar c = new GregorianCalendar();
        int year = c.get(Calendar.YEAR);
        return String.valueOf(year);
    }

    /**
     * 获取当前月的当前天
     * @return 当天为当前月的第几天
     */
    public static int howManyDayFromFirstDayOfMonth(){
        Calendar calendar = getCalendar();
        calendar.setTime(new Date());
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        return dayOfMonth;
    }

    /**
     * 获取指定时间月份的当前天
     * @param date
     * @return 当天为当月的第几天
     */
    public static int howManyDayFromFirstDayOfMonth(Date date){
        Calendar calendar = getCalendar();
        calendar.setTime(date);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
        return dayOfMonth;
    }

    /**
     * 几天之前的日期
     * @param howManyDay 向前推的天数
     * @return
     */
    public static Date recentlyDay(int howManyDay){
        Calendar calendar = getCalendar();
        calendar.add(Calendar.DAY_OF_MONTH, -howManyDay);
        return calendar.getTime();
    }

    /**
     * 获取calendar对象
     * @return
     */
    public static Calendar getCalendar() {
        return GregorianCalendar.getInstance(Locale.CHINA);
    }

    /**
     * 几个月之前的日期
     * @param howManyMonth 向前推的月数
     * @return
     */
    public static Date recentlyMonth(int howManyMonth){
        Calendar calendar = getCalendar();
        calendar.add(Calendar.MONTH, -howManyMonth);
        return calendar.getTime();
    }

    /**
     * 几个月之前,且从1号开始，比如当前为5月15日，howManyMonth为1，则返回的日期为4月1日
     * @param howManyMonth 向前推的月数
     * @return
     */
    public static Date recentlyMonthFromStartDay(int howManyMonth){
        Calendar calendar = getCalendar();
        calendar.add(Calendar.MONTH, -howManyMonth);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTime();
    }

    /**
     * 判断时间是否是当天
     * @param date
     * @return
     */
    public static boolean isToday(Date date){
        return StringUtils.equals(DateTimeUtils.formatStandDate(date), DateTimeUtils.formatStandDate(new Date()));
    }

    /**
     * 获取日期的月份
     * @param date
     * @return
     */
    public static int getMonthNum(Date date) {
        Calendar calendar = getCalendar();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * 两个日期间隔的天数
     * @param start
     * @param end
     * @return
     */
    public static int minusDays(Date start, Date end){
        start = getStartTimeOfDay(start);
        end = getStartTimeOfDay(end);
        int days = (int)(start.getTime() - end.getTime())/(1000*3600*24);
        return days;

    }

    /**
     * 计算每个月的天数
     * @param date
     * @return
     */
    public static int getLastDayOfMonth(Date date){
        Calendar calendar = getCalendar();
        calendar.setTime(date);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    /**
     * 计算每个月的天数
     * @param month 月份:数字，不带0
     * @return
     */
    public static int getLastDayOfMonth(String month){
        Calendar calendar = getCalendar();
        calendar.set(Calendar.MONTH, Integer.parseInt(month) - 1);
        return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取seconds秒后的时间
     * @param seconds
     * @return
     */
    public static Date getTimeAddSeconds(int seconds) {
        Calendar calendar = getCalendar();
        calendar.add(Calendar.SECOND, seconds);
        return calendar.getTime();
    }

    /**
     * 得到日期字符串 默认格式（yyyy-MM-dd）pattern可以为："yyyy-MM-dd" "HH:mm:ss"
     * @param date
     * @param pattern
     * @return
     */
    public static String formatDate(Date date, Object... pattern) {
        String formatDate = null;
        if (pattern != null && pattern.length > 0) {
            formatDate = DateFormatUtils.format(date, pattern[0].toString());
        } else {
            formatDate = DateFormatUtils.format(date,YYYY_MM_DD);
        }
        return formatDate;
    }


}
