package com.oes.model.entity;

import io.swagger.annotations.ApiModel;

import java.io.Serializable;

/**
 * (OrganizationCourse)实体类
 *
 * @author makejava
 * @since 2020-03-27 14:38:11
 */
@ApiModel(value = "OrganizationCourse",description = "机构课程模型")
public class OrganizationCourse implements Serializable {
    private static final long serialVersionUID = -15977018168343207L;
    
    private long organizationId;
    
    private int courseCategoryId;
    
    private String notes;


    public long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(long organizationId) {
        this.organizationId = organizationId;
    }

    public int getCourseCategoryId() {
        return courseCategoryId;
    }

    public void setCourseCategoryId(int courseCategoryId) {
        this.courseCategoryId = courseCategoryId;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

}