package com.oes.dao;

import com.oes.model.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * (Role)表数据库访问层
 *
 * @author makejava
 * @since 2020-03-27 14:40:13
 */
@Mapper
public interface RolesDao {

    /**
     * 通过ID查询单条数据
     *
     * @param roleid 主键
     * @return 实例对象
     */
    Role queryById(int roleid);

    /**
     * 通过名字查询角色
     * @param name 角色名字
     * @return 返回角色
     */
    Role queryByName(String name);



    /**
     * 修改数据
     *
     * @param role 实例对象
     * @return 影响行数
     */
    int update(Role role);



}