package com.oes.dao;

import com.oes.model.entity.OrganizationCourse;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * (OrganizationCourse)表数据库访问层
 *
 * @author makejava
 * @since 2020-03-27 14:38:11
 */
//@Repository
@Mapper
public interface OrganizationCoursesDao {

    /**
     * 通过ID查询单条数据
     *
     * @param organizationId 主键
     * @return 实例对象
     */
    List<OrganizationCourse> queryById(long organizationId);

    /**
     * 通过课程类别查询
     * @param courseCategoryId
     * @return
     */
    List<OrganizationCourse> queryByCourseTypeId(int courseCategoryId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<OrganizationCourse> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param organizationCourse 实例对象
     * @return 对象列表
     */
    List<OrganizationCourse> queryByEntity(OrganizationCourse organizationCourse);

    /**
     * 查询全部记录
     * @return
     */
    List<OrganizationCourse> queryAll();

    /**
     * 新增数据
     *
     * @param organizationCourse 实例对象
     * @return 影响行数
     */
    int insert(OrganizationCourse organizationCourse);

    /**
     * 修改数据
     *
     * @param organizationCourse 实例对象
     * @return 影响行数
     */
    int update(OrganizationCourse organizationCourse);

    /**
     * 通过主键删除数据
     *
     * @param organizationId 主键
     * @param courseTypeId 主键
     * @return 影响行数
     */
    int deleteById(@Param("organizationId") long organizationId, @Param("courseTypeId") int courseTypeId);

}