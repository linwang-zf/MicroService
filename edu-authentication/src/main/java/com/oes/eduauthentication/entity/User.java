package com.oes.eduauthentication.entity;


import com.oes.eduauthentication.enums.Gender;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * (User)实体类
 *
 * @author makejava
 * @since 2020-03-27 14:40:14
 */
@ApiModel(value = "User",description = "普通用户")
@Getter
@Setter
@ToString
public class User implements Serializable {
    private static final long serialVersionUID = -65703925559121529L;
    
    private long userid;
    
    private String account;
    
    private String password;
    
    private String nickname;
    
    private String phone;
    
    private String mail;
    /**
    * 0-男，1-女
    */
    @ApiModelProperty(value = "性别",dataType = "Gender",example = "0",notes = "0-男，1-女")
    private Gender gender;
    
    private LocalDate birth;
    
    private long profilePhoto;
    /**
    * 0:未认证,1:已认证
    */
    private boolean authenticated;  //boolean
    /**
    * 0:未关联,1-已关联
    */
    private boolean hasBankcard;  //boolean
    
    private int defaultRole;
    
    private int role1;

    private int role2;

    private int role3;
}