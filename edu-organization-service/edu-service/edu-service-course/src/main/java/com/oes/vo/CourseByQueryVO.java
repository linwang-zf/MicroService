package com.oes.vo;

import lombok.Data;

import java.util.List;

@Data
public class CourseByQueryVO {
    private Integer orgId;
    private Integer courseId;
    private String startTime;
    private String endTime;
    private String courseName;
    private String orgName;
    private List<String> teachers;
    private List<String> assistants;
    private List<String> courseTimes;
}
