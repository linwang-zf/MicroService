package com.oes.eduauthentication.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @Author: Jiang Xiaodan
 * @Date: 2020/8/8 20:03
 */
@Component
public class CustomPasswordEncoder implements PasswordEncoder {
    @Override
    // 加密
    public String encode(CharSequence rawPassword) {
        //rawPassword用户输入的密码 加密后存到数据库
        return rawPassword.toString();
    }

    @Override
    // 匹配密码
    public boolean matches(CharSequence rawPassword, String encodePassword) {
        //springsecurity调用
        // 登录时获取loadUserByUsernanme中返回的数据库密码和用户输入请求的密码进行比对
        return encodePassword.equals(rawPassword.toString());
    }
}
