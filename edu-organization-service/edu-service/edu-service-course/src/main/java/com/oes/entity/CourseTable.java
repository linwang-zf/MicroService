package com.oes.entity;

import lombok.*;

import java.time.LocalDateTime;

/** 课程表 */
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class CourseTable {
    /** id主键 */
    private Integer id;

    /** 课程id */
    private Integer courseId;

    /** 1节课的开始时间 */
    private LocalDateTime startTime;

    /** 第几节课 */
    private Integer courseNo;
}