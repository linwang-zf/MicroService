package com.oes.service;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.oes.Exceptions.database.DBOperateException;
import com.oes.config.Url;
import com.oes.dao.OrganizationsStudentsDao;
import com.oes.dao.StudentsDao;
import com.oes.dto.ManualStudentDTO;
import com.oes.entity.OrganizationsStudent;
import com.oes.model.dto.BaseResultDTO;
import com.oes.model.entity.*;
import com.oes.util.http.HttpResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class OrgStuService {
    @Resource
    private RestTemplate restTemplate;
    @Resource
    private StudentsDao studentsDao;
    @Resource
    private OrganizationsStudentsDao organizationsStudentsDao;

    @Transactional
    public long addStudentManual(long org_id, ManualStudentDTO studentDTO) {
        User user = new User();
        Student student = new Student();
        BeanUtils.copyProperties(studentDTO, user);
        BeanUtils.copyProperties(studentDTO, student);
        /*
        * 将roleName也传入SERVICE_USERCENTER服务，让该服务内部在插入的时候去设置此user的角色
        * 少一次跟SERVICE_USERCENTER服务的交互
        * */
       // user.setDefaultRole(rolesDao.queryByName("student").getRoleid());
        //设置访问的Entity
        HttpEntity entity = new HttpEntity<>(user);
        HttpResult<User> userInsert = restTemplate.exchange
                (Url.SERVICE_USERCENTER+"/user/api/student", HttpMethod.POST, entity,
                        new ParameterizedTypeReference<HttpResult<User>>() {
                        }).getBody();
        user = userInsert.getData();
        student.setOwner_id(org_id);
        student.setUserid(user.getUserid());
        student.setOrg_added(true);

        JSONArray courseTypes = studentDTO.getCourseType();
        int size = courseTypes.size();
        if (size >= 1) student.setCourseType1Id(courseTypes.getInteger(0));
        if (size >= 2) student.setCourseType2Id(courseTypes.getInteger(1));
        if (size >= 3) student.setCourseType3Id(courseTypes.getInteger(2));
        if (size >= 4) student.setCourseType4Id(courseTypes.getInteger(3));
        if (size >= 5) student.setCourseType5Id(courseTypes.getInteger(4));
        int stuInsert = studentsDao.insert(student);
        BaseResultDTO resultDTO = addStudent(user.getUserid(), org_id);

        if (userInsert.getCode() == 200 && stuInsert == 1 && resultDTO.isSuccess()) {
            return user.getUserid();
        } else return 0;
//        userService.insertUser();


    }

    @Transactional
    /** 向机构报名 */
    public BaseResultDTO addStudent(long userid, long orgId) {
        OrganizationsStudent student1 = organizationsStudentsDao.queryById(orgId, userid);
        if (student1 != null)
            return new BaseResultDTO(false, "不能重复报名");
        Student isHaveStudent = studentsDao.queryById(userid);
        int row = 0;
        /*保证students表中存在该学生 */
        if (null == isHaveStudent) {
            Student addStudent = new Student();
            addStudent.setUserid(userid);
            addStudent.setOrg_added(false);
            row = studentsDao.insert(addStudent);
        } else
            row = 1;

        if (row > 0) {
            OrganizationsStudent student = new OrganizationsStudent();
            student.setStudentId(userid);
            student.setOrganization(orgId);
            student.setCoursesNumber(0);
            student.setInternalId(generateInternalId(orgId));
            student.setEnrollTime(Timestamp.valueOf(LocalDateTime.now()));
            int insert = organizationsStudentsDao.insert(student);
            if (insert > 0) {
                return new BaseResultDTO(true, "报名成功");
            }
        }
        log.error("addStudent错误，stuId为{}===orgId为{}", userid, orgId);
        throw new DBOperateException("数据库添加学生信息失败");
    }

    /**
     * 自动生成机构内部id，根据数据库当前的最后一条记录生成
     *
     * @param orgId 机构ID
     * @return
     */
    public long generateInternalId(long orgId) {
        String head = String.format("%03d", orgId);
        List<OrganizationsStudent> organizationsStudents = organizationsStudentsDao.queryByOrgId(orgId);

        if (organizationsStudents == null || organizationsStudents.isEmpty()) {
            return Long.valueOf(head + "01" + "001");
        } else {
            OrganizationsStudent student = organizationsStudents.get(organizationsStudents.size() - 1);
            long internalId = student.getInternalId();
            long innerNumber = internalId - (internalId / 1000) * 1000;
            long classNumber = internalId / 1000 - (internalId / 100000) * 100;
            if (innerNumber < 100)
                return Long.valueOf(head + String.format("%02d", classNumber) + String.format("%03d", innerNumber + 1));
            else
                return Long.valueOf(head + String.format("%02d", innerNumber + 1) + "001");
        }
    }

    /** 获取该学生挂靠的全部机构 */
    public JSONArray getAllOrg(Long userId) {
        List<OrganizationsStudent> organizationsStudents = organizationsStudentsDao.queryByStuId(userId);
        JSONArray result = new JSONArray();
        organizationsStudents.forEach(o -> {
            JSONObject object = new JSONObject();
            HttpResult<Organization> orgResult = restTemplate.exchange
                    (Url.SERVICE_ORGANIZATION+"/organization/api/"+o.getOrganization(), HttpMethod.GET, null,
                            new ParameterizedTypeReference<HttpResult<Organization>>() {
                            }).getBody();
            Organization organization = orgResult.getData();
            object.put("orgId", organization.getOrgId());
            object.put("enrollTime", o.getEnrollTime());
            object.put("courseNumber", o.getCoursesNumber());
            object.put("orgName", organization.getName());
            object.put("internalId", o.getInternalId());
            result.add(object);
        });
        return result;
    }

    /* 学生注销账号 */
    public boolean deleteStudent(long orgId, Long userId) {
        return organizationsStudentsDao.deleteById(orgId, userId) == 1;
    }



}
