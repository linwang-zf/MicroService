package com.oes.controller;


import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.oes.dto.EnrollCoursesDTO;
import com.oes.model.enums.EnrollCourseStatus;
import com.oes.model.vo.CourseVo;
import com.oes.service.CoursesService;
import com.oes.service.StuEnrollCourseService;
import com.oes.util.http.HttpResult;
import com.oes.util.http.HttpStatus;
import com.oes.vo.EnrollCoursesVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * @author : JQK
 * @date : 2020-07-22 11:14
 * @description :
 */
@Api(tags = {"学生选修课程"})
@DefaultProperties(defaultFallback = "Global_FallbackMethod")
@RestController
public class StuEnrollCourseController {

    private final StuEnrollCourseService stuEnrollCourseService;
    private final CoursesService coursesService;

    @Autowired
    public StuEnrollCourseController(final StuEnrollCourseService stuEnrollCourseService, final CoursesService coursesService) {
        this.stuEnrollCourseService = stuEnrollCourseService;
        this.coursesService = coursesService;
    }


    @GetMapping("/student/{orgId}/course/enrollment")
    @ApiOperation(" 获取未处理的学生选课")
    public HttpResult getUncheckedCourses(@PathVariable String orgId) {
        List<EnrollCoursesVO> uncheckedCourses = stuEnrollCourseService.getUncheckedCourses(Long.valueOf(orgId));
        if (uncheckedCourses == null) return HttpResult.error("获取失败");
        else return HttpResult.ok("获取成功", uncheckedCourses);
    }

    @GetMapping("/student/{userId}/course/{courseId}/enrollment")
    @ApiOperation("学生选课确认/拒绝")
    public HttpResult confirmEnrollCourse(@PathVariable String userId, @PathVariable String courseId,
                                          @RequestParam boolean pass,
                                          @RequestParam(required = false) String fail_reason) {
        boolean b = stuEnrollCourseService.confirmCourses(Long.valueOf(userId), Long.valueOf(courseId), pass, fail_reason);
        if (b) return HttpResult.ok("确认成功", true);
        else return HttpResult.error("确认失败");
    }

    @PostMapping("/student/{userId}/course/{courseId}/application")
    @ApiOperation("学生发起选课申请")
    public HttpResult applyForCourse(@PathVariable String userId, @PathVariable String courseId) {
        boolean b = stuEnrollCourseService.applyForCourse(Long.valueOf(userId), Integer.valueOf(courseId));
        if (b) return HttpResult.ok("申请成功", true);
        else return HttpResult.error("申请失败");
    }

    @PostMapping("/student/{userId}/course/{courseId}")
    @ApiOperation("管理员帮学生选修某一课程")
    public HttpResult enrollCourse(@PathVariable String userId, @PathVariable String courseId) {
        boolean b = stuEnrollCourseService.enrollCourse(Long.valueOf(userId), Integer.valueOf(courseId));
        if (b) return HttpResult.ok("选修成功", true);
        else return HttpResult.error("选修失败");
    }

    @DeleteMapping("/student/{userId}/course/{courseId}")
    @ApiOperation("学生取消选修某课程")
    public HttpResult cancelCourse(@PathVariable String userId, @PathVariable String courseId) {
        boolean b = stuEnrollCourseService.cancelCourse(Long.valueOf(userId), Integer.valueOf(courseId));
        if (b) return HttpResult.ok("取消成功", true);
        else return HttpResult.error("取消失败");
    }

    @GetMapping("/student/{orgId}/{userId}/course/option")
    @HystrixCommand
    @ApiOperation("获取已经开始且该学生尚未选修过的所有课程")
    public HttpResult getCourseOptionByStuId(@PathVariable Integer orgId, @PathVariable Integer userId) {
        List<CourseVo> courses = coursesService.getCourseOptionByStuId(orgId, userId);
        if (Objects.nonNull(courses))
            return HttpResult.ok("查询成功", courses);
        else
            return HttpResult.error("查询失败");
    }


    /******************************对外提供接口********************/
    //shopping
    @GetMapping("/student/api/apply")
    public boolean isApplied(@PathVariable  Integer userId, @PathVariable Integer courseId){
        return stuEnrollCourseService.isApplied(userId, courseId);
    }

    public HttpResult Global_FallbackMethod(){
        return HttpResult.error(HttpStatus.SC_REQUEST_TIMEOUT,"请求超时，请稍后再试");
    }

}
