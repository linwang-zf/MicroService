package com.oes.dao;


import com.oes.model.entity.CourseCategory;
import com.oes.model.entity.Student;
import com.oes.vo.CourseCategoryVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (CourseCategory)表数据库访问层
 *
 * @author makejava
 * @since 2020-03-27 14:18:35
 */
//@Repository
@Mapper
public interface CourseCategoryDao {

    /**
     * 通过ID查询单条数据
     *
     * @param categoryId 主键
     * @return 实例对象
     */
    CourseCategory queryById(int categoryId);


    /**
     * 通过课程名字搜索课程类型
     * @param name 课程名字
     * @return 课程类别
     */
    CourseCategory queryByName(String name);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<CourseCategory> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param courseCategory 实例对象
     * @return 对象列表
     */
    List<CourseCategory> queryByEntity(CourseCategory courseCategory);

    /**
     * 查询全部记录
     * @return 对象列表
     */
    List<CourseCategory> queryAll();


    /**
     * 通过课程级别查询课程
     * @param level 课程级别
     * @return 对象列表
     */
    List<CourseCategory> queryByLevel(int level);


    /**
     * 根据父节点的id查找子节点
     * @param parentId 父节点的id
     * @return 对象列表
     */
    List<CourseCategory> queryByParentId(int parentId);

    /**
     * 新增课程类别
     * @param courseCategory
     * @return
     */
    int insert(CourseCategory courseCategory);

    /**
     * 修改数据
     *
     * @param courseCategory 实例对象
     * @return 影响行数
     */
    int update(CourseCategory courseCategory);

    /**
     * 通过主键删除数据
     *
     * @param categoryId 主键
     * @return 影响行数
     */
    int deleteById(int categoryId);

    /**
     * 查询课程类别树的叶子节点
     * @return
     */
    List<CourseCategory> getCategoryLeaf();

    List<CourseCategory> getInterestedCourseByStudent(Student student);

    List<CourseCategoryVO> getAllCourseCategory();
}