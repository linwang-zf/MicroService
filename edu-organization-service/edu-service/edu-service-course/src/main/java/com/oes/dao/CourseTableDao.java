package com.oes.dao;


import com.oes.entity.CourseTable;
import com.oes.example.CourseTableExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
public interface CourseTableDao {
    long countByExample(CourseTableExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(CourseTable record);

    int insertSelective(CourseTable record);

    Integer insertBatch(@Param("records") List<CourseTable> records);

    List<CourseTable> selectByExample(CourseTableExample example);

    CourseTable selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CourseTable record);

    int updateByPrimaryKey(CourseTable record);
}