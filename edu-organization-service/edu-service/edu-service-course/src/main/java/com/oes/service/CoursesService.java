package com.oes.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.oes.Exceptions.database.DBOperateException;
import com.oes.common.dao.UsersCommonDao;
import com.oes.constant.enums.BusinessType;
import com.oes.dao.*;
import com.oes.model.entity.*;
import com.oes.entity.CourseTable;
import com.oes.model.dto.*;
import com.oes.model.vo.teacher.TeacherVO;
import com.oes.query.CoursePageQuery;
import com.oes.model.query.CourseQuery;
import com.oes.util.CourseRollCallUtil;
import com.oes.util.CourseUtil;
import com.oes.vo.CourseByQueryVO;
import com.oes.model.vo.CourseVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author : yanjundong
 * @date : 2020-04-10 09:58
 * @description : 课程信息管理 service
 */

@Service
public class CoursesService {
    private final Logger logger = LoggerFactory.getLogger(CoursesService.class);
    @Resource
    private CoursesDao coursesDao;
    @Resource
    private CourseCategory courseCategory;
    @Resource
    private AuthenticatedUsersDao authenticatedUsersDao;
    @Resource
    private OrganizationsDao organizationsDao;
    @Resource
    private CourseCategoryDao courseCategoryDao;
    @Resource
    private CourseTableDao courseTableDao;
    @Resource
    private UsersCommonDao usersCommonDao;

    /**
     * 通过关键字查找课程信息
     */
    public List<CourseVo> getCourseByKeywords(CourseQuery query, Integer orgId) {
        List<Courses> courses = coursesDao.getCourseByKeywords(query, orgId);
        if (Objects.isNull(courses)) {
            throw new DBOperateException("数据查询错误");
        }
        List<CourseVo> courseVoList = new ArrayList<>(courses.size());
        transform(courseVoList, courses);
        return courseVoList;
    }

    /**
     * 新增某一个机构的课程
     * @param courseVo
     * @return
     */
    public int addCourse(CourseVo courseVo, Integer orgId) {
        Courses course = new Courses(courseVo);

        if (null == courseVo.getOrganization())
            course.setOrganizationId(orgId);
            System.out.println(course.getOrganizationId());
        try {
            int row = coursesDao.addCourse(course);
            if (row > 0) {
                courseVo.setCourseId(course.getCourseId());
                logger.info("id为{}的机构新增课程成功，课程名称为{}", orgId, courseVo.getName());
                generateCourseTable(course);
            }
        } catch(Exception e) {
            logger.error("id为{}的机构新增课程失败，课程名称为{}", orgId, courseVo.getName());
            return -1;
        }
        return course.getCourseId();
    }

    /**
     * 更新课程信息
     */
    public int updateCourse(CourseVo courseVo, Integer orgId) {
        Courses course = new Courses(courseVo);
        if (null == courseVo.getOrganization())
            course.setOrganizationId(orgId);
        int row = coursesDao.updateCourse(course);
        if (row > 0) {
            courseVo.setCourseId(course.getCourseId());
            logger.info("id为{}的机构修改课程成功，课程名称为{}", orgId, courseVo.getName());
        } else {
            logger.error("id为{}的机构修改课程失败，课程名称为{}", orgId, courseVo.getName());
        }
        return row;
    }

    /**
     * 删除某个机构的某个课程信息
     */
    public BaseResultDTO deleteCourse(Integer orgId, Integer courseId) {
        Courses course = coursesDao.getCourseByKey(courseId);
        if (null == course)
            return new BaseResultDTO(BusinessType.DELETE, false, "没有查询到该课程信息");
        int size = coursesDao.getStudentInfoByCourseId(courseId).size();
        if (course.getCurrentStuCount() > 0 || size > 0) {
            return new BaseResultDTO(BusinessType.DELETE, false, "还有学生选修该课程，暂不能删除");
        }
        int row = coursesDao.deleteCourse(courseId);
        if (row > 0) {
            logger.info("id为{}的机构删除课程成功，课程id为{}", orgId, courseId);
            return new BaseResultDTO(BusinessType.DELETE, true, "删除成功");
        } else {
            throw new DBOperateException("数据库操作错误");
        }
    }

