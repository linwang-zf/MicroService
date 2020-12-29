package com.oes.model.query.user;

import com.oes.model.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * @author : JQK
 * @date : 2020-07-25 20:21
 * @description : 用户注册参数
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterQuery implements Serializable {

    private String account;
    private String password;
    private String nickname;
    private String phone;
    private String mail;
    private Gender gender;
    private LocalDate birth;
    private int defaultRole;
    private int role1;
    private int role2;
    private int role3;
    private long profilePhoto;
}
