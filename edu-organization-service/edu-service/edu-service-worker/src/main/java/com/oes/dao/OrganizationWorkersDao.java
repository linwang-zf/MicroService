package com.oes.dao;

import com.oes.entity.OrganizationWorker;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * (OrganizationWorker)表数据库访问层
 *
 * @author makejava
 * @since 2020-03-27 14:25:56
 */
@Mapper
public interface OrganizationWorkersDao {

    /**
     * 通过ID查询单条数据
     *
     * @param userid 主键
     * @return 实例对象
     */
    OrganizationWorker queryById(@Param("userId") long userid, @Param("roleId") long roleId);


    /**
     * 通过机构id查询
     * @param orgId 机构id
     * @return 机构工作人员列表
     */
    List<OrganizationWorker> queryCheckedByOrgId(long orgId);

    /**
     * 通过机构id查询全部未通过审核员工
     * @param orgId
     * @return
     */
    List<OrganizationWorker> queryUncheckedByOrgId(long orgId);


    /**
     * 通过用户id查询
     * @param userId 用户id
     * @return 员工列表
     */
    List<OrganizationWorker> queryByUserId(long userId);

    /**
     * 通过用户角色查询记录
     * @param role 对应角色id
     * @return 返回所有机构相关置为的职位
     */
    List<OrganizationWorker> queryByRoleId(int role);


    /**
     * 通过机构id和角色查询人员列表
     * @param orgId 机构id
     * @param role 角色
     * @return 返回固定机构的固定职位的人员
     */
    List<OrganizationWorker> queryByOrgAndRoleId(@Param("orgId") long orgId, @Param("role") int role);

    /**
     * 查询指定行数据
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<OrganizationWorker> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param organizationWorker 实例对象
     * @return 对象列表
     */
    List<OrganizationWorker> queryByEntity(OrganizationWorker organizationWorker);


    /**
     * 查询全部记录
     * @return 返回结果
     */
    List<OrganizationWorker> queryAll();

    /**
     * 新增数据
     *
     * @param organizationWorker 实例对象
     * @return 影响行数
     */
    int insert(OrganizationWorker organizationWorker);

    /**
     * 修改数据
     *
     * @param organizationWorker 实例对象
     * @return 影响行数
     */
    int update(OrganizationWorker organizationWorker);

    /**
     * 通过主键删除数据
     *
     * @param userid 主键
     * @return 影响行数
     */
    int deleteById(@Param("userid") long userid, @Param("roleId") long roleId);

    /**
     * 通过机构id删除记录
     * @param orgId 机构id
     * @return 影响行数
     */
    int deleteByOrgId(long orgId);

}