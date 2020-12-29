package com.oes.controller;


import com.oes.query.SyllabusQuery;
import com.oes.service.CourseTableService;
import com.oes.util.http.HttpResult;
import com.oes.vo.SyllabusVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * @author : JQK
 * @date : 2020-07-22 9:40
 * @description :
 */
@RestController
@Api(tags = {"课程表生成"})
public class CourseTableController {
    @Resource
    private CourseTableService courseTableService;


    @PostMapping("/course/syllabus")
    @ApiOperation(value = "课程表生成")
    @ApiImplicitParam(name = "query", value = "课程表的查询参数", dataType = "SyllabusQuery")
    public HttpResult getCourseTableByQuery(@RequestBody SyllabusQuery query) {
        List<SyllabusVo> syllabus = courseTableService.getCourseTableByQuery(query);
        if (Objects.nonNull(syllabus)) {
            return HttpResult.ok(syllabus);
        } else {
            return HttpResult.error("课程表生成失败");
        }
    }

}
