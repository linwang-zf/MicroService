package com.oes.dao;

import com.oes.model.entity.Teacher;
import org.apache.ibatis.annotations.Mapper;


import java.util.List;

/**
 * (Teacher)表数据库访问层
 *
 * @author makejava
 * @since 2020-03-27 14:40:14
 */
@Mapper
public interface TeachersDao {



    /**
     * 通过机构id查询所有未审核的老师
     * @param orgId
     * @return
     */
    List<Teacher> queryUncheckedByOrgId(long orgId);

    /**
     * 通过ID查询单条数据
     *
     * @param userid 主键
     * @return 实例对象
     */
    Teacher queryById(long userid);

    /**
     * 修改数据
     *
     * @param teacher 实例对象
     * @return 影响行数
     */
    int update(Teacher teacher);




}