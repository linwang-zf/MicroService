package com.oes.vo;

/**
 * @Author: Jiang Xiaodan
 * @Date: 2020/6/29 16:25
 */
public class CourseCategoryVO {
    private Integer category_id;
    private String name;

    @Override
    public String toString() {
        return "CourseCategoryVO{" +
                "category_id=" + category_id +
                ", name='" + name + '\'' +
                '}';
    }

    public Integer getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Integer category_id) {
        this.category_id = category_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
