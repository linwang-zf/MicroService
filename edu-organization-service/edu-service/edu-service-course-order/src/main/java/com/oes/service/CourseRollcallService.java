package com.oes.service;


import com.oes.Exceptions.database.DBOperateException;
import com.oes.config.Url;
import com.oes.constant.enums.BusinessType;
import com.oes.exception.CourseNotExistsException;
import com.oes.model.dto.BaseResultDTO;
import com.oes.model.dto.CourseStudentDTO;
import com.oes.model.entity.Courses;
import com.oes.model.entity.Student;
import com.oes.util.course.CourseUtil;
import com.oes.util.datatime.DateTimeUtil;
import com.oes.util.http.HttpResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author : yanjundong
 * @date : 2020-04-25 01:04
 * @description : 课程签到
 */

@Service
@Slf4j
public class CourseRollcallService {
    private CourseRollcallDao courseRollcallDao;
    private CoursesDao coursesDao;
    private StudentsDao studentsDao;
    private CourseTableDao courseTableDao;
    @Resource
    private RestTemplate restTemplate;



    /**
     *  课程签到
     * @param stuId 签到学生id
     * @param courseId  签到课程id
     */
    public BaseResultDTO addCourseRollcal(Integer stuId, Integer courseId) {

        BaseResultDTO resultDTO = new BaseResultDTO();

        if (stuId <= 0 || courseId <= 0 )
            throw new IllegalArgumentException("参数异常");

        /*得到当前是第几次课*/
        Courses course = coursesDao.getCourseByKey(courseId);   //该课程信息
        HttpResult<Student> stuResult = restTemplate.exchange
                (Url.SERVICE_STUDENT+"/student/api/"+stuId, HttpMethod.GET, null,
                        new ParameterizedTypeReference<HttpResult<Student>>() {
                }).getBody();

        Student student = stuResult.getData();

        if (Objects.isNull(course))
            throw new CourseNotExistsException("没有查询到id为" + courseId + "的课程信息");
        if (Objects.isNull(student))
            throw new StuNotExistsException("没有查询到id为" + stuId + "的学生信息");
        LocalDate start = course.getStartTime();
        LocalDate end = course.getEndTime();
        int minutes = course.getMinutes();
        Map<Integer, List<LocalTime>> courseTimes = new HashMap<>(7);
        CourseUtil.getCourseTimeMap(course, courseTimes);

        /*第几次课*/
        int courseNo = CourseRollCallUtil.getCourseNo(start, end, courseTimes, minutes);

        if (-1 == courseNo) {
            resultDTO.setSuccess(false);
            resultDTO.setMessage("该课程已结课");
            return resultDTO;
        } else {
            if (-2 == courseNo) {
                resultDTO.setSuccess(false);
                resultDTO.setMessage("当前不在上课期间");
                return resultDTO;
            } else {
                if (0 == courseNo) {
                    resultDTO.setSuccess(false);
                    resultDTO.setMessage("该课程尚未开课");
                    return resultDTO;
                }
            }
        }

        /*开始签到工作*/
        CourseRollcall rollcall = new CourseRollcall();
        rollcall.setStudentId(stuId);
        rollcall.setCourseId(courseId);
        rollcall.setCourseNo(courseNo);
        rollcall.setState(true);
        /*判断是否已签到，避免重复签到*/
        Integer rollcallId = courseRollcallDao.getIdByInfo(rollcall);
        if (null != rollcallId && rollcallId > 0) {  //已签到
            resultDTO.setSuccess(true);
            resultDTO.setData(rollcallId);
            return resultDTO;
        }
        int row = courseRollcallDao.insert(rollcall);
        if (row > 0) {
            resultDTO.setSuccess(true);
            resultDTO.setData(row);
            return resultDTO;
        } else {
            throw new DBOperateException("数据库访问异常");
        }
    }

