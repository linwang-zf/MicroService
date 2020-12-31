package com.oes.service;

import com.oes.Exceptions.ServiceException;
import com.oes.Exceptions.common.RepeatOperationException;
import com.oes.dao.CourseEnrollDao;
import com.oes.dao.CoursesDao;
import com.oes.dao.OrganizationsStudentsDao;
import com.oes.dto.EnrollCoursesDTO;
import com.oes.entity.CourseEnroll;
import com.oes.entity.OrganizationsStudent;
import com.oes.exception.CourseNotExistsException;
import com.oes.model.entity.Courses;
import com.oes.model.entity.Organization;
import com.oes.model.enums.EnrollCourseStatus;
import com.oes.model.vo.CourseTeacherVo;
import com.oes.vo.EnrollCoursesVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

/**
 * @author : JQK
 * @date : 2020-07-22 11:15
 * @description :
 */
@Service
public class StuEnrollCourseService {
    @Resource
    private CourseEnrollDao courseEnrollDao;
    @Resource
    private  OrganizationsStudentsDao organizationsStudentsDao;
    @Resource
    private  CoursesDao coursesDao;






    public List<EnrollCoursesVO> getUncheckedCourses(Long orgId) {
        return courseEnrollDao.getUncheckedEnrollCourses(orgId);
    }

    @Transactional
    public boolean confirmCourses(Long userId, long courseId, boolean pass, String fail_reason) {
        CourseEnroll courseEnroll = courseEnrollDao.queryByUserIdAndCourseId(userId, courseId);
        if (courseEnroll == null) throw new ServiceException("没有userId为" + userId + ",courseId为" + courseId + "的选课申请");
        if (pass) {
            courseEnroll.setStatus(EnrollCourseStatus.success);
            Courses course = coursesDao.getCourseByKey(courseId);
            OrganizationsStudent organizationsStudent = organizationsStudentsDao.queryById(course.getOrganizationId(), userId);
            organizationsStudent.setCoursesNumber(organizationsStudent.getCoursesNumber() + 1);
            int updateNumber = organizationsStudentsDao.update(organizationsStudent);
            Courses updateCourse = new Courses();
            updateCourse.setCourseId((int)courseId);
            Integer currentStuCount = course.getCurrentStuCount() + 1;
            updateCourse.setCurrentStuCount(currentStuCount);
            coursesDao.updateCourse(updateCourse);
        } else {
            courseEnroll.setStatus(EnrollCourseStatus.failed);
            courseEnroll.setFail_reason(fail_reason);
        }
        int updateStatus = courseEnrollDao.updateByPrimaryKeySelective(courseEnroll);
        return updateStatus == 1;
    }

    public boolean applyForCourse(Long userId, int courseId) {
        Courses course = coursesDao.getCourseByKey(courseId);
        CourseEnroll enroll = courseEnrollDao.queryByUserIdAndCourseId(userId, courseId);

        if (course == null) throw new CourseNotExistsException("该课程不存在");
        if (enroll != null) throw new RepeatOperationException("该学生已选修该课程");

        CourseEnroll courseEnroll = new CourseEnroll();
        courseEnroll.setEnrollTime(Date.valueOf(LocalDate.now()));
        courseEnroll.setCourseId(courseId);
        courseEnroll.setStudentId(userId);
        courseEnroll.setStatus(EnrollCourseStatus.unchecked);
        int insert = courseEnrollDao.insert(courseEnroll);
        return insert == 1;
    }

    @Transactional
    public boolean enrollCourse(Long userId, int courseId) {

        Courses course = coursesDao.getCourseByKey(courseId);

        if (course == null) throw new ServiceException("该课程不存在");
        CourseEnroll enroll = courseEnrollDao.queryByUserIdAndCourseId(userId, courseId);
        if (enroll != null) throw new ServiceException("该学生已选修该课程");

        OrganizationsStudent organizationsStudent = organizationsStudentsDao.queryById(course.getOrganizationId(), userId);
        if (organizationsStudent == null)
            throw new ServiceException("该学生未在该机构内，无法选修该机构的课程");
        organizationsStudent.setCoursesNumber(organizationsStudent.getCoursesNumber() + 1);

        int update = organizationsStudentsDao.update(organizationsStudent);

        CourseEnroll courseEnroll = new CourseEnroll();
        courseEnroll.setEnrollTime(Date.valueOf(LocalDate.now()));
        courseEnroll.setCourseId(courseId);
        courseEnroll.setStudentId(userId);
        courseEnroll.setStatus(EnrollCourseStatus.success);
        int insert = courseEnrollDao.insert(courseEnroll);

        Courses updateCourse = new Courses();
        updateCourse.setCourseId(courseId);
        Integer currentStuCount = course.getCurrentStuCount() + 1;
        updateCourse.setCurrentStuCount(currentStuCount);
        coursesDao.updateCourse(updateCourse);
        return insert == 1 && update == 1;
    }

    @Transactional
    public boolean cancelCourse(Long userId, int courseId) {
        int delete = courseEnrollDao.deleteByStuIdAndCourseId(userId, courseId);
        Courses course = coursesDao.getCourseByKey(courseId);
        if (course == null) throw new ServiceException("该课程不存在");
        OrganizationsStudent organizationsStudent = organizationsStudentsDao.queryById(course.getOrganizationId(), userId);
        if (organizationsStudent == null)
            throw new ServiceException("该学生未在该机构内，无法取消该机构的课程");

        organizationsStudent.setCoursesNumber(organizationsStudent.getCoursesNumber() - 1);
        int update = organizationsStudentsDao.update(organizationsStudent);
        return delete == 1 && update == 1;
    }

    /**
     * 检查该课程是否已选修
     * @param userId
     * @param courseId
     * @return
     */
    public boolean isApplied(Integer userId, Integer courseId) {
        CourseEnroll enroll = courseEnrollDao.queryByUserIdAndCourseId(userId, courseId);
        if (enroll != null) {
            return true;
        }
        return false;
    }



}
