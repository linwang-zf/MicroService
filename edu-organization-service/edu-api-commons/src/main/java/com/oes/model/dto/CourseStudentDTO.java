package com.oes.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author : yanjundong
 * @date : 2020-04-19 08:30
 * @description : 学生选修课程的 dto 模型,侧重于学生的相关信息
 */

@Getter
@Setter
@ToString
public class CourseStudentDTO {
    private int userId;

    private String name;

    private int gender;

    private String phone;

    private String secondTel;

    private String mail;

    private String school;

    private int grade;

    private String qq;

    private String weixin;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date enrollTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date trialTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date reservationTime;

    @Override
    public boolean equals(Object obj) {
        CourseStudentDTO b = (CourseStudentDTO)obj;
        return userId == b.getUserId();
    }
}
