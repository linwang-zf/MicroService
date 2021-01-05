package com.oes.service;

import com.alibaba.fastjson.JSONObject;
import com.oes.Exceptions.database.DBOperateException;
import com.oes.config.Url;
import com.oes.dao.TeachersDao;
import com.oes.model.dto.AuthUserDTO;
import com.oes.model.dto.BaseResultDTO;
import com.oes.model.dto.TeacherDTO;
import com.oes.model.dto.UserDTO;
import com.oes.model.entity.AuthenticatedUser;
import com.oes.model.entity.Role;
import com.oes.model.entity.Teacher;
import com.oes.model.entity.User;
import com.oes.model.enums.Gender;
import com.oes.model.vo.teacher.TeacherVO;
import com.oes.util.http.HttpResult;
import com.oes.validator.ValidationResult;
import com.oes.validator.ValidatorImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author : yanjundong
 * @date : 2020-03-30 17:09
 * @description :
 */

@Service
public class TeacherService {



    @Resource
    TeachersDao teachersDao;
    @Resource
    RestTemplate restTemplate;

    //引入自定义的校验器
    @Autowired
    private ValidatorImpl validator;


    public List<Teacher> getTeachers(int page, int size) {
        int start = (page - 1) * size;
        List<Teacher> teachers = teachersDao.queryAllByLimit(start, size);
        return teachers;
    }


    public List<TeacherVO> queryTeacherByOrgId(long orgId) {
        List<TeacherVO> teachersvo = new ArrayList<>();
        List<Teacher> teachers = teachersDao.queryByOrgId(orgId);
        convertTeacherToVO(teachersvo, teachers);
        return teachersvo;
    }

    public Teacher getTeacherById(long id) {
        return teachersDao.queryById(id);
    }

    public int updateTeacher(Teacher teacher) {
        int i;
        try {
            i = teachersDao.update(teacher);
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        return i;
    }

    @Transactional
    public boolean insertTeacher(Teacher teacher) {
        boolean b;
        int row;
        try {
            teacher.setChecked(1);
            row = teachersDao.insert(teacher);
            long userid = teacher.getUserid();
            JSONObject object = new JSONObject();
            object.put("userId",userid);
            object.put("roleName","teacher");
            b = restTemplate.postForObject(Url.SERVICE_USERCENTER+"/user/api/roleName",object,boolean.class);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return row == 1 && b;
    }

    /** 机构未审核的教师 */
    public BaseResultDTO getUncheckedByOrgId(Integer orgId) {
        if (orgId < 0) {
            throw new IllegalArgumentException("没有该机构信息");
        }
        List<Teacher> teachers = teachersDao.queryUncheckedByOrgId(orgId);
        if (null == teachers) {
            throw new DBOperateException("数据查询错误");
        }
        List<TeacherVO> teachersvo = new ArrayList<>();
        convertTeacherToVO(teachersvo, teachers);
        return new BaseResultDTO(true, "查询成功", teachersvo);
    }

    private void convertTeacherToVO(List<TeacherVO> teachersvo, List<Teacher> teachers) {
        for (Teacher teacher : teachers) {
            long userid = teacher.getUserid();
            //TODO
            HttpResult<JSONObject> result = restTemplate.exchange
                    (Url.SERVICE_USERCENTER+"/user/auth/api/"+userid, HttpMethod.GET, null,
                            new ParameterizedTypeReference<HttpResult<JSONObject>>() {
                            }).getBody();
            User user = (User) result.getData().get("user");
            AuthenticatedUser auth = (AuthenticatedUser) result.getData().get("auth");
            TeacherDTO teacherDTO = new TeacherDTO();
            UserDTO userDTO = new UserDTO();
            AuthUserDTO authUserDTO = new AuthUserDTO();

            if (null != teacher)
                BeanUtils.copyProperties(teacher, teacherDTO);
            if (null != user)
                BeanUtils.copyProperties(user, userDTO);
            if (null != auth)
                BeanUtils.copyProperties(auth, authUserDTO);

            TeacherVO teacherVO = new TeacherVO(userDTO, authUserDTO, teacherDTO);
            teachersvo.add(teacherVO);
        }
    }


}
