package com.oes.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.oes.model.enums.EnrollCourseStatus;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class CourseEnroll {
    private Integer id;

    private Integer courseId;

    private Long studentId;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date enrollTime;

    private EnrollCourseStatus status;

    private String fail_reason;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    public Date getEnrollTime() {
        return enrollTime;
    }

    public void setEnrollTime(Date enrollTime) {
        this.enrollTime = enrollTime;
    }

    public EnrollCourseStatus getStatus() {
        return status;
    }

    public void setStatus(EnrollCourseStatus status) {
        this.status = status;
    }

    public String getFail_reason() {
        return fail_reason;
    }

    public void setFail_reason(String fail_reason) {
        this.fail_reason = fail_reason;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"id\":")
                .append(id);
        sb.append(",\"courseId\":")
                .append(courseId);
        sb.append(",\"studentId\":")
                .append(studentId);
        sb.append(",\"enrollTime\":\"")
                .append(enrollTime).append('\"');
        sb.append(",\"status\":")
                .append(status);
        sb.append(",\"fail_reason\":\"")
                .append(fail_reason).append('\"');
        sb.append('}');
        return sb.toString();
    }
}