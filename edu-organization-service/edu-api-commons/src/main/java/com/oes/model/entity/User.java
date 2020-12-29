package com.oes.model.entity;


import com.oes.model.enums.Gender;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;


@Data
public class User {
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
    private Gender gender;

    private Date birth;

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
