package com.oes.model.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;


import java.io.Serializable;
import java.util.List;

/**
 * (CourseCategory)实体类
 *
 * @author makejava
 * @since 2020-03-27 14:18:35
 */
@ApiModel(value = "CourseCategory", description = "课程类别模型")
@Data
@NoArgsConstructor
@Component
public class CourseCategory implements Serializable {
    private static final long serialVersionUID = -90729711720299807L;

    private int categoryId;

    @ApiModelProperty(value = "课程类别名称", required = true, example = "游泳")
    private String name;
    /**
     * 1:一级目录；2-二级目录；3-三级目录
     */
    @ApiModelProperty(hidden = true)
    private int level;

    @ApiModelProperty(value = "上级类别id", required = true, example = "-1")
    private int parentId;

    @ApiModelProperty(hidden = true)
    private List<CourseCategory> children;

    public CourseCategory(Integer id, String name, Integer level, Integer parentId) {
        this.categoryId = id;
        this.name = name;
        this.level = level;
        this.parentId = parentId;
    }

}