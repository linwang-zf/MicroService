/*
package com.oes.dao;

import com.oes.model.entity.OrganizationsStudent;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

*/
/**
 * (OrganizationsStudent)表数据库访问层
 *
 * @author makejava
 * @since 2020-03-27 14:40:13
 *//*

@Repository
public interface OrganizationsStudentsDao {

    */
/**
     * 通过ID查询单条数据
     *
     * @param organization 主键
     * @param studentId 主键
     * @return 实例对象
     *//*

    OrganizationsStudent queryById(@Param("orgId") long organization, @Param("stuId") long studentId);


    */
/**
     * 通过机构Id查询
     * @param organization 机构id
     * @return 机构学生对应关系列表
     *//*

    List<OrganizationsStudent> queryByOrgId(long organization);


    */
/**
     * 通过学生id查询
     * @param studentId 学生Id
     * @return 机构学生对应关系列表
     *//*

    List<OrganizationsStudent> queryByStuId(long studentId);


    */
/**
     * 通过内部学号查询
     * @param orgId 机构的Id
     * @param internalId 内部学号
     * @return 具体学号对应学生
     *//*

    OrganizationsStudent queryByOrgAndInternalId(@Param("orgId") long orgId, @Param("internalId") long internalId);



    */
/**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     *//*

    List<OrganizationsStudent> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    */
/**
     * 通过实体作为筛选条件查询
     *
     * @param organizationsStudent 实例对象
     * @return 对象列表
     *//*

    List<OrganizationsStudent> queryByEntity(OrganizationsStudent organizationsStudent);


    */
/**
     * 查询全部记录
     * @return 机构学生对应关系列表
     *//*

    List<OrganizationsStudent> queryAll();

    */
/**
     * 新增数据
     *
     * @param organizationsStudent 实例对象
     * @return 影响行数
     *//*

    int insert(OrganizationsStudent organizationsStudent);

    */
/**
     * 修改数据
     *
     * @param organizationsStudent 实例对象
     * @return 影响行数
     *//*

    int update(OrganizationsStudent organizationsStudent);

    */
/**
     * 通过主键删除数据
     *
     * @param organization 主键
     * @return 影响行数
     *//*

    int deleteById(@Param("orgId") long organization, @Param("stuId") long studentId);

    */
/**
     * 通过机构id删除
     * @param orgId 机构id
     * @return 影响行数
     *//*

    int deleteByOrgId(long orgId);

    */
/**
     * 通过学生id删除
     * @param stuId 学生id
     * @return 影响行数
     *//*

    int deleteByStuId(long stuId);

}*/
