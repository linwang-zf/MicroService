package com.oes.dao;

import com.oes.model.entity.AuthenticatedUser;
import com.oes.model.example.AuthUserExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

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

    long countByExample(AuthUserExample example);

    List<AuthenticatedUser> selectByExample(AuthUserExample example);

    /**
     * 通过ID查询单条数据
     *
     * @param userid 主键
     * @return 实例对象
     */
    AuthenticatedUser queryById(long userid);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<AuthenticatedUser> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param authenticatedUser 实例对象
     * @return 对象列表
     */
    List<AuthenticatedUser> queryByEntity(AuthenticatedUser authenticatedUser);

    /**
     * 查询全部记录
     * @return
     */
    List<AuthenticatedUser> queryAll();

    /**
     * 新增数据
     *
     * @param authenticatedUser 实例对象
     * @return 影响行数
     */
//    @Transactional
    int insert(AuthenticatedUser authenticatedUser);

    /**
     * 修改数据
     *
     * @param authenticatedUser 实例对象
     * @return 影响行数
     */
//    @Transactional
    int update(AuthenticatedUser authenticatedUser);

    /**
     * 通过主键删除数据
     *
     * @param userid 主键
     * @return 影响行数
     */
//    @Transactional
    int deleteById(long userid);

    List<AuthenticatedUser> queryNamesByIds(@Param("userIds") List<Integer> userIds);
}