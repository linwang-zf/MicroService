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
     * 通过用户id查询
     * @param userId 用户id
     * @return 员工列表
     */
    List<OrganizationWorker> queryByUserId(long userId);

    /**
     * 修改数据
     *
     * @param organizationWorker 实例对象
     * @return 影响行数
     */
    int update(OrganizationWorker organizationWorker);


}