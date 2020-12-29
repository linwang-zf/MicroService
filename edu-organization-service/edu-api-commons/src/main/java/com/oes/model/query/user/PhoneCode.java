package com.oes.model.query.user;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PhoneCode {
    private String phone;

    private String verifyCode;
}
