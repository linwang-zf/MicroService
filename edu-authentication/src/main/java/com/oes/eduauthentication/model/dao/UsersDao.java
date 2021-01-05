package com.oes.eduauthentication.model.dao;

import com.oes.eduauthentication.model.entity.User;
import org.springframework.stereotype.Repository;

/**
 * (User)表数据库访问层
 *
 * @author makejava
 * @since 2020-03-27 14:40:14
 */
@Repository
public interface UsersDao {

    /**
     * 通过account查询,account在数据库中也是唯一的
     * @param account 账号
     * @return 返回符合条件的记录
     */
    User queryByAccount(String account);

    User queryByPhone(String username);
}