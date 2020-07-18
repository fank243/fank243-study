package com.fank243.springboot.common.utils;

import org.apache.commons.lang3.StringUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 常用工具类：日期转换
 * 
 * @author FanWeiJie
 * @date 2015年7月16日
 * @since JDK1.6
 */
public class DateUtils {

    /** yyyy-MM-dd **/
    public final static String YMD = "yyyy-MM-dd";

    /** yyyy-MM-dd hh:mm:ss **/
    public final static String YMDHMS = "yyyy-MM-dd HH:mm:ss";

    /**
     * 字符串转日期：自定义日期格式
     * 
     * @param date 日期字符串
     * @param pattern 自定义日期格式
     * @return Date
     * @author FanWeiJie
     * @date 2015年7月16日
     */
    public static Date strToDate(String date, String pattern) {
        if (StringUtils.isBlank(date)) {
            return null;
        }
        SimpleDateFormat format = new SimpleDateFormat();
        if (StringUtils.isBlank(pattern)) {
            format.applyPattern(YMDHMS);
        } else {
            format.applyPattern(pattern);
        }
        format.applyPattern(pattern);
        try {
            return format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 字符串转日期：yyyy-MM-dd
     * 
     * @param date 日期字符串
     * @return Date
     * @author FanWeiJie
     * @date 2015年7月16日
     */
    public static Date strToYMD(String date) {
        if (StringUtils.isBlank(date)) {
            return null;
        }
        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern(YMD);
        try {
            return format.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 字符串转日期：yyyy-MM-dd HH:mm:ss
     * 
     * @param date 日期字符串
     * @return Date
     * @throws ParseException
     * @author FanWeiJie
     * @date 2015年7月16日
     */
    public static Date strToYMDHMS(String date) throws ParseException {
        if (StringUtils.isBlank(date) || "null".equals(date)) {
            return null;
        }
        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern(YMDHMS);
        return format.parse(date);
    }

    /**
     * 日期转字符串：自定义日期格式
     * 
     * @param date 日期对象
     * @param pattern 自定义日期格式
     * @return 字符串
     * @author FanWeiJie
     * @date 2015年7月16日
     */
    public static String dateToStr(Date date, String pattern) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat format = new SimpleDateFormat();
        if (StringUtils.isBlank(pattern)) {
            format.applyPattern(YMDHMS);
        } else {
            format.applyPattern(pattern);
        }
        return format.format(date);
    }

    /**
     * 日期转字符串：yyyy-MM-dd
     * 
     * @param date 日期对象
     * @return 字符串
     * @author FanWeiJie
     * @date 2015年7月16日
     */
    public static String dateToYMD(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern(YMD);
        return format.format(date);
    }

    /**
     * 日期转字符串：yyyy-MM-dd HH:mm:ss
     * 
     * @param date 日期对象
     * @return 字符串
     * @author FanWeiJie
     * @date 2015年7月16日
     */
    public static String dateToYMDHMS(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern(YMDHMS);
        return format.format(date);
    }

    /**
     * 比较2个日期的大小
     * 
     * @param beginDate 开始日期
     * @param endDate 结束日期
     * @return 毫秒值
     * @author FanWeiJie
     * @date 2015年7月17日
     */
    public static long compareDate(Date beginDate, Date endDate) {
        return beginDate.getTime() - endDate.getTime();
    }

    /**
     * 计算指定时间后的日期
     *
     * @param date 日期
     * @param field 日期类型，时分秒等
     * @param amount 数量
     * @return 日期
     * @author FanWeiJie
     * @date 2015年7月17日
     */
    public static Date addDate(Date date, int field, int amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(field, amount);
        return calendar.getTime();
    }

    /**
     * 加一天
     *
     * @param date 日期
     * @param amount 数量
     * @return 日期
     * @author FanWeiJie
     * @date 2015年7月17日
     */
    public static Date addDay(Date date, int amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, amount);
        return calendar.getTime();
    }

    /**
     * 计算指定日期星期几，周一为一周的开始
     * 
     * @param date 日期
     * @return 星期几
     */
    public static int getWeek(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        if (Calendar.SUNDAY == calendar.get(Calendar.DAY_OF_WEEK)) {
            return 7;
        }

        return calendar.get(Calendar.DAY_OF_WEEK) - 1;
    }

    /**
     * 计算周一
     * 
     * @param date 日期
     * @return 周一
     */
    public static Date getMonday(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        // 获得当前日期是一个星期的第几天
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);
        if (1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        // 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        // 获得当前日期是一个星期的第几天
        int day = cal.get(Calendar.DAY_OF_WEEK);
        // 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);
        return cal.getTime();
    }

    /**
     * 计算周日
     *
     * @param date 日期
     * @return 周日
     */
    public static Date getSunday(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        // 获得当前日期是一个星期的第几天
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);
        if (1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        // 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        // 获得当前日期是一个星期的第几天
        int day = cal.get(Calendar.DAY_OF_WEEK);
        // 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day + 6);
        return cal.getTime();
    }

    public static void main(String[] args) {
        System.out.println(DateUtils.addDay(new Date(),7));
        System.out.println(DateUtils.dateToYMD(getSunday(new Date())));

    }


    /**
     *  判读时间差距，两个时间相差多少天
     *
     * @param pastTime
     * @param currentTime
     * @return
     */
    public static Long getDay(Date pastTime, Date currentTime) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Long days = null;
        long diff =  currentTime.getTime()   -  pastTime.getTime();
        days = diff / (1000 * 60 * 60 * 24);
        return days;
    }

    /**
     * 判断两个日期是否是同一天
     * @param d1
     * @param d2
     * @return
     */
    public static boolean isTheSameDay(Date d1, Date d2) {
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(d1);
        c2.setTime(d2);
        return (c1.get(Calendar.YEAR) == c2.get(Calendar.YEAR))
                && (c1.get(Calendar.MONTH) == c2.get(Calendar.MONTH))
                && (c1.get(Calendar.DAY_OF_MONTH) == c2.get(Calendar.DAY_OF_MONTH));
    }

}
