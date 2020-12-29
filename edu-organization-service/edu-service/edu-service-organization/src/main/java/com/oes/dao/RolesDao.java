package com.oes.dao;

import com.oes.model.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * (Role)表数据库访问层
 *
 * @author makejava
 * @since 2020-03-27 14:40:13
 */
@Mapper
public interface RolesDao {
    /**
     * 通过名字查询角色
     * @param name 角色名字
     * @return 返回角色
     */

    @Select("select roleid, role_name, role_table_name from roles where role_name=#{name}")
    Role queryByName(String name);



}