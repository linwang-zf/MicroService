package com.oes.model.info;
import com.oes.model.entity.Student;
import com.oes.model.entity.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class StudentInfo {
    private Student student;
    private User user;
    private OrgInfo orgInfo;
}
