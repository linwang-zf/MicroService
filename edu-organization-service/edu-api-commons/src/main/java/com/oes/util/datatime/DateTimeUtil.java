package com.oes.util.datatime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * @author : JQK
 * @date : 2020-07-21 10:51
 * @description : 日期时间的处理
 */
public class DateTimeUtil {

    /*日期的格式*/
    public static final String DATE_FORMAT = "yyyy-MM-dd";

    /*时间的格式*/
    public static final String TIME_FORMAT_HMS = "HH:mm:ss";

    /*时间的格式*/
    public static final String TIME_FORMAT_HM = "HH:mm";

    /*日期时间的格式*/
    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    /**
     * 判断一个时间是否在一个区间中
     * dateTime: 判断的时间
     * start: 这个区间的开始
     * end: 这个区间的结束
     * */
    public static Boolean datetimeBetween(LocalDateTime dateTime, LocalDateTime start, LocalDateTime end) {
        if (null == dateTime || null == start || null == end) {
            return null;
        }
        if (dateTime.isAfter(start) && dateTime.isBefore(end)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断一个时间是否在一个区间中
     * dateTime: 判断的时间
     * start: 这个区间的开始
     * end: 这个区间的结束
     * */
    public static Boolean datetimeBetween(LocalTime dateTime, LocalTime start, LocalTime end) {
        if (null == dateTime || null == start || null == end) {
            return null;
        }
        if (dateTime.isAfter(start) && dateTime.isBefore(end)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断一个日期是否在一个区间中
     * dateTime: 判断的时间
     * start: 这个区间的开始
     * end: 这个区间的结束
     * */
    public static Boolean datetimeBetween(LocalDate dateTime, LocalDate start, LocalDate end) {
        if (null == dateTime || null == start || null == end) {
            return null;
        }
        if (dateTime.isAfter(start) && dateTime.isBefore(end)) {
            return true;
        } else {
            return false;
        }
    }

}
