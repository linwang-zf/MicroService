package com.oes.query;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;

/**
 * @author : JQK
 * @date : 2020-07-22 17:15
 * @description : 课程历史查询
 */
@Getter
@Setter
@ApiModel(parent = PageQuery.class)
public class CourseHistoryQuery extends PageQuery {

    private int id;
    private String limitTime;

}
