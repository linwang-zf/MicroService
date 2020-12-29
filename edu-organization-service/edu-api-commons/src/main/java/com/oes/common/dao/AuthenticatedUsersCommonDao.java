package com.oes.common.dao;

import com.oes.model.entity.AuthenticatedUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


import java.util.List;

/**
 * (AuthenticatedUser)表数据库访问层
 *
 * @author makejava
 * @since 2020-03-27 14:17:47
 */
@Mapper
public interface AuthenticatedUsersCommonDao {



    /**
     * 通过ID查询单条数据
     *
     * @param userid 主键
     * @return 实例对象
     */
    @Select("select * from authenticated_users where userid = #{userid}")
    AuthenticatedUser queryById(long userid);

    /**
     * 查询全部记录
     * @return
     */
    @Select("select * from authenticated_users")
    List<AuthenticatedUser> queryAll();
}