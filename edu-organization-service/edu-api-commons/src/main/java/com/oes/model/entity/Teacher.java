package com.oes.model.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * (Teacher)实体类
 *
 * @author makejava
 * @since 2020-03-27 14:40:14
 */
@ApiModel(value = "Teacher",description = "教师模型")
@Getter
@Setter
@ToString
public class Teacher implements Serializable {
    private static final long serialVersionUID = 358064255738717430L;
    @Excel(name = "教师编号", orderNum = "0")
    private long userid;
    @Excel(name = "机构编号", orderNum = "1")
    private long organizationId;
    
    private long photoId;
    
    private long adverPhoto;
    
    private String briefIntro;
    
    private String homepage;
    
    private String weibo;
    /**
    * 以逗号隔开的关键字
    */
    private String keywords;
    @Excel(name = "毕业院校",orderNum = "2")
    private String gradSchool;
    @Excel(name = "学历", orderNum = "3")
    private String education;
    @Excel(name = "学位", orderNum = "4")
    private String degree;
    @Excel(name = "学历证书编号", orderNum = "5")
    private String gradCertId;
    
    private long gradCertPhoto;
    @Excel(name = "学位证书编号", orderNum = "6")
    private String degreeCertId;

    private long degreeCertPhoto;
    @Excel(name = "第二学位", orderNum = "7")
    private String degree2;
    @Excel(name = "第二学位证书编号", orderNum = "8")
    private String degree2CertId;
    
    private long degree2CertPhoto;
    
    private int courseType1Id;
    
    private int courseType2Id;
    
    private int courseType3Id;
    
    private int courseType4Id;
    
    private int courseType5Id;
    
    private int trainNum;  //问题
    
    private int awardsNum;
    
    private int workNum;
    
    private int orgCommentNum;
    
    private int stuCommentNum;
    /**
    * 0-不是，1-是
    */
    @Excel(name= "是否全职",replace = {"是_true","否_false"},orderNum = "9")
    private boolean fullTime;
    @Excel(name= "审核状态",replace = {"未挂靠_-1", "未审核_0","审核成功_1","审核失败_2"},orderNum = "10")
    private int checked;
    @Excel(name = "审核失败原因", orderNum = "11")
    private String fail_reason;
}