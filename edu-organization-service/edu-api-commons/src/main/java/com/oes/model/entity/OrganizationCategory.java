package com.oes.model.entity;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * (OrganizationCategory)实体类
 *
 * @author makejava
 * @since 2020-03-27 14:20:40
 */
@ApiModel(value = "OrganizationCategory",description = "机构类别模型")
@Getter
@Setter
public class OrganizationCategory implements Serializable {
    private static final long serialVersionUID = -96978873750161107L;
    
    private int orgCategoryId;
    
    private String name;
}