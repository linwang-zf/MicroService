package com.oes.vo;

import com.fasterxml.jackson.annotation.JsonFormat;

import com.oes.model.enums.EnrollCourseStatus;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * 用于查询未审核选修课程的模型
 */
@Getter
@Setter
public class EnrollCoursesVO {
    private Integer courseId;

    private String courseName;

    private Long studentId;

    private String studentName;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date enrollTime;

    private EnrollCourseStatus status;

    private String fail_reason;
}
