package com.oes.vo;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class OrganizationTeacherVO {
    private Integer value;
    private String label;
    private List<Map<String, Object>> children;
}
