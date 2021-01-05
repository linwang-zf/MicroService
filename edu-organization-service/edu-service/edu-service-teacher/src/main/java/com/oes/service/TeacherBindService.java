package com.oes.service;

import com.oes.Exceptions.database.DBOperateException;
import com.oes.config.Url;
import com.oes.dao.TeachersDao;
import com.oes.model.dto.BaseResultDTO;
import com.oes.model.entity.AuthenticatedUser;
import com.oes.model.entity.Teacher;
import com.oes.util.http.HttpResult;
import com.oes.util.http.HttpStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @author : JQK
 * @date : 2020-07-22 10:06
 * @description :
 */
@Service
@Slf4j
public class TeacherBindService {
    @Resource
    private TeachersDao teachersDao;
    @Resource
    private RestTemplate restTemplate;




    public BaseResultDTO attachTeacher(Long teaId, long orgId) {
        HttpResult result = new HttpResult();
        int insert = 0;
        HttpResult<AuthenticatedUser> authResult = restTemplate.exchange(Url.SERVICE_USERCENTER+"/AuthenticatedUser/"+teaId, HttpMethod.GET,null,
                new ParameterizedTypeReference<HttpResult<AuthenticatedUser>>() {
                }).getBody();
        AuthenticatedUser auth = authResult.getData();
        if (auth == null) {
            return new BaseResultDTO(false, "您还没有实名认证");
        }

        Teacher teacher1 = teachersDao.queryById(teaId);
        if (teacher1 == null) {
            return new BaseResultDTO(false, "您还未登记教师信息");
        } else if (teacher1.getOrganizationId() != -1 && teacher1.getChecked() == 1) {
            return new BaseResultDTO(false, "您已挂靠过机构");
        }

        try {
            Teacher teacher = new Teacher();
            teacher.setUserid(teaId);
            teacher.setOrganizationId(orgId);
            teacher.setChecked(0);
            insert = teachersDao.update(teacher);
        } catch (Exception e) {
            log.error("教师挂靠请求错误，教师id：{}，机构id：{}", teaId, orgId);
            throw new DBOperateException("挂靠信息存储错误");
        }

        return new BaseResultDTO(true, "申请挂靠成功");
    }

    public HttpResult deleteAttach(Integer teaId) {
        HttpResult result = new HttpResult();
        int insert = 0;
        try {
            Teacher teacher = new Teacher();
            teacher.setUserid(teaId);
            /*解决挂靠后机构id置为-1*/
            teacher.setOrganizationId(-1);
            teacher.setChecked(-1);
            insert = teachersDao.update(teacher);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (insert == 1) {
            result.setMsg("解除挂靠成功");
        } else {
            result.setCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            result.setMsg("解除挂靠失败");
        }
        return result;
    }

    /** 教师是否挂靠机构*/
    public BaseResultDTO getTeacherVOById(long id) {
        Teacher teacher = teachersDao.queryById(id);
        if (null == teacher) {
            return new BaseResultDTO(false, "没有查询到该教师信息");
        }
        if (teacher.getOrganizationId() > 0 && (teacher.getChecked() == 0 || teacher.getChecked() == 1)) {
            String msg = null;
            if (teacher.getChecked() == 0)
                msg = "正在审核中";
            else
                msg = "已挂靠";
            return new BaseResultDTO(true, msg, teacher.getOrganizationId());
        } else {
            return new BaseResultDTO(true, "未挂靠");
        }
    }

    /**
     * 管理员确认教师挂靠请求
     */
    public BaseResultDTO confirmTeacherAttach(long teacherId, boolean pass, String reason) {
        Teacher teacher = teachersDao.queryById(teacherId);
        if (Objects.isNull(teacher)) {
            return new BaseResultDTO(false, "请确定该教师信息是否存在");
        }
        if (pass) {
            teacher.setChecked(1);
        } else {
            teacher.setChecked(2);
            teacher.setFail_reason(reason);
        }
        int row = teachersDao.update(teacher);
        if (row > 0) {
            log.info("id为{}的教师挂靠{}", teacherId, (pass) ? "成功" : ("失败，原因为：" + reason));
            return new BaseResultDTO(true, (pass) ? "挂靠成功" : "挂靠已拒绝");
        } else {
            throw new DBOperateException("挂靠失败，数据库处理错误");
        }
    }

}
