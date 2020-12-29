package com.oes.vo;

import com.oes.model.entity.Student;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 学生前置信息类，只包括学生类别、家长id
 */
public class StudentPreInfo {
    private ArrayList<String> studentType = new ArrayList<>();
    private ArrayList<Parent> parents = new ArrayList<>();

    public StudentPreInfo(Student student) {
        String type = student.getType();
        String[] split = type.split(",");
        studentType.addAll(Arrays.asList(split));
        parents.add(new Parent(1,"Tom"));
        parents.add(new Parent(2,"Dolores"));
    }

    public ArrayList<String> getStudentType() {
        return studentType;
    }

    public void setStudentType(ArrayList<String> studentType) {
        this.studentType = studentType;
    }

    public ArrayList<Parent> getParents() {
        return parents;
    }

    public void setParents(ArrayList<Parent> parents) {
        this.parents = parents;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"studentType\":")
                .append(studentType);
        sb.append(",\"parents\":")
                .append(parents);
        sb.append('}');
        return sb.toString();
    }

    class Parent{
        long id;
        String name;

        public Parent(long id, String name) {
            this.id = id;
            this.name = name;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("{");
            sb.append("\"id\":")
                    .append(id);
            sb.append(",\"name\":\"")
                    .append(name).append('\"');
            sb.append('}');
            return sb.toString();
        }
    }
}
