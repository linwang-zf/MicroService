package com.oes.util.datatime;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;

/**
 * @author : JQK
 * @date : 2020-07-23 11:41
 * @description : DayOfWeek 的方法
 */
public class DayOfWeekOp {

    /**
     * start 到 end 期间 dayOfWeek 的个数
     * 例如：2020-06-01 到 2020-07-23，星期一的个数为 8
     * 调用方法：
     *  dateUtil(start, end, DayOfWeek.MONDAY)
     *
     * @param start 开始日期，包括
     * @param end 结束日期，不包括
     * @param dayOfWeek 星期
     * */
    public static long dayBetween(LocalDate start, LocalDate end, DayOfWeek dayOfWeek) {
        LocalDate pre = start.with(TemporalAdjusters.nextOrSame(dayOfWeek));
        LocalDate pos = end.with(TemporalAdjusters.previous(dayOfWeek));
        long diff = pre.until(pos, ChronoUnit.DAYS);

        long week = diff / 7;
        return week + 1;
    }

}