    /*根据条件查询课程信息*/
    public BaseResultDTO getQueryCourse(CoursePageQuery courseQuery) throws ParseException {
        PageHelper.startPage(courseQuery.getPageNum(), courseQuery.getPageSize());
        List<CoursesDTO> courses = coursesDao.getQueryCourse(courseQuery);
        List<String> limitTime = courseQuery.getLimitTime();
        if (limitTime != null && limitTime.size() != 0) {
            List<CoursesDTO> afterRemoveCourses = new ArrayList<>();
            for (int i = 0; i < courses.size(); i++) {
                if (courses.get(i).getCourse_time1() != null && isInTime(limitTime, courses.get(i).getCourse_time1())) {
                    afterRemoveCourses.add(courses.get(i));
                }
                if (courses.get(i).getCourse_time2() != null && isInTime(limitTime, courses.get(i).getCourse_time2())) {
                    afterRemoveCourses.add(courses.get(i));
                }
                if (courses.get(i).getCourse_time3() != null && isInTime(limitTime, courses.get(i).getCourse_time3())) {
                    afterRemoveCourses.add(courses.get(i));
                }
                if (courses.get(i).getCourse_time4() != null && isInTime(limitTime, courses.get(i).getCourse_time4())) {
                    afterRemoveCourses.add(courses.get(i));
                }
                if (courses.get(i).getCourse_time5() != null && isInTime(limitTime, courses.get(i).getCourse_time5())) {
                    afterRemoveCourses.add(courses.get(i));
                }
                if (courses.get(i).getCourse_time6() != null && isInTime(limitTime, courses.get(i).getCourse_time6())) {
                    afterRemoveCourses.add(courses.get(i));
                }
            }
            PageInfo<CoursesDTO> courseList = new PageInfo<>(afterRemoveCourses);
            List<CourseByQueryVO> courseByQueryVOS = transCourseDTO2VO(courseList.getList());
            PageInfo<CourseByQueryVO> result = new PageInfo<>(courseByQueryVOS);
            BeanUtils.copyProperties(courseList,result);
            result.setList(courseByQueryVOS);
            return new BaseResultDTO(true,result);
        }
        PageInfo<CoursesDTO> courseList = new PageInfo<>(courses);
        List<CourseByQueryVO> courseByQueryVOS = transCourseDTO2VO(courseList.getList());
        PageInfo<CourseByQueryVO> result = new PageInfo<>(courseByQueryVOS);
        BeanUtils.copyProperties(courseList,result);
        result.setList(courseByQueryVOS);
        return new BaseResultDTO(true,result);
    }

    /**
     * 根据id查询课程
     */
    public BaseResultDTO queryCourseById(Integer id) {
        Courses course = coursesDao.getCourseByKey(id);
        if (course == null) {
            return new BaseResultDTO(false, "课程不存在");
        } else {
            return new BaseResultDTO(true, "查询成功",course);
        }
    }

    /**
     * 获取该机构所有的课程信息
     */
    public List<CourseVo> getCourses(Integer orgId) {
        List<Courses> courses = coursesDao.getCoursesByOrgId(orgId);
        if (Objects.isNull(courses)) {
            throw new DBOperateException("数据查询错误");
        }
        List<CourseVo> courseVoList = new ArrayList<>(courses.size());

        transform(courseVoList, courses);
        /* 按照课程的创建时间倒序 */
        courseVoList.sort((o1, o2) -> {
            LocalDateTime createTime1 = o1.getCreateTime();
            LocalDateTime createTime2 = o2.getCreateTime();
            return (createTime1.isBefore(createTime2)) ? 1 : -1;
        });
        return courseVoList;
    }


    /**
     * 根据 机构id 查询到教师基本信息
     *
     * @param orgId
     * @return
     */
    public List<Object> getTeacherByOrgId(Integer orgId) {
        List<Object> teacherInfo = new ArrayList<>();

        List<Teacher> teachers = coursesDao.queryByOrgId(orgId);
        for (Teacher teacher : teachers) {
            AuthenticatedUser user = authenticatedUsersDao.queryById(teacher.getUserid());
            if (Objects.nonNull(user)) {
                Map tea = new HashMap(2);
                tea.put("id", user.getUserid());
                tea.put("name", user.getName());
                teacherInfo.add(tea);
            }
        }

        return teacherInfo;

    }

    /*********************************其他微服务模块使用到course中API接口******************* */
//    public

    /***********************************************************************************/
    public List<TeacherVO> queryTeacherByOrgId(long orgId) {
        List<TeacherVO> teachersvo = new ArrayList<>();
        List<Teacher> teachers = coursesDao.queryByOrgId(orgId);
        convertTeacherToVO(teachersvo, teachers);
        return teachersvo;
    }

