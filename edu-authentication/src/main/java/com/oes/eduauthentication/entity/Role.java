package com.oes.eduauthentication.entity;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * (Role)实体类
 *
 * @author makejava
 * @since 2020-03-27 14:40:13
 */
@ApiModel(value = "Role",description = "角色对应模型")
@Getter
@Setter
@ToString
public class Role implements Serializable {
    private static final long serialVersionUID = 662113320612748878L;
    
    private int roleid;
    
    private String roleName;
    
    private String roleTableName;
}