package com.oes.dto;

import com.alibaba.fastjson.JSONArray;
import com.oes.model.enums.Gender;


/**
 * 手动录入学生模型DTO
 */
public class ManualStudentDTO {
    private String name;
    private Gender gender;
    private String type;
    private String phone;
    private String secondTel;
    private String mail;
    private String school;
    private int grade;
    private String qq;
    private String weixin;
    private JSONArray courseType;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSecondTel() {
        return secondTel;
    }

    public void setSecondTel(String secondTel) {
        this.secondTel = secondTel;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
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

    public JSONArray getCourseType() {
        return courseType;
    }

    public void setCourseType(JSONArray courseType) {
        this.courseType = courseType;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"name\":\"")
                .append(name).append('\"');
        sb.append(",\"gender\":")
                .append(gender);
        sb.append(",\"type\":\"")
                .append(type).append('\"');
        sb.append(",\"phone\":\"")
                .append(phone).append('\"');
        sb.append(",\"secondTel\":\"")
                .append(secondTel).append('\"');
        sb.append(",\"mail\":\"")
                .append(mail).append('\"');
        sb.append(",\"school\":\"")
                .append(school).append('\"');
        sb.append(",\"grade\":")
                .append(grade);
        sb.append(",\"qq\":\"")
                .append(qq).append('\"');
        sb.append(",\"weixin\":\"")
                .append(weixin).append('\"');
        sb.append(",\"courseType\":")
                .append(courseType);
        sb.append('}');
        return sb.toString();
    }
}