    /***********************************************************************************/
    @Async
    /** 异步任务生成课表*/
    public void generateCourseTable(Courses course) {
        logger.info("=============开始生成id为{}的课程表信息=============", course.getCourseId());

        List<CourseTable> tables = new LinkedList<>();
        Map<Integer, List<LocalTime>> courseTimes = new HashMap<>(7);
        CourseUtil.getCourseTimeMap(course, courseTimes);

        LocalDate startDate = course.getStartTime();
        LocalDate endDate = course.getEndTime();

        if (!courseTimes.isEmpty()) {
            for (LocalDate temp = startDate; !temp.isAfter(endDate); temp = temp.plusDays(1)) {
                List<LocalTime> localTimes = courseTimes.get(temp.getDayOfWeek().getValue());   //星期几的所有上课时间
                if (null != localTimes) {
                    for (LocalTime item : localTimes) {
                        LocalDateTime startTime = LocalDateTime.of(temp,item);
                        int courseNo = CourseRollCallUtil.getCourseNo(startTime, startDate, endDate, courseTimes, course.getMinutes());

                        tables.add(new CourseTable(null, course.getCourseId(), startTime, courseNo));
                        if (tables.size() % 16 == 0) {
                            courseTableDao.insertBatch(tables);
                            tables.clear();
                        }
                    }
                }
            }
            if (!tables.isEmpty()) courseTableDao.insertBatch(tables);
        }
        logger.info("=============id为{}的课程表信息成功生成=============", course.getCourseId());
    }

    /*Courses 转化为 CourseVo*/
    private void transform(List<CourseVo> courseVoList, List<Courses> courses) {
        /*得到所有认证用户，然后过滤得到课程教师信息*/
        List<AuthenticatedUser> users = authenticatedUsersDao.queryAll();

        for (Courses course : courses) {
            CourseVo courseVo = new CourseVo(course);
            /*课程的教师*/
            List<AuthenticatedUser> collect = users.stream()
                    .filter(user -> (null != course.getTeacher1Id() && user.getUserid() == course.getTeacher1Id()) ||
                            (null != course.getTeacher2Id() && user.getUserid() == course.getTeacher2Id()) ||
                            (null != course.getAssistant1Id() && user.getUserid() == course.getAssistant1Id()) ||
                            (null != course.getAssistant2Id() && user.getUserid() == course.getAssistant2Id()))
                    .collect(Collectors.toList());
            if (course.getTeacher1Id() != null) {
                collect.forEach(item -> {
                    if (item.getUserid() == course.getTeacher1Id()) {
                        Map teacher = new HashMap();
                        teacher.put("teacher1Id", item.getUserid());
                        teacher.put("name", item.getName());
                        courseVo.getTeacherList().add(teacher);
                    }
                });
            }
            if (course.getTeacher2Id() != null) {
                collect.forEach(item -> {
                    if (item.getUserid() == course.getTeacher2Id()) {
                        Map teacher = new HashMap();
                        teacher.put("teacher2Id", item.getUserid());
                        teacher.put("name", item.getName());
                        courseVo.getTeacherList().add(teacher);
                    }
                });
            }
            if (course.getAssistant1Id() != null) {
                collect.forEach(item -> {
                    if (item.getUserid() == course.getAssistant1Id()) {
                        Map assistant = new HashMap();
                        assistant.put("assistant1Id", item.getUserid());
                        assistant.put("name", item.getName());
                        courseVo.getAssistantList().add(assistant);
                    }
                });
            }
            if (course.getAssistant2Id() != null) {
                collect.forEach(item -> {
                    if (item.getUserid() == course.getAssistant2Id()) {
                        Map assistant = new HashMap();
                        assistant.put("assistant2Id", item.getUserid());
                        assistant.put("name", item.getName());
                        courseVo.getAssistantList().add(assistant);
                    }
                });
            }
            /*课程上课时间*/
            CourseUtil.getCourseTimeList(course, courseVo.getCourseTime());

            /*课程的机构*/
            String organization = organizationsDao.queryById(course.getOrganizationId()).getName();
            courseVo.setOrganization(organization);

            /*课程列别*/
            CourseCategory courseCategory = null;
            int categoryId = course.getTypeId();
            int[] ids = new int[3];
            String[] names = new String[3];
            int index = 0;

            while (categoryId != -1) {
                courseCategory = courseCategoryDao.queryById(categoryId);
                ids[index] = courseCategory.getCategoryId();
                names[index] = courseCategory.getName();
                index++;
                categoryId = courseCategory.getParentId();
            }
            for (int i = ids.length - 1; i >= 0; i--) {
                Map category = new HashMap();
                category.put("id", ids[i]);
                category.put("name", names[i]);
                courseVo.getCategoryList().add(category);
            }
            courseVoList.add(courseVo);
        }
    }

