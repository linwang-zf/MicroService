package com.oes.model.query;

import lombok.Getter;
import lombok.Setter;

/**
 * @author : JQK
 * @date : 2020-07-10 21:20
 * @description :
 */
@Getter
@Setter
public class FindPwdQuery {
    /* account/mail */
    private String userInfo;
    /*验证码*/
    private String verifyCode;
    /*新的密码*/
    private String newPwd;
}
