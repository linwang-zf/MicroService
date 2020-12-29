package com.oes.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.oes.model.dto.BaseResultDTO;
import com.oes.model.enums.Gender;
import com.oes.model.query.OrganizationQuery;
import com.oes.model.vo.student.StudentVo;
import com.oes.query.StudentQuery;

import com.oes.service.StudentService;
import com.oes.util.http.HttpResult;
import com.oes.vo.OrgStudentVo;
import com.oes.vo.StudentPreInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@RestController
@Api(tags = {"学生管理"})
public class StudentController {
  @Resource
  private StudentService studentService;





    @GetMapping("/student/{stuId}")
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

   /* @PostMapping("/student/{user_id}")
    @ApiOperation("学生信息补充登记")
    public HttpResult addStudent(@PathVariable String user_id, @RequestBody StudentVo student) throws Exception {
        BaseResultDTO resultDTO = studentService.addStudent(Long.valueOf(user_id), student);
        if (resultDTO.isSuccess()) {
            return HttpResult.ok(resultDTO.getMessage());
        } else {
            return HttpResult.error(resultDTO.getMessage());
        }
    }

*//*    @PostMapping("/student/{org_id}/manual")
    @ApiOperation("手动录入学生信息")
    public HttpResult addStudentManual(@PathVariable String org_id, @RequestBody ManualStudentDTO studentDTO) {
        long id = orgStuService.addStudentManual(Long.valueOf(org_id), studentDTO);
        if (id > 0) {
            JSONObject object = new JSONObject();
            object.put("userId", id);
            return HttpResult.ok("添加成功", object);
        } else return HttpResult.error("添加失败");
    }*//*

    @DeleteMapping("/student/{user_id}")
    @ApiOperation("学生注销账号")
    public HttpResult deleteStudent(@PathVariable String user_id) {
        boolean b = studentService.deleteStudent(Long.valueOf(user_id));
        if (b) return HttpResult.ok("删除成功", true);
        else return HttpResult.error("删除失败");
    }

    @GetMapping("/student/organization/{org_id}")
    @ApiOperation("获取一个机构的学生(可添加删选条件,当条件为空时，即获取全部学生)")
    //FIXME 该接口需要修改
    public HttpResult getAllStuById(@PathVariable String org_id, @ApiParam("姓名") @RequestParam(required = false) String name,
                                    @ApiParam("性别") @RequestParam(required = false) Gender gender,
                                    @ApiParam("起始出生年月") @RequestParam(required = false) Date startBirth,
                                    @ApiParam("终止出生年月") @RequestParam(required = false) Date endBirth,
                                    @ApiParam("起始报名时间") @RequestParam(required = false) Timestamp startEnrollTime,
                                    @ApiParam("终止报名时间") @RequestParam(required = false) Timestamp endEnrollTime,
                                    @ApiParam("手机号") @RequestParam(required = false) String phone) {
        List<OrgStudentVo> students = orgStuService.getStudents(Long.valueOf(org_id), name, gender, startBirth, endBirth, startEnrollTime, endEnrollTime, phone);
        if (students == null) return HttpResult.error();
        else if (students.isEmpty()) return HttpResult.ok("机构暂无学生", students);
        else return HttpResult.ok("查询成功", students);
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
    @ApiOperation("获取该学生挂靠的全部机构")
    public HttpResult getAllOrgByStu(@PathVariable String user_id) {
        JSONArray allOrg = orgStuService.getAllOrg(Long.valueOf(user_id));
        if (allOrg == null) return HttpResult.error("查询失败");
        else if (allOrg.isEmpty()) return HttpResult.ok("该学生尚未挂靠任何机构", allOrg);
        else return HttpResult.ok("查询成功", allOrg);
    }

    @PostMapping("/student/unEnroll/organizations/{user_id}")
    @ApiOperation("获取该学生未挂靠的全部机构")
    public HttpResult getUnEnrollOrgByStu(@PathVariable int user_id, @RequestBody OrganizationQuery query) {
        BaseResultDTO resultDTO = orgStuService.getUnEnrollOrgByStu(user_id, query);
        if (resultDTO.isSuccess()) {
            return HttpResult.ok(resultDTO.getMessage(), resultDTO.getData());
        } else {
            return HttpResult.error(resultDTO.getMessage());
        }
    }

    @PostMapping("/platAdmin/QureyStudent")
    @ApiOperation("平台管理员查询学生")
    public HttpResult queryStudents(@RequestBody StudentQuery studentQuery){
        BaseResultDTO baseResultDTO = studentService.queryStudents(studentQuery);
        if(baseResultDTO.isSuccess()){
            return HttpResult.ok(baseResultDTO.getMessage(),baseResultDTO.getData());
        }else {
            return HttpResult.error(baseResultDTO.getMessage());
        }
    }
*/
}
