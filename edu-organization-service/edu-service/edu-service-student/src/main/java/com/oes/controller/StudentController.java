package com.oes.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.oes.dto.ManualStudentDTO;
import com.oes.model.dto.BaseResultDTO;
import com.oes.model.entity.Student;
import com.oes.model.vo.student.StudentVo;
import com.oes.service.OrgStuService;
import com.oes.service.StudentService;
import com.oes.util.http.HttpResult;
import com.oes.util.http.HttpStatus;
import com.oes.vo.StudentPreInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@DefaultProperties(defaultFallback = "Global_FallbackMethod")
@Api(tags = {"学生管理"})
public class StudentController {
  @Resource
  private StudentService studentService;
  @Resource
  private OrgStuService orgStuService;

    @GetMapping("/student/{stuId}")
    @HystrixCommand
    @ApiOperation("通过id获取学生信息")
    public HttpResult getStudentById(@PathVariable Integer stuId) throws IllegalAccessException {
        BaseResultDTO resultDTO = studentService.getStudentById(stuId);
        if (resultDTO.isSuccess()) {
            return HttpResult.ok(resultDTO.getMessage(), resultDTO.getData());
        } else {
            return HttpResult.error(resultDTO.getMessage());
        }
    }

    @GetMapping("/student/preInfo/{user_id}")
    @ApiOperation("获取学生前置信息")
    public HttpResult getStudentPreInfo(@PathVariable long user_id) {
        StudentPreInfo studentPreInfo = studentService.getStudentPreInfo(user_id);
        return HttpResult.ok("查询成功", studentPreInfo);
    }

    //TODO 工作量问题
    @PostMapping("/student/{user_id}")
    @HystrixCommand
    @ApiOperation("学生信息补充登记")
    public HttpResult addStudent(@PathVariable String user_id, @RequestBody StudentVo student) throws Exception {
        BaseResultDTO resultDTO = studentService.addStudent(Long.valueOf(user_id), student);
        if (resultDTO.isSuccess()) {
            return HttpResult.ok(resultDTO.getMessage());
        } else {
            return HttpResult.error(resultDTO.getMessage());
        }
    }

    @PostMapping("/student/{org_id}/manual")
    @HystrixCommand
    @ApiOperation("手动录入学生信息")
    public HttpResult addStudentManual(@PathVariable String org_id, @RequestBody ManualStudentDTO studentDTO) {
        long id = orgStuService.addStudentManual(Long.valueOf(org_id), studentDTO);
        if (id > 0) {
            JSONObject object = new JSONObject();
            object.put("userId", id);
            return HttpResult.ok("添加成功", object);
        } else return HttpResult.error("添加失败");
    }
    public HttpResult error(@PathVariable String org_id, @RequestBody ManualStudentDTO studentDTO){
        return HttpResult.error("请稍后再试");
    }





    @GetMapping("/student/organization/{org_id}/{user_id}")
    @ApiOperation("学生向机构报名")
    public HttpResult attachOrg(@PathVariable String user_id, @PathVariable String org_id) {
        BaseResultDTO resultDTO = orgStuService.addStudent(Long.valueOf(user_id), Long.valueOf(org_id));
        if (resultDTO.isSuccess()) {
            return HttpResult.ok(resultDTO.getMessage());
        } else {
            return HttpResult.error(resultDTO.getMessage());
        }
    }

    @DeleteMapping("/student/organization/{org_id}/{user_id}")
    @ApiOperation("学生解除挂靠某机构")
    public HttpResult unAttach(@PathVariable String user_id, @PathVariable String org_id) {
        boolean b = orgStuService.deleteStudent(Long.valueOf(org_id), Long.valueOf(user_id));
        if (b) return HttpResult.ok("解除挂靠成功", true);
        else return HttpResult.error("解除挂靠失败");
    }

    @GetMapping("/student/organizations/{user_id}")
    @HystrixCommand
    @ApiOperation("获取该学生挂靠的全部机构")
    public HttpResult getAllOrgByStu(@PathVariable String user_id) {
        JSONArray allOrg = orgStuService.getAllOrg(Long.valueOf(user_id));
        if (allOrg == null) return HttpResult.error("查询失败");
        else if (allOrg.isEmpty()) return HttpResult.ok("该学生尚未挂靠任何机构", allOrg);
        else return HttpResult.ok("查询成功", allOrg);
    }


    /*********************************** 对外暴露API**********/
    @GetMapping("/student/api/{stuId}")
    @ApiOperation("对服务间暴露的API，通过stuId获取student信息")
    public HttpResult getStuInfoById(@PathVariable Integer stuId){
        Student student = studentService.getStuInfoById(stuId);
        if (student != null) {
            return HttpResult.ok("查询成功", student);
        } else {
            return HttpResult.error("查找失败");
        }
    }

    public HttpResult Global_FallbackMethod(){
        return HttpResult.error(HttpStatus.SC_REQUEST_TIMEOUT,"请求超时，请稍后再试");
    }
}
