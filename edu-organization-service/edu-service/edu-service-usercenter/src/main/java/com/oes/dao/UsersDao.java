package com.oes.dao;

import com.oes.model.entity.User;
import com.oes.model.enums.Gender;
import com.oes.model.example.UserExample;
import com.oes.model.query.user.UserPrimaryKey;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

//@Repository
@Mapper
public interface UsersDao {
    long countByExample(UserExample example);

    /**
     * 通过ID查询单条数据
     *
     * @param userid 主键
     * @return 实例对象
     */
    User queryById(long userid);
    /**
     * 通过account查询,account在数据库中也是唯一的
     * @param account 账号
     * @return 返回符合条件的记录
     */
    User queryByAccount(String account);

    /**
     * 查询二级管理员的密码
     * @param account
     * @return
     */
    String queryPasswordByAccount(String account);

    /**通过用户主键查询用户信息*/
    User queryByPrimaryKey(UserPrimaryKey primaryKey);

    /**
     * 通过昵称查找用户
     * @param nickname 昵称
     * @return 返回符合条件的记录
     */
    List<User> queryByNickName(String nickname);

    /**
     * 通过性别查询用户
     * @param gender 性别
     * @return 返回符合条件的记录
     */
    List<User> queryByGender(Gender gender);


    /**
     * 通过用户角色id查询用户
     * @param roleId 用户角色id
     * @return 返回符合条件的记录
     */
    List<User> queryByRole(int roleId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<User> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param user 实例对象
     * @return 对象列表
     */
    List<User> queryByEntity(User user);

    /**
     * 查询全部记录
     * @return 返回全部记录
     */
    List<User> queryAll();

    /**
     * 新增数据
     *
     * @param user 实例对象
     * @return 影响行数
     */
    int insert(User user);

    /**
     * 修改数据
     *
     * @param user 实例对象
     * @return 影响行数
     */
    int update(User user);

    /**
     * 通过主键删除数据
     *
     * @param userid 主键
     * @return 影响行数
     */
    int deleteById(long userid);

    String queryPasswordByUserid(long userid);

    List<User> checkPhone(String phone);
}
