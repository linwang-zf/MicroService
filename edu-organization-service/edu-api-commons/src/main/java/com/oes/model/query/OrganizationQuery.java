package com.oes.model.query;

import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @Author: Jiang Xiaodan
 * @Date: 2020/6/24 21:15
 * @Content: 机构分页和过滤查询
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationQuery {
    //当前页码
    @NotNull(message = "当前页码不为空")
    @Min(value = 0,message = "当前页码不小于0")
    private Integer pageNum;
    //每页数量
    @NotNull(message = "页面大小不为空")
    @Min(value = 0,message = "页面大小不小于0")
    private Integer pageSize;
    private String name;
    private Integer state;
    private String query;
    //机构所在省
    private String province;
    //机构所在市
    private String city;
    private String district;
}
