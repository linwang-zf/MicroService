package com.oes.model.dto;

/**
 * 老师的DTO,该类为实体类Teacher中的一部分
 *
 */
public class TeacherDTO {
    private long userid;
    private long organizationId;
    private String gradSchool;
    private String education;
    private String keywords;
    private String degree;
    private String degree2;
    private boolean FullTime;

    public long getUserid() {
        return userid;
    }

    public void setUserid(long userid) {
        this.userid = userid;
    }

    public long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(long organizationId) {
        this.organizationId = organizationId;
    }

    public String getGradSchool() {
        return gradSchool;
    }

    public void setGradSchool(String gradSchool) {
        this.gradSchool = gradSchool;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getDegree2() {
        return degree2;
    }

    public void setDegree2(String degree2) {
        this.degree2 = degree2;
    }

    public boolean isFullTime() {
        return FullTime;
    }

    public void setFullTime(boolean fullTime) {
        FullTime = fullTime;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"userid\":")
                .append(userid);
        sb.append(",\"organizationId\":")
                .append(organizationId);
        sb.append(",\"gradSchool\":\"")
                .append(gradSchool).append('\"');
        sb.append(",\"education\":\"")
                .append(education).append('\"');
        sb.append(",\"keywords\":\"")
                .append(keywords).append('\"');
        sb.append(",\"degree\":\"")
                .append(degree).append('\"');
        sb.append(",\"degree2\":\"")
                .append(degree2).append('\"');
        sb.append(",\"FullTime\":")
                .append(FullTime);
        sb.append('}');
        return sb.toString();
    }
}
