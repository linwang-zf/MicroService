package com.oes.query;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @Author: Jiang Xiaodan
 * @Date: 2020/7/1 11:23
 */
@Getter
@Setter
@ToString
public class PageQuery {
    //当前页码
    @ApiModelProperty(value = "当前页码", example = "1")
    @NotNull(message = "当前页码不为空")
    @Min(value = 0,message = "当前页码不小于0")
    private int pageNum;

    //每页数量
    @NotNull(message = "页面大小不为空")
    @Min(value = 0,message = "页面大小不小于0")
    @ApiModelProperty(value = "每页数量", example = "10")
    private int pageSize;

    private String query;
}
