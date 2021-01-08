package com.oes.eduauthentication.dao;

import com.oes.eduauthentication.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * (Role)表数据库访问层
 *
 * @author makejava
 * @since 2020-03-27 14:40:13
 */
@Mapper
public interface RolesDao {

    List<Role> selectBatch(@Param(value = "roleids") Set<Integer> roleids);
}