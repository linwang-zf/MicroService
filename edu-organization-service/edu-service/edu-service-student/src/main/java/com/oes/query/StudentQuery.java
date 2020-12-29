package com.oes.query;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class StudentQuery {
    @NotNull(message = "当前页码不为空")
    @Min(value = 0, message = "当前页码不小于0")
    private Integer pageNum;
    @NotNull(message = "页面大小不为空")
    @Min(value = 0, message = "页面大小不小于0")
    private Integer pageSize;
    private String query;// school、name、userid、orgName
    private Integer grade;
}
