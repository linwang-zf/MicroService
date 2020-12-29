package com.oes.model.vo;

public class CourseTeacherVo {
    private long teacher1Id;
    private String teacher1name;
    private long teacher2Id;
    private String teacher2name;

    public long getTeacher1Id() {
        return teacher1Id;
    }

    public void setTeacher1Id(long teacher1Id) {
        this.teacher1Id = teacher1Id;
    }

    public String getTeacher1name() {
        return teacher1name;
    }

    public void setTeacher1name(String teacher1name) {
        this.teacher1name = teacher1name;
    }

    public long getTeacher2Id() {
        return teacher2Id;
    }

    public void setTeacher2Id(long teacher2Id) {
        this.teacher2Id = teacher2Id;
    }

    public String getTeacher2name() {
        return teacher2name;
    }

    public void setTeacher2name(String teacher2name) {
        this.teacher2name = teacher2name;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"teacher1Id\":")
                .append(teacher1Id);
        sb.append(",\"teacher1name\":\"")
                .append(teacher1name).append('\"');
        sb.append(",\"teacher2Id\":")
                .append(teacher2Id);
        sb.append(",\"teacher2name\":\"")
                .append(teacher2name).append('\"');
        sb.append('}');
        return sb.toString();
    }
}