    /** 获取课程签到名单 */
    public BaseResultDTO getSignInRoster(int courseId, String courseTime) {

        Courses course = coursesDao.getCourseByKey(courseId);
        if (null == course)
            throw new CourseNotExistsException("没有查询到id为" + courseId + "的课程信息");

        LocalDateTime startDateTime = LocalDateTime.parse(courseTime, DateTimeFormatter.ofPattern(DateTimeUtil.DATE_TIME_FORMAT));

        LocalDate start = course.getStartTime();
        LocalDate end = course.getEndTime();
        int minutes = course.getMinutes();
        Map<Integer, List<LocalTime>> courseTimes = new HashMap<>(7);
        CourseUtil.getCourseTimeMap(course, courseTimes);

        int courseNo = CourseRollCallUtil.getCourseNo(startDateTime, start, end, courseTimes, minutes);

        Map<String, List> data = getCallAndUnCall(courseId, courseNo);


        return new BaseResultDTO(BusinessType.SELECT, true, "获取成功", data);
    }

    /** 课程的上课历史 */
    public BaseResultDTO getCourseHistory(CourseHistoryQuery query) {
        int courseId = query.getId();
        Courses course = coursesDao.getCourseByKey(courseId);
        if (null == course) {
            throw new CourseNotExistsException("没有查询到该课程信息");
        }

        List<Courses> courses = new ArrayList<>(1);
        courses.add(course);

        LocalDate startDate = null;
        LocalDate endDate = null;

        if (null != query.getLimitTime() && !"".equals(query.getLimitTime())) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");
            YearMonth yearMonth = YearMonth.parse(query.getLimitTime(), formatter);
            startDate = yearMonth.atDay(1);
            endDate = yearMonth.atEndOfMonth();

            if (endDate.isBefore(course.getStartTime()) || startDate.isAfter(course.getEndTime())) {
                throw new IllegalArgumentException("超过上课时间");
            }
        } else {
            startDate = course.getStartTime();
            endDate = LocalDate.now();
        }

        CourseTableExample example = new CourseTableExample();
        CourseTableExample.Criteria criteria = example.createCriteria();
        criteria.andCourseIdEqualTo(courseId);
        criteria.andStartTimeBetween(LocalDateTime.of(startDate, LocalTime.MIN), LocalDateTime.of(endDate, LocalTime.MIN));
        long total = courseTableDao.countByExample(example);

        if (query.getPageNum() > 0 && query.getPageSize() > 0) {
            int offset = (query.getPageNum() - 1) * query.getPageSize();
            if (offset < total) example.setOrderByClause("id limit " + offset + " ," + query.getPageSize());
        }
        List<CourseTable> tables = courseTableDao.selectByExample(example);
        List<Map<String, Object>> data = new ArrayList<>();
        for (CourseTable table : tables) {
            try {
                Map<String, List> roster = getCallAndUnCall(courseId, table.getCourseNo());
                List signInRoster = roster.get("signInRoster");
                List unSignRoster = roster.get("unSignInRoster");

                if (null != signInRoster && null != unSignRoster) {
                    HashMap<String, Object> item = new HashMap<>();
                    item.put("courseTime", table.getStartTime().format(DateTimeFormatter.ofPattern(DateTimeUtil.DATE_TIME_FORMAT)));
                    item.put("currentCount", table.getCourseNo());
                    item.put("totalCount", course.getTotalCount());
                    item.put("totalStudent", signInRoster.size() + unSignRoster.size());
                    item.put("signedStudent", signInRoster.size());
                    item.put("unsignedStudent", unSignRoster.size());
                    data.add(item);
                }
            } catch (Exception e) {
                log.error("获取上课历史失败==={}", query);
            }
        }

        TableData tableData = new TableData(total, data);
        return new BaseResultDTO(true, "查询成功", tableData);
    }

    /*得到课程某节课的签到名单和未签到名单*/
    private Map<String, List> getCallAndUnCall(int courseId, int courseNo) {
        /*查询该课程的签到名单*/
        List<CourseRollcallDTO> signInRoster = courseRollcallDao.getSignInRoster(courseId, courseNo);

        /*查询该课程的所有选修名单*/
        List<CourseStudentDTO> allStuInfo = coursesDao.getStudentInfoByCourseId(courseId);
        if (null != signInRoster && null != allStuInfo) {
            /*签到学生的id*/
            Set<Integer> rollCallStuId = signInRoster.stream().map(CourseRollcallDTO::getUserId).collect(Collectors.toSet());
            allStuInfo.removeIf(item -> rollCallStuId.contains(item.getUserId()));
        }
        Map<String, List> data = new HashMap();
        data.put("signInRoster", signInRoster);
        data.put("unSignInRoster", allStuInfo);
        return data;
    }

}
