package com.oes.model.query;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : yanjundong
 * @date : 2020-04-18 18:25
 * @description : 课程的 数据查询对象
 */

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class CourseQuery {

    //当前页码
    private int pageNum;

    //每页数量
    private int pageSize;

    //课程类别id
    private int typeId;

    //课程名称
    private String name;

    //主讲教师id
    private List<String> teacherId = new ArrayList<>();

    //开设时间范围
    private List<String> limitTime = new ArrayList<>(2);

    @ApiModelProperty(value = "是否查询历史课程", hidden = true)
    private boolean isHistory;

    public void addLimitTime(LocalDate date) {
        limitTime.add(date.toString());
    }

    public void addLimitTime(LocalDate start, LocalDate end) {
        limitTime.add(start.toString());
        limitTime.add(end.toString());
    }

}
