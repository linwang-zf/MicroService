package com.oes.query;

import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author dxh924
 * @Title:
 * @Package
 * @Description:
 * @date 2020/9/2621:15
 */
@Data
@ToString
public class CourseMerQuery {

    /**
     * 课程名
     */
    private String courseName;

    /**
     * 机构id
     */
    private Integer orgId;

    /**
     * 课程商品状态
     */
    private Integer merState;

    @NotNull(message = "当前页码不为空")
    @Min(value = 0, message = "页码不小于0")
    private Integer pageNum;
    @NotNull(message = "页面大小不为空")
    @Min(value = 0, message = "页面大小不小于0")
    private Integer pageSize;
    // private String query;
}
