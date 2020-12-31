package com.oes.dao;


import com.oes.dto.CourseRollcallDTO;
import com.oes.entity.CourseRollcall;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRollcallDao {
    int deleteByPrimaryKey(Integer rollcallId);

    int insert(CourseRollcall record);

    Integer getIdByInfo(CourseRollcall record);

    CourseRollcall selectByPrimaryKey(Integer rollcallId);

    int updateByPrimaryKeySelective(CourseRollcall record);

    int updateByPrimaryKey(CourseRollcall record);

    /*得到课程的签到名单*/
    List<CourseRollcallDTO> getSignInRoster(@Param("courseId") Integer courseId, @Param("courseNo") Integer courseNo);
}