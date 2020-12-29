package com.oes.dao;

import com.oes.model.entity.Student;
import com.oes.model.info.StudentInfo;
import com.oes.query.StudentQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Student)表数据库访问层
 *
 * @author makejava
 * @since 2020-03-27 14:40:14
 */
@Mapper
public interface StudentsDao {

    /**
     * 通过ID查询单条数据
     *
     * @param userid 主键
     * @return 实例对象
     */
    Student queryById(long userid);

    /**
     * 通过父母的id获取孩子
     * @param parentId 父母的id
     * @return 课程实例
     */
    List<Student> queryByParentId(long parentId);

    /**
     * 通过学士姓名获取学生（可能重名）
     * @param name 学生姓名
     * @return 学生列表
     */
    List<Student> queryByName(String name);

    /**
     * 通过学生类别查询
     * @param type 课程类别
     * @return 学生列表
     */
    List<Student> queryByType(String type);

    /**
     * 通过学生就读学校查询
     * @param school 学校名
     * @return 学生列表
     */
    List<Student> queryBySchool(String school);

    /**
     * 通过学生年级查询
     * @param grade 年级
     * @return 学生列表
     */
    List<Student> queryByGrade(int grade);

    /**
     * 通过学生感兴趣的课程获取学生
     * @param typeId 课程类型
     * @return 学生列表
     */
    List<Student> queryByCourseType(int typeId);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Student> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param student 实例对象
     * @return 对象列表
     */
    List<Student> queryByEntity(Student student);

    /**
     * 获取全部学生
     * @return 对象列表
     */
    List<Student> queryAll();

    /**
     * 新增数据
     *
     * @param student 实例对象
     * @return 影响行数
     */
    int insert(Student student);

    /**
     * 修改数据
     *
     * @param student 实例对象
     * @return 影响行数
     */
    int update(Student student);

    /**
     * 通过主键删除数据
     *
     * @param userid 主键
     * @return 影响行数
     */
    int deleteById(long userid);

    List<StudentInfo> queryStudents(StudentQuery studentQuery);
}