package com.oes.common.dao;


import com.oes.model.entity.CourseCategory;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

/**
 * (CourseCategory)表数据库访问层
 *
 * @author makejava
 * @since 2020-03-27 14:18:35
 */
@Mapper
public interface CourseCategoryCommonDao {

    /**
     * 通过ID查询单条数据
     *
     * @param categoryId 主键
     * @return 实例对象
     */
    @Select("select * from course_category where category_id = #{categoryId}")
    CourseCategory queryById(int categoryId);



}