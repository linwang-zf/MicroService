package com.oes.service;


import com.oes.Exceptions.format.FormatIllegalException;
import com.oes.dao.CourseTableDao;
import com.oes.dao.CoursesDao;
import com.oes.entity.CourseTable;
import com.oes.example.CourseTableExample;
import com.oes.model.entity.Courses;
import com.oes.query.SyllabusQuery;
import com.oes.vo.SyllabusVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author : JQK
 * @date : 2020-07-22 9:41
 * @description : 课程表生成
 */
@Service
@Slf4j
public class CourseTableService {
    @Resource
    private CoursesDao coursesDao;
    @Resource
    private CourseTableDao courseTableDao;



    /**
     * 根据id 得到课程表(包括学生/教师/机构)
     */
    public List<SyllabusVo> getCourseTableByQuery(SyllabusQuery query) {
        if (!(Objects.equals(query.getType().trim(), "month") || Objects.equals(query.getType().trim(), "week"))) {
            throw new IllegalArgumentException("参数异常");
        }
        List<Courses> courses = null;
        List<SyllabusVo> syllabusVos = null;
        if (3 == query.getUserType()) {
            //得到该学生的选课
            courses = coursesDao.getCourseByStuId(query.getId());
        } else {
            if (2 == query.getUserType()) {
                //得到教师的课程
                courses = coursesDao.getCourseByTeacherId(query.getId());
            }else {
                //得到机构的课程
                courses = coursesDao.getCoursesByOrgId(query.getId());
            }
        }

        if (null != courses) {
            if ("week".equals(query.getType().trim())) {
                syllabusVos = getWeekSyllabus(courses, query.getLimitTime());
            } else {
                if ("month".equals(query.getType().trim())) {
                    syllabusVos = getMonthSyllabus(courses, query.getLimitTime());
                }
            }
        }
        return syllabusVos;
    }

    /**
     * 根据课程信息得到周课程表信息
     * @param courses
     * @param weekInfo 指定年第几月第几周(例如：2020年5月第1周),格式为: 2020-5-1
     */
    private List<SyllabusVo> getWeekSyllabus(List<Courses> courses, String weekInfo) {
        if (null == courses || courses.isEmpty()) {
            return null;
        }

        LocalDate findDateStart = null;
        if (null != weekInfo && !"".equals(weekInfo)) {
            String[] split = weekInfo.split("-");
            if (split.length != 3)
                throw new FormatIllegalException("参数格式不正确");
            int yearValue = Integer.parseInt(split[0]); //年
            int monthValue = Integer.parseInt(split[1]); //月
            int weekNo = Integer.parseInt(split[2]); //该月的第几周

            findDateStart = LocalDate.of(yearValue, monthValue, 1)
                    .with(TemporalAdjusters.dayOfWeekInMonth(weekNo-1, DayOfWeek.MONDAY));//要求查询周的第1天
        } else {
            findDateStart = LocalDate.now().with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));  //当前周的第1天
        }

        LocalDate findDateEnd = findDateStart.plusDays(7);  //下周周一

        List<SyllabusVo> vos = new ArrayList<>();
        for (Courses course : courses) {
            CourseTableExample example = new CourseTableExample();
            CourseTableExample.Criteria criteria = example.createCriteria();
            criteria.andStartTimeBetween(LocalDateTime.of(findDateStart, LocalTime.MIN), LocalDateTime.of(findDateEnd, LocalTime.MIN));
            criteria.andCourseIdEqualTo(course.getCourseId());
            List<CourseTable> tables = courseTableDao.selectByExample(example);

            for (CourseTable item : tables) {
                LocalDateTime startTime = item.getStartTime();
                String time = String.format("%d %tR-%tR", startTime.getDayOfWeek().getValue(), startTime.toLocalTime(), startTime.plusMinutes(course.getMinutes()).toLocalTime());
                vos.add(new SyllabusVo(item.getCourseId(), course.getName(), time, course.getSite(), item.getCourseNo()));
            }
        }
        return vos;
    }

    /**
     * 根据课程信息得到月课程表信息
     * @param courses
     * @param monthInfo 指定年第几月(例如：2020年5月),格式为: 2020-5
     */
    private List<SyllabusVo> getMonthSyllabus(List<Courses> courses, String monthInfo) {
        if (Objects.isNull(courses))
            return null;

        LocalDate findDateStart = null;
        if (Objects.nonNull(monthInfo) && "" != monthInfo) {
            String[] split = monthInfo.split("-");
            if (split.length != 2)
                return null;
            int yearValue = Integer.parseInt(split[0]); //年
            int monthValue = Integer.parseInt(split[1]); //月

            findDateStart = LocalDate.of(yearValue, monthValue, 1);  //要求查询月份的第1天
        } else {
            findDateStart = LocalDate.now().with(TemporalAdjusters.firstDayOfMonth());  //当前月的第1天
        }
        LocalDate findDateEnd = findDateStart.with(TemporalAdjusters.firstDayOfNextMonth());    //下月的第1天

        List<SyllabusVo> vos = new ArrayList<>();
        for (Courses course : courses) {
            CourseTableExample example = new CourseTableExample();
            CourseTableExample.Criteria criteria = example.createCriteria();
            criteria.andCourseIdEqualTo(course.getCourseId());
            criteria.andStartTimeBetween(LocalDateTime.of(findDateStart, LocalTime.MIN), LocalDateTime.of(findDateEnd, LocalTime.MIN));
            List<CourseTable> tables = courseTableDao.selectByExample(example);

            for (CourseTable item : tables) {
                LocalDateTime startTime = item.getStartTime();
                String time = String.format("%tF %tR-%tR", startTime.toLocalDate(), startTime.toLocalTime(), startTime.plusMinutes(course.getMinutes()).toLocalTime());
                vos.add(new SyllabusVo(item.getCourseId(), course.getName(), time, course.getSite(), item.getCourseNo()));
            }
        }
        return vos;
    }

}
