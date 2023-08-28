package com.xiaobai.fast.quarkus.core.util;

import com.xiaobai.fast.quarkus.config.ienum.ServiceCodeEnum;
import com.xiaobai.fast.quarkus.core.exception.ServiceException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * @author baijie <a href="mrwhite777@163.com"></a>
 * @since 1.0
 */
public class DateUtils {

    public static final String YYYY_MM_DD = "yyyy-MM-dd";
    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    /**
     * 获取当前时间
     * @return 当前时间
     */
    public static Date getNowDate(){
        return new Date();
    }

    /**
     * 获取当前时间字符串
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static String getNowDateString(){

        return formatDate(getNowDate());
    }

    /**
     * 格式化日期
     * @param date date
     * @return 格式化的日期
     * @see DateUtils#YYYY_MM_DD 格式
     */
    public static String formatDate(Date date){
        return format(YYYY_MM_DD,date);
    }

    /**
     * 格式化日期
     * @param date date
     * @return 格式化的日期
     * @see  DateUtils#YYYY_MM_DD_HH_MM_SS
     */
    public static String formatDateTime(Date date){
        return format(YYYY_MM_DD_HH_MM_SS,date);
    }

    /**
     * 格式化日期
     * @param pattern 日期格式
     * @param date 时间
     * @return 被格式化后的日期
     */
    public static String format(String pattern,Date date){
        return new SimpleDateFormat(pattern).format(date);
    }

    public static Date parseDateTime(String startTime) {

        try {
            return new SimpleDateFormat(YYYY_MM_DD_HH_MM_SS).parse(startTime);
        } catch (ParseException e) {
            throw new ServiceException(ServiceCodeEnum.DATE_PARSE_ERROR,ServiceCodeEnum.DATE_PARSE_ERROR);
        }
    }
    public static LocalDateTime parseLocalDateTime(String startTime) {

        try {
            return parseDateTime(startTime).toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        } catch (Exception e) {
            throw new ServiceException(ServiceCodeEnum.DATE_PARSE_ERROR,ServiceCodeEnum.DATE_PARSE_ERROR);
        }
    }
}
