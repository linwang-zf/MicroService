package com.oes.model.dto;

import lombok.Data;

@Data
public class CoursesDTO {
    private Integer orgId;
    private Integer courseId;
    private String startTime;
    private String endTime;
    private String courseName;
    private String orgName;
    private Integer teacher1id;
    private Integer teacher2id;
    private Integer assistant1id;
    private Integer assistant2id;
    private String course_time1;
    private String course_time2;
    private String course_time3;
    private String course_time4;
    private String course_time5;
    private String course_time6;
}
