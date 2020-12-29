package com.oes.model.entity;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * (OrganizationWorker)实体类
 *
 * @author makejava
 * @since 2020-03-27 14:25:56
 */
@ApiModel(value = "OrganizationWorker",description = "机构员工的模型")
@Getter
@Setter
@ToString
public class OrganizationWorker implements Serializable {
    private static final long serialVersionUID = 782593917375269348L;
    
    private long userid;
    
    private long organizationId;
    
    private int role;
    
    private int roleSubLevel;//enum?

    /*入职时间*/
    private LocalDate entryTime;
    
    private String standbyPhone;
    
    private String relativeName;
    
    private String relationship;
    
    private String relativePhone;
    
    private long wageCardId;

    private int checked;

    private String fail_reason;
}