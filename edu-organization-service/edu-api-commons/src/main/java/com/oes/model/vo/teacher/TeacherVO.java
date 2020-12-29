package com.oes.model.vo.teacher;

import com.oes.model.dto.AuthUserDTO;
import com.oes.model.dto.TeacherDTO;
import com.oes.model.dto.UserDTO;

/*
* 1、edu-service-bind
* 2、edu-service-course
* */
public class TeacherVO {
    UserDTO baseInfo;
    AuthUserDTO authInfo;
    TeacherDTO teacherInfo;

    public TeacherVO() {
    }

    public TeacherVO(UserDTO baseInfo, AuthUserDTO authInfo, TeacherDTO teacherInfo) {
        this.baseInfo = baseInfo;
        this.authInfo = authInfo;
        this.teacherInfo = teacherInfo;
    }

    public UserDTO getBaseInfo() {
        return baseInfo;
    }

    public void setBaseInfo(UserDTO baseInfo) {
        this.baseInfo = baseInfo;
    }

    public AuthUserDTO getAuthInfo() {
        return authInfo;
    }

    public void setAuthInfo(AuthUserDTO authInfo) {
        this.authInfo = authInfo;
    }

    public TeacherDTO getTeacherInfo() {
        return teacherInfo;
    }

    public void setTeacherInfo(TeacherDTO teacherInfo) {
        this.teacherInfo = teacherInfo;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"baseInfo\":")
                .append(baseInfo);
        sb.append(",\"authInfo\":")
                .append(authInfo);
        sb.append(",\"teacherInfo\":")
                .append(teacherInfo);
        sb.append('}');
        return sb.toString();
    }
}
