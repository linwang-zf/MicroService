package com.oes.controller;


import com.oes.model.dto.BaseResultDTO;
import com.oes.service.CourseRollcallService;
import com.oes.util.http.HttpResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.oes.query.CourseHistoryQuery;

/**
 * @author : yanjundong
 * @date : 2020-04-25 01:16
 * @description : 课程签到
 */

@RestController
@Api(tags = {"课程签到-签到名单"})
public class CourseRollcallController {

    private CourseRollcallService courseRollcallService;

    @Autowired
    public CourseRollcallController(final CourseRollcallService courseRollcallService) {
        this.courseRollcallService = courseRollcallService;
    }

    @GetMapping("/student/{userId}/course/{courseId}/sign")
    @ApiOperation(value = "课程签到")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "学生id", required = true),
            @ApiImplicitParam(name = "courseId", value = "课程id", required = true)
    })
    public HttpResult addCourseRollcal(@PathVariable Integer userId, @PathVariable Integer courseId) {
        BaseResultDTO resultDTO = courseRollcallService.addCourseRollcal(userId, courseId);
        if (resultDTO.isSuccess()) {
            return HttpResult.ok("签到成功");
        } else {
            return HttpResult.error(resultDTO.getMessage());
        }
    }

    @GetMapping("/course/{courseId}/signIn/roster")
    @ApiOperation(value = "课程签到名单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "courseId", value = "课程id", example = "16", required = true),
            @ApiImplicitParam(name = "courseTime", value = "上课时间返回", example = "2020-04-24 14:30-18:00", required = true),
    })
    public HttpResult getSignInRoster(@PathVariable int courseId, @RequestParam String courseTime) {
        BaseResultDTO resultDTO = courseRollcallService.getSignInRoster(courseId, courseTime);

        if (resultDTO.isSuccess()) {
            return HttpResult.ok(resultDTO.getMessage(), resultDTO.getData());
        } else {
            return HttpResult.ok(resultDTO.getMessage());
        }
    }

    @ApiOperation(value = "课程的上课历史")
    @PostMapping("/course/history")
    @ApiImplicitParam(name = "query", value = "查询参数", dataType = "CourseHistoryQuery")
    public HttpResult getCourseHistory(@RequestBody CourseHistoryQuery query) {

        BaseResultDTO resultDTO = courseRollcallService.getCourseHistory(query);
        if (resultDTO.isSuccess()) {
            return HttpResult.ok(resultDTO.getMessage(), resultDTO.getData());
        } else {
            return HttpResult.ok(resultDTO.getMessage());
        }
    }

}
