package com.oes.dao;


import com.oes.entity.CourseMer;
import com.oes.query.CourseMerQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CourseMerDao {
    int insert(CourseMer record);

    int insertSelective(CourseMer record);

    int putOnCourseMer(Integer merId);

    int pullOffCourseMer(Integer merId);

    List<CourseMer> findCourseMerByState(Integer merState);

    List<CourseMer> findCourseMer(CourseMerQuery courseMerQuery);

    CourseMer findByMerId(Integer merId);

    List<CourseMer> findByCourseId(Integer courseId);

    List<CourseMer> findByOrgId(Integer orgId);
    List<CourseMer> findByOrgIds(@Param("orgId") Integer orgId, String s);

    List<CourseMer> findByTeacherId(Integer teacherId);

    int reduceStock(Integer merId);

    int increaseStock(Integer merId);

    int findTotal();

    int findCourseMerCount(CourseMerQuery courseMerQuery);
}