package com.oes.vo;

import com.oes.entity.OrganizationsStudent;
import com.oes.model.dto.UserDTO;
import com.oes.model.entity.Student;
import com.oes.model.vo.student.StudentVo;

import java.sql.Timestamp;

/**
 * 机构学生管理的VO
 */
public class OrgStudentVo {
    long student_id;
    StudentVo studentVo;
    UserDTO userInfo;
    Timestamp enroll_time;
    long internal_id;
    int courses_number;

    public OrgStudentVo(OrganizationsStudent orgStudent, Student student) {
        setStudent_id(orgStudent.getStudentId());
        setCourses_number(orgStudent.getCoursesNumber());
        setEnroll_time(orgStudent.getEnrollTime());
        setInternal_id(orgStudent.getInternalId());
        setStudentVo(new StudentVo(student));
    }

    public long getStudent_id() {
        return student_id;
    }

    public void setStudent_id(long student_id) {
        this.student_id = student_id;
    }

    public StudentVo getStudentVo() {
        return studentVo;
    }

    public void setStudentVo(StudentVo studentVo) {
        this.studentVo = studentVo;
    }

    public Timestamp getEnroll_time() {
        return enroll_time;
    }

    public void setEnroll_time(Timestamp enroll_time) {
        this.enroll_time = enroll_time;
    }

    public long getInternal_id() {
        return internal_id;
    }

    public void setInternal_id(long internal_id) {
        this.internal_id = internal_id;
    }

    public int getCourses_number() {
        return courses_number;
    }

    public void setCourses_number(int courses_number) {
        this.courses_number = courses_number;
    }

    public UserDTO getUserInfo() {
        return userInfo;
    }

    public void setUserInfo(UserDTO userInfo) {
        this.userInfo = userInfo;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"student_id\":")
                .append(student_id);
        sb.append(",\"studentVo\":")
                .append(studentVo);
        sb.append(",\"userInfo\":")
                .append(userInfo);
        sb.append(",\"enroll_time\":\"")
                .append(enroll_time).append('\"');
        sb.append(",\"internal_id\":")
                .append(internal_id);
        sb.append(",\"courses_number\":")
                .append(courses_number);
        sb.append('}');
        return sb.toString();
    }
}
