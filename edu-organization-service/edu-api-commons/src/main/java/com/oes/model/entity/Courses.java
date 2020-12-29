package com.oes.model.entity;


import com.oes.model.vo.CourseVo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class Courses {

    private Integer courseId;

    private Integer typeId;

    private Integer organizationId;

    private String name;

    private String introduction;

    private String textbook;

    private String site;

    private Integer ageStart;

    private Integer ageEnd;

    private Integer teacher1Id;

    private Integer teacher2Id;

    private Integer assistant1Id;

    private Integer assistant2Id;

    private LocalDate startTime;

    private LocalDate endTime;

    private Integer totalCount;

    private Integer weekCount;

    private Integer minutes;

    private String courseTime1;

    private String courseTime2;

    private String courseTime3;

    private String courseTime4;

    private String courseTime5;

    private String courseTime6;

    private Integer maxStuCount;

    private Integer currentStuCount;

    private Integer refundLimit;

    private Long pricePerHour;

    /*宣传照片id*/
    private Integer adverPhoto;

    /*报名开始时间*/
    private LocalDateTime enrollStartTime;

    /*报名截止时间*/
    private LocalDateTime enrollEndTime;

    /*课程创建时间*/
    private LocalDateTime createTime;

    public Courses() {}

    public Courses(CourseVo courseVo) {
        this.courseId = courseVo.getCourseId();
        this.name = courseVo.getName();
        this.introduction = courseVo.getIntroduction();
        this.textbook = courseVo.getTextbook();
        this.site = courseVo.getSite();
        this.ageStart = courseVo.getAgeStart();
        this.ageEnd = courseVo.getAgeEnd();
        this.startTime = courseVo.getStartTime();
        this.endTime = courseVo.getEndTime();
        this.totalCount = courseVo.getTotalCount();
        this.weekCount = courseVo.getWeekCount();
        this.minutes = courseVo.getMinutes();
        this.maxStuCount = courseVo.getMaxStuCount();
        this.currentStuCount = courseVo.getCurrentStuCount();
        this.refundLimit = courseVo.getRefundLimit();
        this.pricePerHour = courseVo.getPricePerHour();
        this.adverPhoto = courseVo.getAdverPhoto();
        this.enrollStartTime = courseVo.getEnrollStartTime();
        this.enrollEndTime = courseVo.getEnrollEndTime();
        //主讲教师
        if (courseVo.getTeacher().size() > 0)
            this.setTeacher1Id(Integer.parseInt(courseVo.getTeacher().get(0)));
        if (courseVo.getTeacher().size() > 1)
            this.setTeacher2Id(Integer.parseInt(courseVo.getTeacher().get(1)));
        //辅助教师
        if (courseVo.getAssistant().size() > 0)
            this.setAssistant1Id(Integer.parseInt(courseVo.getAssistant().get(0)));
        if (courseVo.getAssistant().size() > 1)
            this.setAssistant2Id(Integer.parseInt(courseVo.getAssistant().get(1)));
        //上课时间
        if (courseVo.getCourseTime().size() > 0)
            this.setCourseTime1(courseVo.getCourseTime().get(0));
        if (courseVo.getCourseTime().size() > 1)
            this.setCourseTime2(courseVo.getCourseTime().get(1));
        if (courseVo.getCourseTime().size() > 2)
            this.setCourseTime3(courseVo.getCourseTime().get(2));
        if (courseVo.getCourseTime().size() > 3)
            this.setCourseTime4(courseVo.getCourseTime().get(3));
        if (courseVo.getCourseTime().size() > 4)
            this.setCourseTime5(courseVo.getCourseTime().get(4));
        if (courseVo.getCourseTime().size() > 5)
            this.setCourseTime6(courseVo.getCourseTime().get(5));
        if (null != courseVo.getCategory())
            this.setTypeId(Integer.parseInt(courseVo.getCategory()));
        if (null != courseVo.getOrganization())
            this.setOrganizationId(Integer.parseInt(courseVo.getOrganization()));
    }
}