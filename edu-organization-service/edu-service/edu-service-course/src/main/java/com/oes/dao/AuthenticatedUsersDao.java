package com.oes.dao;

import com.oes.model.entity.AuthenticatedUser;
import com.oes.model.example.AuthUserExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (AuthenticatedUser)表数据库访问层
 *
 * @author makejava
 * @since 2020-03-27 14:17:47
 */
//@Repository
@Mapper
public interface AuthenticatedUsersDao {

    /**
     * 通过ID查询单条数据1
     *
     * @param userid 主键
     * @return 实例对象
     */
    AuthenticatedUser queryById(long userid);

    /**
     * 查询全部记录
     * @return
     */
    List<AuthenticatedUser> queryAll();

    List<AuthenticatedUser> queryNamesByIds(@Param("userIds") List<Integer> userIds);
}