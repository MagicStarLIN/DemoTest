package com.lcl.utils;


import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

/**
 * @ClassName: DateUtils
 * @Description: 时间操作工具类
 * @author liuchanglin
 * @date 2019/10/25 11:06 上午
 * @version 1.0
 */
public class DateUtils {
    private static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HH:mm:ss");
    private static final DateTimeFormatter DATE8_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd");
    private static final DateTimeFormatter DATE13_FORMATTER =
            DateTimeFormatter.ofPattern("yyyyMMddHHmmssSSS");
    private static final DateTimeFormatter DATE10_FORMATTER =
            DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

    public static String getNowFormatDateStr(){
        return DATE_TIME_FORMATTER.format(Instant.now().atZone(ZoneId.systemDefault()));
    }

    public static String getFormatDateStr(Long timestmp){
        if(timestmp == null){
            return "";
        }
        return format(new Date(timestmp), DATE_TIME_FORMATTER);
    }

    public static Date getFormatDate(Date date){
        if(date == null){
            return new Date();
        }
        return Date.from(Instant.now().truncatedTo(ChronoUnit.SECONDS));
    }

    /**
     *  返回 指定格式的日期字符串
     * @param timestmp dateFormat
     * @return format date String
     */
    public static String getFormatDateStr(Long timestmp,String dateFormat){
        if(timestmp == null){
            return "";
        }
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
        return formatter.format(new Date(timestmp));
    }



    /**
     * java.util.Date 返回 yyyy-MM-dd String 类型
     * @param date
     * @return format date String
     */
    public static String getFormatDateStr(Date date){
        if(date==null){
            return "";
        }
        return format(date, DATE_TIME_FORMATTER);
    }


    /**
     * 返回 指定格式的日期字符串
     * @param date dateFormat(例如 yyyy-MM-dd)
     * @return format date String
     */
    public static String getFormatDateStr(Date date, String dateFormat){
        if(date==null){
            return "";
        }
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat);
        return formatter.format(date);
    }


    /**
     * java.lang.Integer Epoch 返回 yyyy-MM-dd String 类型
     * @param num
     * @return format date String
     */
    public static String getFormatEpochStr(int num){
        long time = num*1000L;
        return format(new Date(time), DATE_TIME_FORMATTER);
    }

    /**
     * yyyy-MM-dd 返回 java.util.Date
     * 不会返回null, 产生异常时返回当前时间的Date对象的实例
     * @param formatStr
     * @return
     */
    public static Date parseDateFromDateStr(String formatStr){
        try{
            LocalDateTime localDateTime = LocalDateTime.parse(formatStr, DATE_TIME_FORMATTER);
            return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        } catch (Exception exp){
            return new Date();
        }
    }

    /**
     * 获取自昨天12点到今天12点的int类型
     * @return {昨天12点epoch_int, 今天12点epoch_int}
     */
    public static int[] getDate(){
        Calendar calendar = getCalendar();
        int[] res = new int[2];
        res[1] = (int)(calendar.getTimeInMillis()/1000);
        calendar.add(Calendar.DAY_OF_YEAR, -1);
        res[0] = (int)(calendar.getTimeInMillis()/1000);
        return res;
    }

    /**
     * 获取yyyy-MM-dd 形式的前后日期时间
     * [0] prev_day [1] current day
     * @param formatDate
     * @return
     */
    public static int[] getDate(String formatDate){
        Calendar calendar = getCalendar();
        String[] dateStr = formatDate.split("-");
        calendar.set(
                Integer.parseInt(dateStr[0]),
                Integer.parseInt(dateStr[1]) - 1,
                Integer.parseInt(dateStr[2]));
        int[] res = new int[2];
        res[1] = (int)(calendar.getTimeInMillis()/1000);
        calendar.add(Calendar.DAY_OF_YEAR, -1);
        res[0] = (int)(calendar.getTimeInMillis()/1000);
        return res;
    }
    /**
     * 获取某一天的上一天 12点到当天12点的int类型
     * @return {上一天12点epoch_int, 当天12点epoch_int}
     */
    public static int[] getBetweenDate(int n){
        Calendar calendar = getCalendar();
        int[] res = new int[2];
        calendar.add(Calendar.DAY_OF_YEAR, 0-n);
        res[1] = (int)(calendar.getTimeInMillis()/1000);
        calendar.add(Calendar.DAY_OF_YEAR, -1);
        res[0] = (int)(calendar.getTimeInMillis()/1000);
        return res;
    }

    /**
     * @param gap 数量
     * @param isFuture true 将来, false 过去
     * @return [Date ,expire time epoch Integer type]
     */
    public static Object[] getEpoch(int gap, boolean isFuture){
        Calendar calendar = getCalendar();
        gap *= isFuture ? 1: -1;
        calendar.add(Calendar.DAY_OF_YEAR,gap);
        return new Object[]{calendar.getTime(), (int)(calendar.getTimeInMillis()/1000)};
    }

    private static Calendar getCalendar(){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 12);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND,0);
        return calendar;
    }

    /**
     *
     * 比较两个时间的大小
     */
    public static int compareDate(String DATE1, String DATE2) {
        try {
            LocalTime time1 = LocalTime.parse(DATE1, TIME_FORMATTER);
            LocalTime time2 = LocalTime.parse(DATE2, TIME_FORMATTER);
            return Integer.signum(time1.compareTo(time2));
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }


    /**
     * 将字符串时间格式转换成Date时间格式，参数String类型
     * 比如字符串时间："2017-12-15 21:49:03"
     * 转换后的date时间：Fri Dec 15 21:49:03 CST 2017
     * @param datetime 类型为String
     * @return
     */
    public static Date StringToDate(String datetime){
        try {
            LocalDateTime localDateTime = LocalDateTime.parse(datetime, DATE_TIME_FORMATTER);
            return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        } catch (Exception e) {
            e.printStackTrace();
            return new Date();
        }
    }

    /**
     *
     * @param date
     * @param pattern
     * @return
     */
    private static int getIntDate(Date date, String pattern) {
        if (date == null) {
            return 0;
        } else {
            return Integer.parseInt(format(date, DateTimeFormatter.ofPattern(pattern)));
        }
    }

    public static int getDate8() {
        return getIntDate(new Date(), "yyyyMMdd");
    }

    public static String getDate13() {
        return DATE13_FORMATTER.format(Instant.now().atZone(ZoneId.systemDefault()));
    }


    public static String getDate10() {
        return DATE10_FORMATTER.format(Instant.now().atZone(ZoneId.systemDefault()));
    }

    private static String format(Date date, DateTimeFormatter formatter) {
        return formatter.format(date.toInstant().atZone(ZoneId.systemDefault()));
    }
}
