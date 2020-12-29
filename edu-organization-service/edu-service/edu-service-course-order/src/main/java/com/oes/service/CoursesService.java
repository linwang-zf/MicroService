package com.oes.service;


import com.oes.Exceptions.database.DBOperateException;
import com.oes.common.dao.AuthenticatedUsersCommonDao;
import com.oes.common.dao.CourseCategoryCommonDao;
import com.oes.common.dao.OrganizationsCommonDao;
import com.oes.config.Url;
import com.oes.dao.CourseEnrollDao;
import com.oes.dto.EnrollCoursesDTO;
import com.oes.model.dto.CourseStudentDTO;
import com.oes.model.enums.EnrollCourseStatus;
import com.oes.model.query.CourseQuery;
import com.oes.model.vo.CourseVo;
import com.oes.util.http.HttpResult;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author : yanjundong
 * @date : 2020-04-10 09:58
 * @description : 课程信息管理 service
 */

@Service
@Slf4j
public class CoursesService {
    private final Logger logger = LoggerFactory.getLogger(CoursesService.class);

    @Resource
    private AuthenticatedUsersCommonDao authenticatedUsersCommonDao;
    @Resource
    private OrganizationsCommonDao organizationsCommonDao;
    @Resource
    private CourseCategoryCommonDao courseCategoryCommonDao;
    @Resource
    private CourseEnrollDao courseEnrollDao;
    @Resource
    private RestTemplate restTemplate;


    /**
     * 获取某学生在某机构所有未选修的课程
     */
    public List<Map<String, Object>> getAllUnEnrollCourseByStuId(Integer orgId, Integer stuId) {
        List<CourseVo> options = getCourseOptionByStuId(orgId, stuId);
        if (null == options) {
            return null;
        }
        List<Map<String, Object>> result = new ArrayList<>(options.size());
        for (CourseVo option : options) {
            Map<String, Object> item = new HashMap<>();
            item.put("id", option.getCourseId());
            item.put("name", option.getName());
            result.add(item);
        }
        return result;
    }

    /**
     * 查询指定学生在指定机构尚未选修的所有课程
     *
     * @param orgId 机构id
     * @param stuId 学生id
     * @return
     */
    public List<CourseVo> getCourseOptionByStuId(Integer orgId, Integer stuId) {
        /*查询该学生选修的课程*/
        List<EnrollCoursesDTO> coursesInfo = courseEnrollDao.getEnrollCoursesInfo(orgId, stuId, EnrollCourseStatus.success.getCode());
        if (Objects.isNull(coursesInfo))
            throw new DBOperateException("数据库访问错误");

        List<Integer> courseIds = coursesInfo.stream().map(courseInfo -> (int) courseInfo.getCourseId()).collect(Collectors.toList());

        /*查询出该机构的所有课程*/
        CourseQuery query = new CourseQuery();
        query.setPageNum(0);
        query.setPageSize(0);
        query.setName("");
        query.setTypeId(0);
        query.getLimitTime().add(LocalDate.now().toString());
        HttpResult result = restTemplate.postForObject(Url.SERVICE_COURSE + "/course/organization/" + orgId, query, HttpResult.class);
        //TODO 错误处理
        if(result.getCode()!= 200 || result.getData() == null){
            return null;
        }
        List<CourseVo> courseVoList = (List<CourseVo>) result.getData();
        /*排除该学生的已选课程*/
        List<CourseVo> collect = courseVoList.stream().filter(item -> !courseIds.contains(item.getCourseId())).collect(Collectors.toList());
        return collect;
    }

    /**
     * 根据courseId 得到某课程试听学生名单
     */
    public List<CourseStudentDTO> getTryStudentInfoByCourseId(Integer courseId) {
        List<CourseStudentDTO> tryStus = courseEnrollDao.getTryStudentInfoByCourseId(courseId);
        if (Objects.isNull(tryStus))
            throw new DBOperateException("数据获取失败");
        else
            return tryStus;
    }

    /**
     * 根据courseId 得到某课程选修学生名单
     */
    public List<CourseStudentDTO> getStudentInfoByCourseId(Integer courseId) {
        List<CourseStudentDTO> stus = courseEnrollDao.getStudentInfoByCourseId(courseId);
        if (Objects.isNull(stus))
            throw new DBOperateException("数据获取失败");
        else
            return stus;
    }


}
