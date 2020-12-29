package com.oes.service;

import com.alibaba.fastjson.JSONObject;

import com.oes.Exceptions.ServiceException;
import com.oes.config.Url;
import com.oes.constant.enums.BusinessType;

import com.oes.dao.StudentsDao;
import com.oes.model.dto.BaseResultDTO;
import com.oes.model.entity.CourseCategory;
import com.oes.model.entity.Student;
import com.oes.util.http.HttpResult;
import com.oes.vo.StudentPreInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class StudentService {
    @Autowired
    StudentsDao studentsDao;


    @Resource
    private RestTemplate restTemplate;

    /**
     * 通过id获取学生信息
     */
    public BaseResultDTO getStudentById(Integer stuId) {
        Student student = studentsDao.queryById(stuId);

        if (null == student) {
            return new BaseResultDTO(false, "没有查询到该学生信息");
        }

        JSONObject result = new JSONObject();
        Class<Student> clazz = Student.class;
        Field[] fields = clazz.getDeclaredFields();
        List<String> categorys = new ArrayList<>();
        List<Integer> categoryIds = new ArrayList<>();
        for (Field field : fields) {
            try {
                if (!"serialVersionUID".equals(field.getName())) {
                    field.setAccessible(true);
                    String key = field.getName();
                    Object value = field.get(student);
                    if (key.startsWith("courseType")) {
                        if (0 != (int) value) {
                            HttpResult<CourseCategory> categoryResult = restTemplate.exchange
                                    (Url.SERVICE_COURSE + "/coursecategory/" + value, HttpMethod.GET, null,
                                            new ParameterizedTypeReference<HttpResult<CourseCategory>>() {
                                            }).getBody();
                            if (categoryResult.getData() != null) {
                                CourseCategory courseCategory = (CourseCategory) categoryResult.getData();
                                categorys.add(courseCategory.getName());
                                categoryIds.add((Integer) value);
                            }
                        }
                    } else {
                        result.put(key, value);
                    }

                }
            } catch (IllegalAccessException e) {
                log.error("getStudentById==获取{}学生信息时错误", stuId, e);
                return new BaseResultDTO(false, "处理学生信息出错");
            }
        }
        result.put("category", categorys);
        result.put("categoryIds", categoryIds);
        return new BaseResultDTO(BusinessType.SELECT, true, "查询成功", result);
    }

    public StudentPreInfo getStudentPreInfo(long user_id) {
        Student student = studentsDao.queryById(user_id);
        if (student == null) throw new ServiceException("该同学不存在");
        return new StudentPreInfo(student);
    }
}
