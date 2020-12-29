package com.oes.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author : yanjundong
 * @date : 2020-04-25 19:05
 * @description : 课程表的查询参数
 */
@Getter
@Setter
@ApiModel(description="课程表的查询query")
public class SyllabusQuery {
    //课程表类型："month" 或者 "week"，意为 月课表/周课表
    @ApiModelProperty(value = "month：月课表；week：周课表", required = true, example = "month")
    private String type;

    //时间范围：如果是周课表，格式：2020-5-1；如果是月课表，格式：2020-5
    @ApiModelProperty(value = "周课表：2020-5-1；月课表：2020-5", example = "2020-5")
    private String limitTime;

    @ApiModelProperty(value = "1-机构；2-教师；3-学生", required = true, example = "3")
    private Integer userType;

    @ApiModelProperty(value = "机构/教师/学生 的id", required = true, example = "61")
    private Integer id;
}
