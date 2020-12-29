package com.oes.common.dao;

import com.oes.model.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * (User)表数据库访问层
 *
 * @author makejava
 * @since 2020-03-27 14:40:14
 */
@Mapper
public interface UsersCommonDao {


    /**
     * 通过ID查询单条数据
     *
     * @param userid 主键
     * @return 实例对象
     */
    @Select("select * from users where userid = #{userid}")
    User queryById(long userid);

}