package com.oes.service;

import com.oes.Exceptions.database.DBOperateException;

import com.oes.common.dao.AuthenticatedUsersCommonDao;
import com.oes.common.dao.UsersCommonDao;
import com.oes.dao.TeachersDao;
import com.oes.model.dto.AuthUserDTO;
import com.oes.model.dto.BaseResultDTO;
import com.oes.model.dto.TeacherDTO;
import com.oes.model.dto.UserDTO;
import com.oes.model.entity.AuthenticatedUser;
import com.oes.model.entity.Teacher;
import com.oes.model.entity.User;
import com.oes.model.vo.teacher.TeacherVO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;


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
    private TeachersDao teachersDao;
    @Resource
    private UsersCommonDao usersCommonDao;
    @Resource
    private AuthenticatedUsersCommonDao authenticatedUsersCommonDao;

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

    /*查询教师信息*/
    public Teacher getTeacherById(long id) {
        return teachersDao.queryById(id);
    }

    private void convertTeacherToVO(List<TeacherVO> teachersvo, List<Teacher> teachers) {
        for (Teacher teacher : teachers) {
            long userid = teacher.getUserid();
            User user = usersCommonDao.queryById(userid);
            AuthenticatedUser auth = authenticatedUsersCommonDao.queryById(userid);

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
