package com.oes.model.vo.student;

import com.alibaba.fastjson.JSONArray;
import com.oes.model.entity.Student;


public class StudentVo {
    long parentId;
    String name;
    String type;
    String school;
    String secondTel;
    int grade;
    String qq;
    String weixin;
    JSONArray category = new JSONArray();

    public StudentVo() {}

    public StudentVo(Student student) {
        setGrade(student.getGrade());
        setName(student.getName());
        setParentId(student.getParentId());
        setQq(student.getQq());
        setSchool(student.getSchool());
        setType(student.getType());
        setWeixin(student.getWeixin());
        setSecondTel(student.getSecondTel());
    }

    public String getSecondTel() {
        return secondTel;
    }

    public void setSecondTel(String secondTel) {
        this.secondTel = secondTel;
    }

    public long getParentId() {
        return parentId;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public int getGrade() {
        return grade;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getWeixin() {
        return weixin;
    }

    public void setWeixin(String weixin) {
        this.weixin = weixin;
    }

    public JSONArray getCategory() {
        return category;
    }

    public void setCategory(JSONArray category) {
        this.category = category;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"parentId\":")
                .append(parentId);
        sb.append(",\"name\":\"")
                .append(name).append('\"');
        sb.append(",\"type\":\"")
                .append(type).append('\"');
        sb.append(",\"school\":\"")
                .append(school).append('\"');
        sb.append(",\"grade\":")
                .append(grade);
        sb.append(",\"qq\":\"")
                .append(qq).append('\"');
        sb.append(",\"weixin\":\"")
                .append(weixin).append('\"');
        sb.append(",\"category\":")
                .append(category);
        sb.append('}');
        return sb.toString();
    }
}
