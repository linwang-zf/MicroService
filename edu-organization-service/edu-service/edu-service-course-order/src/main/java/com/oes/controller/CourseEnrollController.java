package com.oes.controller;


import com.oes.model.dto.CourseStudentDTO;
import com.oes.service.CoursesService;
import com.oes.util.http.HttpResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author : JQK
 * @date : 2020-07-22 9:50
 * @description :
 */
@Api(tags = {"课程选修-试听"})
@RestController
public class CourseEnrollController {
    @Resource
    private CoursesService coursesService;


    @GetMapping("/course/{orgId}/option/student/{stuId}")
    @ApiOperation(value = "获取已经开始且该学生尚未选修过的课程==简短信息")
    public HttpResult getAllUnEnrollCourseByStuId(@PathVariable Integer orgId, @PathVariable Integer stuId) {
        List<Map<String, Object>> options = coursesService.getAllUnEnrollCourseByStuId(orgId, stuId);
        if (null != options)
            return HttpResult.ok("查询成功", options);
        else
            return HttpResult.error("获取信息失败");
    }

    @GetMapping("/course/{courseId}/student")
    @ApiOperation(value = "获取课程的选修名单")
    @ApiImplicitParam(name = "courseId", value = "课程id", required = true)
    public HttpResult getStudentInfoByCourseId(@PathVariable Integer courseId) {
        if (courseId == 0) {
            return HttpResult.error("参数错误");
        }

        List<CourseStudentDTO> stus = coursesService.getStudentInfoByCourseId(courseId);
        return HttpResult.ok(stus);
    }

    @GetMapping("/course/{courseId}/trialstudent")
    @ApiOperation(value = "获取课程的试听名单")
    @ApiImplicitParam(name = "courseId", value = "课程id", required = true)
    public HttpResult getTryStudentInfoByCourseId(@PathVariable Integer courseId) {
        if (courseId == 0) {
            return HttpResult.error("参数错误");
        }

        List<CourseStudentDTO> tryStus = coursesService.getTryStudentInfoByCourseId(courseId);
        return HttpResult.ok(tryStus);
    }

}
