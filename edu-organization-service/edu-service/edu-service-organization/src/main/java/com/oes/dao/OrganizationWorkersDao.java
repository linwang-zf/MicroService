package com.oes.dao;

import com.oes.entity.OrganizationWorker;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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
    @Select("select * from organization_workers where userid = #{userId} and role = #{roleId}")
    OrganizationWorker queryById(@Param("userId") long userid, @Param("roleId") long roleId);
}