package com.oes.controller;


import com.oes.model.dto.BaseResultDTO;
import com.oes.model.entity.Teacher;
import com.oes.model.enums.Gender;
import com.oes.model.vo.teacher.TeacherVO;
import com.oes.query.TeacherQuery;
import com.oes.service.TeacherService;
import com.oes.util.http.HttpResult;
import com.oes.util.http.HttpStatus;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author : yanjundong
 * @date : 2020-03-30 13:24
 * @description :
 */

@RestController
@Api(tags = {"教师管理"})
public class TeachersController {

    @Resource
    private  TeacherService teacherService;



    @GetMapping("/teacher")
    @ApiOperation("根据机构id查询全部已通过审核教师")
    public HttpResult queryTeachers(@RequestParam long orgId) {
        List<TeacherVO> teachers = teacherService.queryTeacherByOrgId(orgId);
        if (null != teachers) {
            return HttpResult.ok("查询成功", teachers);
        } else {
            return HttpResult.error("查询失败");
        }
    }

    @GetMapping("/teacher/{id}")
    @ApiOperation("个人信息查看")
    public HttpResult getTeacherById(@ApiParam(value = "教师的用户ID", example = "2") @PathVariable long id) {

        HttpResult result = new HttpResult();
        Teacher teacher = teacherService.getTeacherById(id);

        if (teacher != null) {
            result.setData(teacher);
            result.setMsg("查询成功");
        } else {
            result.setCode(HttpStatus.SC_BAD_REQUEST);
            result.setMsg("查询失败");
        }
        return result;
    }

    @PostMapping("/teacher")
    @ApiOperation("教师信息补充登记")
    public HttpResult insertTeacher(@RequestBody Teacher teacher) {
        boolean b = teacherService.insertTeacher(teacher);
        if (b) {
            return HttpResult.ok("信息补充完成!");
        }
        return HttpResult.error("信息补充失败!");
    }

    @PutMapping("/teacher")
    @ApiOperation("教师基本信息修改")
    public HttpResult updateTeacher(@RequestBody Teacher teacher) {
        int row = teacherService.updateTeacher(teacher);
        if (row > 0) {
            return HttpResult.ok("修改成功!");
        }

        return HttpResult.error("修改失败!");
    }

}
