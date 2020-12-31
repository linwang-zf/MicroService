package com.oes.info;


import com.oes.model.entity.AuthenticatedUser;
import com.oes.model.entity.Teacher;
import com.oes.model.entity.User;
import com.oes.model.info.OrgInfo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class TeacherInfo {
    private User user;
    private AuthenticatedUser authenticatedUser;
    private Teacher teacher;
    private OrgInfo orgInfo;
}
