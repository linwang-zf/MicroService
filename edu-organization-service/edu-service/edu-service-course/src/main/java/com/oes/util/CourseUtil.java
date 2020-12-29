package com.oes.util;


import com.oes.model.entity.Courses;
import com.oes.util.datatime.DateTimeUtil;
import com.oes.vo.SyllabusVo;
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

    /**
     * 从start到end的课程表
     * @param courses  课程信息
     * @param start 开始日期，包括开始日期
     * @param end   结束日期，包括结束日期
     * @param week  课程表的返回信息是否是 "周的格式: 1 10:30-12:00"
     * @return 返回课程表信息
     */
    @Deprecated
    public static List<SyllabusVo> getSyllabus(List<Courses> courses, LocalDate start, LocalDate end, boolean week) {
        if (Objects.isNull(courses) || start.isAfter(end))
            return null;
        List<SyllabusVo> syllabusVos = new ArrayList<>();   //课程表信息
        Map<Integer, List<LocalTime>> courseTimes = new HashMap<>(7);
        for (Courses course : courses) {
            //课程开始日期
            LocalDate courseStartDate = course.getStartTime();
            //课程结束日期
            LocalDate courseEndDate = course.getEndTime();

            if(!(end.isBefore(courseStartDate) || start.isAfter(courseEndDate))) {

                LocalDate startPoint = start;
                LocalDate endPoint = end;
                if (startPoint.isBefore(courseStartDate))
                    //start时，课程还没开课
                    startPoint = courseStartDate;
                if (courseEndDate.isBefore(endPoint))
                    //end时，课程已结课
                    endPoint = courseEndDate;

                courseTimes.clear(); //清空
                getCourseTimeMap(course, courseTimes);

                if (!courseTimes.isEmpty()) {
                    LocalDate temp = startPoint;
                    for (; !temp.isAfter(endPoint); temp = temp.plusDays(1)) {
                        List<LocalTime> localTimes = courseTimes.get(temp.getDayOfWeek().getValue());   //星期几的所有上课时间
                        if (Objects.nonNull(localTimes)) {
                            for (LocalTime item : localTimes) {
                                //一节课的结束时间
                                LocalTime endTime = item.plusMinutes(course.getMinutes());
                                String courseTimeStr = null;
                                if (!week)  //月课表
                                    courseTimeStr = String.format("%tF %tR-%tR", temp, item, endTime);
                                else //周课表
                                    courseTimeStr = String.format("%d %tR-%tR", temp.getDayOfWeek().getValue(), item, endTime);

                                int courseNo = CourseRollCallUtil.getCourseNo(LocalDateTime.of(temp, item), courseStartDate, courseEndDate, courseTimes, course.getMinutes());
                                syllabusVos.add(new SyllabusVo(course.getCourseId(), course.getName(), courseTimeStr, course.getSite(), courseNo));
                            }
                        }
                    }
                }
            }
        }

        return syllabusVos;
    }




}
