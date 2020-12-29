package com.oes.util.course;


import com.oes.model.entity.Courses;
import com.oes.util.datatime.DateTimeUtil;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @author : yanjundong
 * @date : 2020-04-25 00:12
 * @description : 课程相关的工具
 */

@Slf4j
public class CourseUtil {

    /**
     * 将上课时间转化为 List 类型
     * 一节课如果一周没有6节课，course对象中的courseTime字段就会为 null
     * @param course
     * @param courseTime
     */
    public static void getCourseTimeList(Courses course, List<String> courseTime) {
        if (null == course)
            return;
        /*课程上课时间*/
        if (null != course.getCourseTime1() && !"".equals(course.getCourseTime1()))
            courseTime.add(course.getCourseTime1());
        if (null != course.getCourseTime2() && !"".equals(course.getCourseTime2()))
            courseTime.add(course.getCourseTime2());
        if (null != course.getCourseTime3() && !"".equals(course.getCourseTime3()))
            courseTime.add(course.getCourseTime3());
        if (null != course.getCourseTime4() && !"".equals(course.getCourseTime4()))
            courseTime.add(course.getCourseTime4());
        if (null != course.getCourseTime5() && !"".equals(course.getCourseTime5()))
            courseTime.add(course.getCourseTime5());
        if (null != course.getCourseTime6() && !"".equals(course.getCourseTime6()))
            courseTime.add(course.getCourseTime6());
    }

    /**
     * 将上课时间转化为 Map 类型
     * @param course
     * @param map key为星期几(1-7) value为上课时间list
     */
    public static void getCourseTimeMap(Courses course, Map<Integer, List<LocalTime>> map) {
        List<String> courseTimes = new ArrayList<>();
        getCourseTimeList(course, courseTimes);
        for (String str : courseTimes) {
            String[] split = str.split("-");
            if (split.length == 2) {
                int dayOfWeek = Integer.parseInt(split[0]);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DateTimeUtil.TIME_FORMAT_HM);
                LocalTime startTime = LocalTime.parse(split[1], formatter);

                if (!map.containsKey(dayOfWeek)) {
                    List<LocalTime> times = new ArrayList<>();
                    times.add(startTime);
                    map.put(dayOfWeek, times);
                } else {
                    map.get(dayOfWeek).add(startTime);
                }
            }
        }
    }







}
