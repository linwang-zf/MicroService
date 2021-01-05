package com.oes.service;

import com.alibaba.fastjson.JSONObject;

import com.oes.Exceptions.ServiceException;
import com.oes.Exceptions.database.DBOperateException;
import com.oes.config.Url;
import com.oes.constant.enums.BusinessType;

import com.oes.dao.StudentsDao;
import com.oes.exception.StuNotExistsException;
import com.oes.model.dto.BaseResultDTO;
import com.oes.model.entity.CourseCategory;
import com.oes.model.entity.Student;
import com.oes.model.vo.student.StudentVo;
import com.oes.util.http.HttpResult;
import com.oes.vo.StudentPreInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class StudentService {
    @Resource
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


    /** 补充学生信息
     * TODO 不实现该接口 测试----
     * */
    @Transactional
    public BaseResultDTO addStudent(long user_id, StudentVo studentVo) {
        Student student = new Student(user_id, studentVo);
        Student isHaveStu = studentsDao.queryById(user_id);

        BaseResultDTO resultDTO = new BaseResultDTO();

        if (null == isHaveStu) {
            int row = studentsDao.insert(student);
            if ((row > 0)) {
                resultDTO.set(true, "登记成功");
            } else{
                log.error("addStudent===id为{}的学生信息登记失败，信息为", user_id, student);
                throw new DBOperateException("学生信息登记失败");
            }
        } else {
            int row = studentsDao.update(student);
            if ((row > 0)) {
                resultDTO.set(true, "登记成功");
            } else {
                log.error("addStudent===id为{}的学生信息修改失败，信息为", user_id, student);
                throw new DBOperateException("学生信息修改失败");
            }
        }
        //TODO 添加addRole全部在user服务中实现
        JSONObject object = new JSONObject();
        object.put("userId",student.getUserid());
        object.put("roleName","student");
        boolean student1 = restTemplate.postForObject(Url.SERVICE_USERCENTER+"/user/api/roleName",object,Boolean.class);
        if (!student1) throw new ServiceException("修改用户角色出错");
        return resultDTO;
    }


    /*
    * 服务间API
    * */
    public Student getStuInfoById(Integer stuId){
        Student student = studentsDao.queryById(stuId);
        if (Objects.isNull(student))
            throw new StuNotExistsException("没有查询到id为" + stuId + "的学生信息");
        else
            return student;
    }
}
