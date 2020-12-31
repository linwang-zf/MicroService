package com.oes.entity;


import com.oes.vo.OrgStudentVo;
import io.swagger.annotations.ApiModel;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * (OrganizationsStudent)实体类
 *
 * @author makejava
 * @since 2020-03-27 14:40:13
 */
@ApiModel(value = "OrganizationsStudent",description = "机构学生关联模型")
public class OrganizationsStudent implements Serializable {
    private static final long serialVersionUID = 340487669482258973L;
    
    private long organization;
    
    private long studentId;
    
    private Timestamp enrollTime;
    /**
     * 机构的内部学号，机构之间可以重复
     */
    private long internalId;
    
    private int coursesNumber;

    public OrganizationsStudent() {
    }

    public OrganizationsStudent(OrgStudentVo studentVo) {
        setInternalId(studentVo.getInternal_id());
        setEnrollTime(studentVo.getEnroll_time());
        setCoursesNumber(studentVo.getCourses_number());
    }

    public long getOrganization() {
        return organization;
    }

    public void setOrganization(long organization) {
        this.organization = organization;
    }

    public long getStudentId() {
        return studentId;
    }

    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }

    public Timestamp getEnrollTime() {
        return enrollTime;
    }

    public void setEnrollTime(Timestamp enrollTime) {
        this.enrollTime = enrollTime;
    }

    public long getInternalId() {
        return internalId;
    }

    public void setInternalId(long internalId) {
        this.internalId = internalId;
    }

    public int getCoursesNumber() {
        return coursesNumber;
    }

    public void setCoursesNumber(int coursesNumber) {
        this.coursesNumber = coursesNumber;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"organization\":")
                .append(organization);
        sb.append(",\"studentId\":")
                .append(studentId);
        sb.append(",\"enrollTime\":\"")
                .append(enrollTime).append('\"');
        sb.append(",\"internalId\":")
                .append(internalId);
        sb.append(",\"coursesNumber\":")
                .append(coursesNumber);
        sb.append('}');
        return sb.toString();
    }
}