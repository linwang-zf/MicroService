package com.oes.controller;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.oes.model.entity.CourseCategory;
import com.oes.model.dto.BaseResultDTO;
import com.oes.model.entity.Courses;
import com.oes.model.vo.teacher.TeacherVO;
import com.oes.query.CoursePageQuery;
import com.oes.service.CourseCategoryService;
import com.oes.service.CoursesService;


import com.oes.model.query.CourseQuery;
import com.oes.model.vo.CourseVo;

import com.oes.util.http.HttpResult;
import com.oes.util.http.HttpStatus;
import io.lettuce.core.dynamic.annotation.Param;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author : yanjundong
 * @date : 2020-04-10 10:21
 * @description :
 */

@RestController
@DefaultProperties(defaultFallback = "Global_FallbackMethod")
@Api(tags = {"课程信息"})
public class CoursesController {
    @Resource
    private CoursesService coursesService;
    @Resource
    private CourseCategoryService courseCategoryService;

    @PostMapping("/course/organization/{orgId}")
    @HystrixCommand
    @ApiOperation(value = "关键字模糊查询课程信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "query", value = "模糊查询参数", required = true, dataType = "CourseQuery"),
            @ApiImplicitParam(name = "orgId", value = "机构id", required = true)
    })
    public HttpResult getCourseByKeywords(@RequestBody CourseQuery query, @PathVariable Integer orgId) {
        System.out.println(query.getLimitTime().size());
        if (query.getPageNum() < 0 || query.getPageSize() < 0 || query.getTypeId() < 0 ||
                ( query.getLimitTime().size() >= 0 && query.getLimitTime().size() <= 2)) {
            return HttpResult.error("参数异常");
        }

        List<CourseVo> courses = coursesService.getCourseByKeywords(query, orgId);
        if (Objects.nonNull(courses))
            return HttpResult.ok("查询成功", courses);
        else
            return HttpResult.error("查询失败");
    }


    @PostMapping("/course/{orgId}")
    @ApiOperation(value = "新增课程")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "course", value = "新增课程信息", required = true, dataType = "CourseVo"),
            @ApiImplicitParam(name = "orgId", value = "机构id", required = true)
    })
    public HttpResult addCourse(@RequestBody CourseVo course, @PathVariable Integer orgId) {
        int row = coursesService.addCourse(course, orgId);
        if (row > 0) {
            JSONObject data = new JSONObject();
            data.put("orgId", course.getCourseId());
            return HttpResult.ok("课程新增成功", data);
        } else {
            return HttpResult.error("课程新增失败");
        }
    }


    @PutMapping("/course/{orgId}")
    @ApiOperation(value = "修改课程信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "course", value = "修改课程信息", required = true, dataType = "CourseVo"),
            @ApiImplicitParam(name = "orgId", value = "机构id", required = true)
    })
    public HttpResult updateCourse(@RequestBody CourseVo course, @PathVariable Integer orgId) {
        int row = coursesService.updateCourse(course, orgId);
        if (row > 0) {
            JSONObject data = new JSONObject();
            data.put("courseId", course.getCourseId());
            return HttpResult.ok("课程修改成功", data);
        } else {
            return HttpResult.error("课程修改失败");
        }
    }


    @DeleteMapping("/course/{orgId}/{courseId}")
    @ApiOperation(value = "删除机构的课程信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orgId", value = "机构id", required = true),
            @ApiImplicitParam(name = "courseId", value = "课程id", required = true)
    })
    public HttpResult deleteCourse(@PathVariable Integer orgId, @PathVariable Integer courseId) {
        BaseResultDTO resultDTO = coursesService.deleteCourse(orgId, courseId);
        if (resultDTO.isSuccess()) {
            return HttpResult.ok(resultDTO.getMessage());
        } else {
            return HttpResult.error(resultDTO.getMessage());
        }
    }


    @PostMapping("/course/getQueryCourse")
    @HystrixCommand
    @ApiOperation(value = "根据条件查询课程")
    public HttpResult getQueryCourse(@RequestBody CoursePageQuery courseQuery) throws ParseException {
        BaseResultDTO queryCourse = coursesService.getQueryCourse(courseQuery);
        return HttpResult.ok(queryCourse);
    }


    @GetMapping("/course/queryCourse/{courseId}")
    @ApiOperation(value = "根据id查询课程")
    public HttpResult queryCourseById(@PathVariable Integer courseId) {
        BaseResultDTO baseResultDTO = coursesService.queryCourseById(courseId);
        if (baseResultDTO.isSuccess()) {
            return HttpResult.ok(baseResultDTO.getMessage(), baseResultDTO.getData());
        } else {
            return HttpResult.error(baseResultDTO.getMessage());
        }
    }


    @GetMapping("/course/{orgId}")
    @HystrixCommand
    @ApiOperation(value = "获取机构的所有课程")
    @ApiImplicitParam(name = "orgId", value = "机构id", required = true)
    public HttpResult getAllCourse(@PathVariable Integer orgId) {
        List<CourseVo> courses = coursesService.getCourses(orgId);
        if (courses != null) {
            return HttpResult.ok("查询成功", courses);
        } else {
            return HttpResult.error("查询失败");
        }
    }


    @GetMapping("/course/organization/{orgId}/basicInfo")
    @ApiOperation(value = "课程信息模糊查询的前置信息")
    @ApiImplicitParam(name = "orgId", value = "机构id", required = true)
    public HttpResult getCourseByKeywordsBasicInfo(@PathVariable Integer orgId) {
        List<TeacherVO> teacherVOS = coursesService.queryTeacherByOrgId(orgId);
        Map<String, Object> result = new HashMap<>(2);
        JSONArray teachers = new JSONArray(teacherVOS.size());
        for (TeacherVO teacherVO : teacherVOS) {
            JSONObject teacher = new JSONObject();
            teacher.put("id", teacherVO.getBaseInfo().getUserid());
            teacher.put("name", teacherVO.getAuthInfo().getName());
            teachers.add(teacher);
        }
        List<CourseCategory> leafCategory = courseCategoryService.getLeafCategory();

        result.put("teacher", teachers);
        result.put("leafCategory", leafCategory);

        return HttpResult.ok(result);
    }

    @GetMapping("/course/{orgId}/basicInfo")
    @HystrixCommand
    @ApiOperation(value = "添加课程时的基础信息")
    @ApiImplicitParam(name = "orgId", value = "机构id", required = true)
    public HttpResult getCourseBasicInfo(@PathVariable int orgId) {
        List<Object> teachers = coursesService.getTeacherByOrgId(orgId);
        List<Map<String, Object>> options = courseCategoryService.getCategoryTree();

        JSONObject result = new JSONObject();
        result.put("teacher", teachers);
        result.put("options", options);
        return HttpResult.ok(result);
    }

    public HttpResult Global_FallbackMethod(){
        return HttpResult.error(HttpStatus.SC_REQUEST_TIMEOUT,"请求超时，请稍后再试");
    }

}
