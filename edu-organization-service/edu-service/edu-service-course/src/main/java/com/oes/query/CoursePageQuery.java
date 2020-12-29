package com.oes.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel("过滤查询课程的类")
public class CoursePageQuery {
    @ApiModelProperty(name = "limitTime", value = "限制时间", example = "[\"09:00\",\"10:00\"]")
    private List<String> limitTime;
    @ApiModelProperty(name = "limitDate", value = "限制日期", example = "[\"2020-04-10\",\"2021-05-20\"]")
    private List<String> limitDate;
    @ApiModelProperty(name = "limitWeekDay", value = "限制周几", example = "[1,2,4]")
    private List<String> limitWeekDay;
    @ApiModelProperty(name = "orgId", value = "机构id", example = "21")
    private Integer orgId;
    @ApiModelProperty(name = "typeId", value = "课程类型id", example = "1")
    private Integer typeId;
    @ApiModelProperty(name = "teacherId", value = "教师id数组", example = "[49,15]")
    private List<Integer> teacherId;
    @ApiModelProperty(name = "pageNum", value = "当前页码", example = "1")
    private int pageNum = 1;
    @ApiModelProperty(name = "pageSize", value = "页面大小", example = "10")
    private int pageSize = 15;
}
