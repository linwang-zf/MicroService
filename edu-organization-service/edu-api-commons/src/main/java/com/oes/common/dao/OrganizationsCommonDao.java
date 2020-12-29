package com.oes.common.dao;

import com.oes.model.dto.OrganizationTeacherDTO;
import com.oes.model.entity.Organization;
import com.oes.model.enums.RegistState;
import com.oes.model.example.OrganizationExample;
import com.oes.model.query.OrganizationQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * (Organization)表数据库访问层
 *
 * @author makejava
 * @since 2020-03-27 14:38:35
 */
@Mapper
public interface OrganizationsCommonDao {



    /**
     * 通过ID查询单条数据
     *
     * @param orgId 主键
     * @return 实例对象
     */
    @Select("select * from organizations where org_id = #{orgId}")
    Organization queryById(long orgId);



}