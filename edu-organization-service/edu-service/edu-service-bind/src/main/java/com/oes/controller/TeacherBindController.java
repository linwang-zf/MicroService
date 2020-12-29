package com.oes.controller;


import com.oes.model.dto.BaseResultDTO;
import com.oes.model.entity.Teacher;
import com.oes.service.TeacherBindService;
import com.oes.service.TeacherService;
import com.oes.util.http.HttpResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author : JQK
 * @date : 2020-07-22 10:04
 * @description :
 */
@RestController
@Api(tags = {"教师挂靠"})
public class TeacherBindController {
    @Resource
    private TeacherBindService teacherBindService;
    @Resource
    private TeacherService teacherService;


    @GetMapping("/teacher/{orgId}/unchecked")
    @ApiOperation("按机构id获取全部未审核教师")
    public HttpResult getUncheckedByOrgId(@PathVariable Integer orgId) {
        BaseResultDTO resultDTO = teacherService.getUncheckedByOrgId(orgId);
        if (resultDTO.isSuccess()) {
            return HttpResult.ok(resultDTO.getMessage(), resultDTO.getData());
        } else {
            return HttpResult.error(resultDTO.getMessage());
        }
    }

    @GetMapping("/teacher/organization/{orgId}")
    @ApiOperation("教师申请挂靠机构")
    public HttpResult teacherAttach(@PathVariable(value = "orgId") long orgId, @RequestParam(required = true) Long teaId) {
        BaseResultDTO resultDTO = teacherBindService.attachTeacher(teaId, orgId);
        if (resultDTO.isSuccess()) {
            return HttpResult.ok(resultDTO.getMessage());
        } else {
            return HttpResult.error(resultDTO.getMessage());
        }
    }

    @DeleteMapping("/teacher/organization/{teaId}")
    @ApiOperation("教师申请解除挂靠")
    public HttpResult deleteAttach(@PathVariable Integer teaId) {
        return teacherBindService.deleteAttach(teaId);
    }

    @GetMapping("/teacher/organization")
    @ApiOperation("根据id查询教师是否挂靠机构")
    public HttpResult getTeacherOrg(@RequestParam long userId) {
        BaseResultDTO resultDTO = teacherBindService.getTeacherVOById(userId);
        if (resultDTO.isSuccess()) {
            return HttpResult.ok(resultDTO.getMessage(), resultDTO.getData());
        } else {
            return HttpResult.error(resultDTO.getMessage());
        }
    }

    @GetMapping("/teacher/process")
    @ApiOperation("教师审核进度查询")
    public HttpResult getProcess(@RequestParam long userId) {
        Teacher teacher = teacherService.getTeacherById(userId);
        int checked = teacher.getChecked();
        long orgId = teacher.getOrganizationId();
        Map<String, Object> data = new HashMap<>();
        data.put("checked", checked);
        data.put("orgId", orgId);
        if (checked == 0) return HttpResult.ok("未审核", data);
        else if (checked == 1) return HttpResult.ok("审核成功", data);
        else if (checked == 2) return HttpResult.ok("审核失败", data);
        else if (checked == -1) return HttpResult.ok("未挂靠", checked);
        else return HttpResult.error("查询失败");
    }

    @GetMapping("/organization/teacher/confirmation")
    @ApiOperation("确认教师挂靠请求")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "teacherId", value = "教师id", required = true),
            @ApiImplicitParam(name = "pass", value = "是否通过", required = true, dataType = "boolean"),
            @ApiImplicitParam(name = "reason", value = "原因")
    })
    public HttpResult confirmTeacherAttach(@RequestParam long teacherId, @RequestParam boolean pass,
                                           @RequestParam(required = false) String reason) {
        BaseResultDTO resultDTO = teacherBindService.confirmTeacherAttach(teacherId, pass, reason);
        if (resultDTO.isSuccess()) {
            return HttpResult.ok(resultDTO.getMessage());
        } else {
            return HttpResult.error(resultDTO.getMessage());
        }
    }

}
