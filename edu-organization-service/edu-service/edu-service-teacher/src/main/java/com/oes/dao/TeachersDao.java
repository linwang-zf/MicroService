package com.oes.dao;

import com.oes.info.TeacherInfo;
import com.oes.model.entity.Teacher;
import com.oes.query.TeacherQuery;
import com.oes.vo.TeacherBriefVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * (Teacher)表数据库访问层
 *
 * @author makejava
 * @since 2020-03-27 14:40:14
 */
@Repository
public interface TeachersDao {

    /**
     * 通过ID查询单条数据
     *
     * @param userid 主键
     * @return 实例对象
     */
    Teacher queryById(long userid);

    /**
     * 通过机构id查询通过审核老师
     * @param orgId 机构id
     * @return 返回符合条件的记录
     */
    List<Teacher> queryByOrgId(long orgId);

    /**
     * 通过机构id查询所有未审核的老师
     * @param orgId
     * @return
     */
    List<Teacher> queryUncheckedByOrgId(long orgId);

    /**
     * 通过关键字查询符合记录的老师
     * @param keyword 关键字
     * @return 返回符合条件的记录
     */
    List<Teacher> queryByKeywords(String keyword);

    /**
     * 通过毕业学校查找教师
     * @param gradSchool 毕业院校
     * @return 返回符合条件的老师
     */
    List<Teacher> queryByGradSchool(String gradSchool);

    /**
     * 通过学历查找老师
     * @param education 学历
     * @return 返回符合条件的老师
     */
    List<Teacher> queryByEducation(String education);

    /**
     * 通过擅长课程查找符合条件的老师
     * @param courseType 课程类型的id
     * @return 符合条件的老师
     */
    List<Teacher> queryByCourseTypeId(int courseType);

    /**
     * 查询指定行数据
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Teacher> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param teacher 实例对象
     * @return 对象列表
     */
    List<Teacher> queryByEntity(Teacher teacher);

    /**
     * 查询全部记录
     * @return 对象列表
     */
    List<Teacher> queryAll();

    /**
     * 新增数据
     *
     * @param teacher 实例对象
     * @return 影响行数
     */
    int insert(Teacher teacher);

    /**
     * 修改数据
     *
     * @param teacher 实例对象
     * @return 影响行数
     */
    int update(Teacher teacher);

    /**
     * 通过主键删除数据
     *
     * @param userid 主键
     * @return 影响行数
     */
    int deleteById(long userid);

    /**
     * 过滤分页查询教师
     * @param teacherQuery
     * @return
     */
    List<TeacherInfo> queryTeachers(TeacherQuery teacherQuery);

    List<TeacherBriefVO> queryTeacherBriefVO(List<Integer> teacherIds);

}