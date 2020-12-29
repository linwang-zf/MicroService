package com.oes.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.oes.model.entity.Courses;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : yanjundong
 * @date : 2020-04-13 10:05
 * @description : 课程相关的显示层对象
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
public class CourseVo {
    private Integer courseId;

    /*课程类别*/
    private String category;

    /*机构名称*/
    private String organization;

    /*课程名称*/
    private String name;

    /*课程简介*/
    private String introduction;

    /*课程参考书*/
    private String textbook;

    private String site;

    private Integer ageStart;

    private Integer ageEnd;

    @ApiModelProperty(example = "2020-04-02")
    private LocalDate startTime;

    @ApiModelProperty(example = "2021-04-02")
    private LocalDate endTime;

    private Integer totalCount;

    private Integer weekCount;

    private Integer minutes;

    private Integer maxStuCount;

    private Integer currentStuCount;

    private Integer refundLimit;

    private Long pricePerHour;

    /*宣传照片id*/
    private Integer adverPhoto;

    /*报名开始时间*/
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(example = "2021-04-02 10:00:00")
    private LocalDateTime enrollStartTime;

    /*报名截止时间*/
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(example = "2021-04-02 10:00:00")
    private LocalDateTime enrollEndTime;

    /*课程创建时间*/
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(hidden = true)
    private LocalDateTime createTime;

    private List<String> teacher = new ArrayList<>(2);

    private List<String> assistant = new ArrayList<>(2);

    private List<String> courseTime = new ArrayList<>(6);

    @ApiModelProperty(hidden = true)
    private List<Object> teacherList = new ArrayList<>();

    @ApiModelProperty(hidden = true)
    private List<Object> assistantList = new ArrayList<>();

    @ApiModelProperty(hidden = true)
    private List<Object> categoryList = new ArrayList<>();



    public CourseVo(Courses courses) {
        this.courseId = courses.getCourseId();
        this.name = courses.getName();
        this.introduction = courses.getIntroduction();
        this.textbook = courses.getTextbook();
        this.site = courses.getSite();
        this.ageStart = courses.getAgeStart();
        this.ageEnd = courses.getAgeEnd();
        this.startTime = courses.getStartTime();
        this.endTime = courses.getEndTime();
        this.totalCount = courses.getTotalCount();
        this.weekCount = courses.getWeekCount();
        this.minutes = courses.getMinutes();
        this.maxStuCount = courses.getMaxStuCount();
        this.currentStuCount = courses.getCurrentStuCount();
        this.refundLimit = courses.getRefundLimit();
        this.pricePerHour = courses.getPricePerHour();
        this.adverPhoto = courses.getAdverPhoto();
        this.enrollStartTime = courses.getEnrollStartTime();
        this.enrollEndTime = courses.getEnrollEndTime();
        this.createTime = courses.getCreateTime();
    }
}
