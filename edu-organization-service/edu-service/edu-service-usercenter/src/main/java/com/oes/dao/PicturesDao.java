package com.oes.dao;

import com.oes.model.entity.Picture;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * (Picture)表数据库访问层
 *
 * @author makejava
 * @since 2020-03-27 14:40:13
 */
//@Repository
@Mapper
public interface PicturesDao {

    /**
     * 通过ID查询单条数据
     *
     * @param pictureId 主键
     * @return 实例对象
     */
    Picture queryById(long pictureId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Picture> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param picture 实例对象
     * @return 对象列表
     */
    List<Picture> queryByEntity(Picture picture);

    /**
     * 查询全部记录
     * @return
     */
    List<Picture> queryAll();

    /**
     * 新增数据
     *
     * @param picture 实例对象
     * @return 影响行数
     */
    int insert(Picture picture);

    /**
     * 修改数据
     *
     * @param picture 实例对象
     * @return 影响行数
     */
    int update(Picture picture);

    /**
     * 通过主键删除数据
     *
     * @param pictureId 主键
     * @return 影响行数
     */
    int deleteById(long pictureId);

}