    private boolean isInTime(List<String> limitTime, String timeStr) throws ParseException {
        List<Date> datetimes = new ArrayList<>();
        DateFormat sdf = new SimpleDateFormat("mm:ss");
        for (String s : limitTime) {
            Date datetime = sdf.parse(s);
            datetimes.add(datetime);
        }
        String[] split = timeStr.split("-");
        if(split.length == 2) {
            Date datetime = sdf.parse(split[1]);
            return !datetime.after(datetimes.get(1)) && !datetime.before(datetimes.get(0));
        }
        return false;
    }

    private List<CourseByQueryVO> transCourseDTO2VO(List<CoursesDTO> dtos) {
        List<CourseByQueryVO> vos = new ArrayList<>();
        for (CoursesDTO dto : dtos) {
            CourseByQueryVO vo = new CourseByQueryVO();
            BeanUtils.copyProperties(dto, vo);
            List<String> courseTimes = new ArrayList<>();
            if (dto.getCourse_time1() != null) {
                courseTimes.add(transWeekDays(dto.getCourse_time1()));
            }
            if (dto.getCourse_time2() != null) {
                courseTimes.add(transWeekDays(dto.getCourse_time2()));
            }
            if (dto.getCourse_time3() != null) {
                courseTimes.add(transWeekDays(dto.getCourse_time3()));
            }
            if (dto.getCourse_time4() != null) {
                courseTimes.add(transWeekDays(dto.getCourse_time4()));
            }
            if (dto.getCourse_time5() != null) {
                courseTimes.add(transWeekDays(dto.getCourse_time5()));
            }
            if (dto.getCourse_time6() != null) {
                courseTimes.add(transWeekDays(dto.getCourse_time6()));
            }
            vo.setCourseTimes(courseTimes);
            List<Integer> teacherIds = Arrays.asList(dto.getTeacher1id(),dto.getTeacher2id());
            List<Integer> assistantIds = Arrays.asList(dto.getAssistant2id(), dto.getAssistant1id());
            List<String> teachers = transId2Name(teacherIds);
            List<String> assistants = transId2Name(assistantIds);
            vo.setTeachers(teachers);
            vo.setAssistants(assistants);
            vos.add(vo);
        }
        return vos;
    }

    private String transWeekDays(String courseTime) {
        String weekDay = "";
        String[] split = courseTime.split("-");
        if (split.length == 2) {
            String weekday = courseTime.split("-")[0];
            String time = courseTime.split("-")[1];
            switch (weekday) {
                case "1": weekDay = "星期一"+time; break;
                case "2": weekDay = "星期二"+time;; break;
                case "3": weekDay = "星期三"+time;; break;
                case "4": weekDay = "星期四"+time;; break;
                case "5": weekDay = "星期五"+time;; break;
                case "6": weekDay = "星期六"+time;; break;
                case "7": weekDay = "星期日"+time;; break;
            }
        }
        return weekDay;
    }

    private List<String> transId2Name(List<Integer> ids) {
        List<AuthenticatedUser> authenticatedUsers = authenticatedUsersDao.queryNamesByIds(ids);
        HashMap<Integer, String> map = new HashMap<>();
        for (AuthenticatedUser authenticatedUser : authenticatedUsers) {
            map.put(authenticatedUser.getUserid(), authenticatedUser.getName());
        }
        List<String> names = new ArrayList<>();
        for (Integer id : ids) {
            if (map.get(id) != null) {
                names.add(map.get(id));
            }
        }
        return names;
    }


    //teacher
    private void convertTeacherToVO(List<TeacherVO> teachersvo, List<Teacher> teachers) {
        for (Teacher teacher : teachers) {
            long userid = teacher.getUserid();
            User user = usersCommonDao.queryById(userid);
            AuthenticatedUser auth = authenticatedUsersDao.queryById(userid);

            TeacherDTO teacherDTO = new TeacherDTO();
            UserDTO userDTO = new UserDTO();
            AuthUserDTO authUserDTO = new AuthUserDTO();

            if (null != teacher)
                BeanUtils.copyProperties(teacher, teacherDTO);
            if (null != user)
                BeanUtils.copyProperties(user, userDTO);
            if (null != auth)
                BeanUtils.copyProperties(auth, authUserDTO);

            TeacherVO teacherVO = new TeacherVO(userDTO, authUserDTO, teacherDTO);
            teachersvo.add(teacherVO);
        }
    }


}
