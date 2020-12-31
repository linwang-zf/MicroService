package com.oes.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author : JQK
 * @date : 2020-07-05 17:38
 * @description : 课程签到查询的学生信息
 */
@Getter
@Setter
@ToString
public class CourseRollcallDTO implements Serializable {

    private static final long serialVersionUID = -1745829440210743641L;

    /*签到id*/
    private Integer id;

    /*学生id*/
    private Integer userId;

    /*学生姓名*/
    private String name;

    /*性别*/
    private int gender;

    /*手机*/
    private String phone;

    /*紧急联系人*/
    private String secondTel;

    /*mail*/
    private String mail;

    /*学校*/
    private String school;

    /*年级*/
    private int grade;

    /*qq*/
    private String qq;

    /*微信*/
    private String weixin;

    /*签到时间*/
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime rollCallTime;
}
