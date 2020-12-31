package com.oes.entity;

import java.util.Date;

public class CourseRollcall {
    private Integer rollcallId;

    private Integer courseId;

    private Integer studentId;

    private Integer courseNo;

    private Date rollcallTime;

    private Boolean state;

    private String remark;

    public Integer getRollcallId() {
        return rollcallId;
    }

    public void setRollcallId(Integer rollcallId) {
        this.rollcallId = rollcallId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getCourseNo() {
        return courseNo;
    }

    public void setCourseNo(Integer courseNo) {
        this.courseNo = courseNo;
    }

    public Date getRollcallTime() {
        return rollcallTime;
    }

    public void setRollcallTime(Date rollcallTime) {
        this.rollcallTime = rollcallTime;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}