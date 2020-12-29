package com.oes.model.dto;

import com.oes.model.entity.AuthenticatedUser;
import lombok.Data;

import java.util.List;

@Data
public class OrganizationTeacherDTO {
    private Integer value;
    private String label;
//    private List<Teacher> teachers;
    private List<AuthenticatedUser> children;
}
