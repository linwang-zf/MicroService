package com.oes.model.query.user;

import lombok.Data;

@Data
public class UserPrimaryKey {
    private static final long serialVersionUID = 5691605555482712118L;

    /*手机号*/
    private String phone;
    /*邮箱*/
    private String mail;
    /*账号*/
    private String account;
    /*用户id*/
    private Integer userid;
}
