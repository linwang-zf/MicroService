package com.oes.model.entity;

import io.swagger.annotations.ApiModel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * (AuthenticatedUser)实体类
 *
 * @author makejava
 * @since 2020-03-27 14:17:45
 */
@Getter
@Setter
@ToString
@ApiModel(value = "AuthenticatedUser",description = "授权用户模型")
public class AuthenticatedUser implements Serializable {
    private static final long serialVersionUID = -58394350543582499L;

    private Integer userid;

    private String idcard;

    private Integer idcardPhoto;

    private String name;

    private String province;

    private String city;

    private String district;

    private String qq;

    private String weixin;
}