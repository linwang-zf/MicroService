package com.oes.vo;

import lombok.*;

/**
 * @author : yanjundong
 * @date : 2020-04-25 17:25
 * @description : 课程表
 */

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SyllabusVo {

    /*课程id*/
    Integer courseId;

    /*课程名称*/
    String name;

    /*课程上课时间*/
    String courseTime;

    /*上课场地*/
    String site;

    /*第几次课*/
    Integer courseNo;

}
