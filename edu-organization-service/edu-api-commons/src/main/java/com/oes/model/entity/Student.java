package com.oes.model.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import com.alibaba.fastjson.JSONArray;
import com.oes.model.vo.student.StudentVo;
import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * (Student)实体类
 *
 * @author makejava
 * @since 2020-03-27 14:40:13
 */
@ApiModel(value = "Student", description = "学生模型")
@Getter
@Setter
@ToString
public class Student implements Serializable {
    private static final long serialVersionUID = -79230082938968104L;
    @Excel(name="学生编号",orderNum = "0")
    private long userid;
    private long parentId;
    @Excel(name = "姓名", orderNum = "1")
    private String name;
    @Excel(name = "学生类型", orderNum = "2")
    private String type;//
    @Excel(name = "所在学校", orderNum = "3")
    private String school;
    @Excel(name = "年级", orderNum = "4")
    private int grade;
    @Excel(name = "QQ", orderNum = "5")
    private String qq;
    @Excel(name = "微信号", orderNum = "6")
    private String weixin;
    @Excel(name = "备用号码", orderNum = "7")
    private String secondTel;
    @Excel(name = "是否由机构录入",replace = {"是_true","否_false"}, orderNum = "8")
    private boolean org_added;
    @Excel(name = "机构编号", orderNum = "9")
    private long owner_id;

    private int courseType1Id;

    private int courseType2Id;

    private int courseType3Id;

    private int courseType4Id;

    private int courseType5Id;

    public Student() {
    }

    public Student(long userid, StudentVo studentVo) {
        setUserid(userid);
        setGrade(studentVo.getGrade());
        setName(studentVo.getName());
        setParentId(studentVo.getParentId());
        setSchool(studentVo.getSchool());
        setType(studentVo.getType());
        setWeixin(studentVo.getWeixin());
        setSecondTel(studentVo.getSecondTel());
        setQq(studentVo.getQq());
        JSONArray category = studentVo.getCategory();
        int size = category.size();
        if (size >= 1) setCourseType1Id(category.getInteger(0));
        if (size >= 2) setCourseType2Id(category.getInteger(1));
        if (size >= 3) setCourseType3Id(category.getInteger(2));
        if (size >= 4) setCourseType4Id(category.getInteger(3));
        if (size >= 5) setCourseType5Id(category.getInteger(4));
    }
}