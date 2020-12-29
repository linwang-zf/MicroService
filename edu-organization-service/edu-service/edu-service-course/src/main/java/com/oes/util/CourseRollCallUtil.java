package com.oes.util;


import com.oes.consist.CourseConstants;
import com.oes.util.datatime.DateTimeUtil;
import com.oes.util.datatime.DayOfWeekOp;
import lombok.extern.slf4j.Slf4j;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author : JQK
 * @date : 2020-07-22 9:35
 * @description : 课程签到
 */
@Slf4j
public class CourseRollCallUtil {

    /**
     * 得到 datetime是第几次上课
     * @param datetime
     * @param startDate 课程开始日期
     * @param endDate 课程结束日期
     * @param courseTime 课程上课时间的map形式
     * @param minutes 每次课的时长(单位：分钟)
     * @return -1:已结课; 0:尚未开课; -2:不在上课期间; 1-N:第几次课
     */
    public static int getCourseNo(LocalDateTime datetime, LocalDate startDate, LocalDate endDate, Map<Integer, List<LocalTime>> courseTime, int minutes) {
        LocalDate nowDate = datetime.toLocalDate();
        if (endDate.isBefore(nowDate))
            //当前课程已结课
            return -1;
        if (startDate.isAfter(nowDate))
            //当前课程尚未开课
            return 0;
        int sum = 0;
        for (int i = 1; i <= 7; i++) {
            List<LocalTime> localTimes = courseTime.get(i);
            if (null != localTimes && !localTimes.isEmpty()) {
                sum += DayOfWeekOp.dayBetween(startDate, nowDate, DayOfWeek.of(i)) * localTimes.size();
            }
        }

        DayOfWeek currentWeek = nowDate.getDayOfWeek(); //当前是周几
        LocalTime nowTime = datetime.toLocalTime();    //当前是几点

        List<LocalTime> times = courseTime.get(currentWeek.getValue());
        if (times == null) {
            return -2;
        }
        Collections.sort(times);
        int index = 1;
        if (null != times && !times.isEmpty()) {
            for (LocalTime time : times) {
                LocalTime startTime = time.minusMinutes(CourseConstants.PRE_GRACE_PERIOD_MINUTES);
                LocalTime endTime = startTime.plusMinutes(minutes + CourseConstants.POST_GRACE_PERIOD_MINUTES);
                if (DateTimeUtil.datetimeBetween(nowTime, startTime, endTime)) {
                    return sum + index;
                }
                index++;
            }
        }
        return -2;
    }

    public static int getCourseNo(LocalDate startDate, LocalDate endDate, Map<Integer, List<LocalTime>> courseTime, int minutes) {
        return getCourseNo(LocalDateTime.now(), startDate, endDate, courseTime, minutes);
    }

}
