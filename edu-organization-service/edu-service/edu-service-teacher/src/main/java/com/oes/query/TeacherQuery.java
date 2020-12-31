package com.oes.query;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ToString
public class TeacherQuery {
    private String education;//学位
    private String degree;//学历
    @NotNull(message = "当前页码不为空")
    @Min(value = 0, message = "页码不小于0")
    private Integer pageNum;
    @NotNull(message = "页面大小不为空")
    @Min(value = 0, message = "页面大小不小于0")
    private Integer pageSize;
    private String query;
}
