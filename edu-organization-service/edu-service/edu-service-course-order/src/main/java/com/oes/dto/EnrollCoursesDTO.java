package com.oes.dto;

import com.oes.model.enums.EnrollCourseStatus;
import com.oes.model.vo.CourseTeacherVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;

/**
 * 用于查询一个学生的选修课程
 */

public class EnrollCoursesDTO {
    private long courseId;
    private long courseOrgId;
    private String orgName;
    private String name;
    private Date startTime;
    private Date endTime;

    private CourseTeacherVo teacherList;
    private CourseTeacherVo assistantList;

    private EnrollCourseStatus status;
    private String fail_reason;

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public long getCourseOrgId() {
        return courseOrgId;
    }

    public void setCourseOrgId(long courseOrgId) {
        this.courseOrgId = courseOrgId;
    }

    public long getCourseId() {
        return courseId;
    }

    public void setCourseId(long courseId) {
        this.courseId = courseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public CourseTeacherVo getTeacherList() {
        return teacherList;
    }

    public void setTeacherList(CourseTeacherVo teacherList) {
        this.teacherList = teacherList;
    }

    public CourseTeacherVo getAssistantList() {
        return assistantList;
    }

    public void setAssistantList(CourseTeacherVo assistantList) {
        this.assistantList = assistantList;
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
        sb.append("\"courseId\":")
                .append(courseId);
        sb.append(",\"courseOrgId\":")
                .append(courseOrgId);
        sb.append(",\"name\":\"")
                .append(name).append('\"');
        sb.append(",\"startTime\":\"")
                .append(startTime).append('\"');
        sb.append(",\"endTime\":\"")
                .append(endTime).append('\"');
        sb.append(",\"teacherList\":")
                .append(teacherList);
        sb.append(",\"assistantList\":")
                .append(assistantList);
        sb.append(",\"status\":")
                .append(status);
        sb.append(",\"fail_reason\":\"")
                .append(fail_reason).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
