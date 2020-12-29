package com.oes.dao;

import com.oes.model.dto.OrganizationTeacherDTO;
import com.oes.model.entity.Organization;
import com.oes.model.enums.RegistState;
import com.oes.model.example.OrganizationExample;
import com.oes.model.query.OrganizationQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * (Organization)表数据库访问层
 *
 * @author makejava
 * @since 2020-03-27 14:38:35
 */
@Mapper
public interface OrganizationsDao {

    long countByExample(OrganizationExample example);

    List<Organization> selectByExample(OrganizationExample example);

    /**
     * 通过ID查询单条数据
     *
     * @param orgId 主键
     * @return 实例对象
     */
    Organization queryById(long orgId);

    /**
     * 通过机构名称查询机构
     * @param name 机构名称
     * @return 符合条件的机构
     */
    List<Organization> queryByName(String name);

    List<Organization> queryByKeyword(String keyWord);

    /**
     * 通过机构法人的名字查询机构
     * @param name 法人名字
     * @return 所有符合结果的机构
     */
    List<Organization> queryByLegalPerson(String name);


    /**
     * 通过机构状态查询机构
     * @param state 注册状态
     * @return 所有符合结果的机构
     */
    List<Organization> queryByState(int state);

    /**
     * 分页过滤查询审核通过/不通过的机构
     */
    List<Organization> queryByStateQuery(OrganizationQuery query);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<Organization> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param organization 实例对象
     * @return 对象列表
     */
    List<Organization> queryByEntity(Organization organization);

    /**
     * 查询全部机构
     * @return 返回全部机构
     */
    List<Organization> queryAll();

    /**
     * 新增数据
     *
     * @param organization 实例对象
     * @return 影响行数
     */
    int insert(Organization organization);

    /**
     * 归档到organazationLogOff
     *
     * @param organization 实例对象
     * @return 影响行数
     */
    int insertLogOff(Organization organization);

    /**
     * 修改数据
     *
     * @param organization 实例对象
     * @return 影响行数
     */
    int update(Organization organization);

    /**
     * 通过主键删除数据
     *
     * @param orgId 主键
     * @return 影响行数
     */
    int deleteById(long orgId);

    /**
     * 查询机构
     * @param query
     * @return
     */
    List<Organization> queryOrganizations(OrganizationQuery query);

    int updateRegisterState(RegistState state, long orgId);

    /**
     * 查询机构对应的教师
     */
    List<OrganizationTeacherDTO> getOrganizationTeacher();

    List<Organization> getOrgRecommend(@Param("recommendNum") Integer recommendNum,
                                       @Param("courseRate") float courseRate,
                                       @Param("studentRate") float studentRate);

}