package com.oes.dao;


import com.oes.model.dto.CourseStudentDTO;
import com.oes.model.dto.CoursesDTO;
import com.oes.model.entity.Courses;
import com.oes.model.entity.Teacher;
import com.oes.query.CoursePageQuery;
import com.oes.model.query.CourseQuery;
import io.lettuce.core.dynamic.annotation.Param;
import org.apache.ibatis.annotations.Mapper;


import java.util.List;


@Mapper
public interface CoursesDao {

    List<Courses> selectAllCourse();

    //根据 主键 删除课程信息
    int deleteCourse(Integer courseId);

    //添加课程信息
    int addCourse(Courses record);

    //根据 主键 得到课程信息
    Courses getCourseByKey(long courseId);

    //根据 关键字 得到课程信息
    List<Courses> getCourseByKeywords(@Param("query") CourseQuery query, @Param("orgId") Integer orgId);

    //根据 机构id 得到该机构的所有课程
    List<Courses> getCoursesByOrgId(Integer orgId);

    //根据 主键 更新课程信息
    int updateCourse(Courses record);

    //根据courseId 得到选修学生名单
    List<CourseStudentDTO> getStudentInfoByCourseId(Integer courseId);

    //根据courseId 得到试听学生名单
    List<CourseStudentDTO> getTryStudentInfoByCourseId(Integer courseId);

    //根据教师id 获取课程信息
    List<Courses> getCourseByTeacherId(Integer teacherId);

    //根据学生id 获取课程信息
    List<Courses> getCourseByStuId(Integer stuId);

    //根据条件查询课程
    List<CoursesDTO> getQueryCourse(CoursePageQuery courseQuery);

    //查询推荐课程
    List<Courses> getRecommendCourse(@Param("recommendNum") Integer recommendNum,
                                     @Param("remarkRate") float remarkRate,
                                     @Param("countRate") float countRate);


    /*****************************************************************************/
    /**
     * 通过机构id查询通过审核老师
     * @param orgId 机构id
     * @return 返回符合条件的记录
     */
    List<Teacher> queryByOrgId(long orgId);



}