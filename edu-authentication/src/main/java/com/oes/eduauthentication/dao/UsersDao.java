package com.oes.eduauthentication.dao;


import com.oes.eduauthentication.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


/**
 * (User)表数据库访问层
 *
 * @author makejava
 * @since 2020-03-27 14:40:14
 */
@Mapper
public interface UsersDao {

    /**
     * 通过account查询,account在数据库中也是唯一的
     * @param account 账号
     * @return 返回符合条件的记录
     */
    //@Select("select * from users where account = #{account} ")
    User queryByAccount(String account);
    //@Select("select * from users where where phone = #{phone}")
    User queryByPhone(String username);




}