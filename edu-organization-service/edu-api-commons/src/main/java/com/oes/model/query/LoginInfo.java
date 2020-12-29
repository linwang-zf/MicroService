package com.oes.model.query;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "LoginInfo",description = "登录的模型，包括账号，密码")
public class LoginInfo {
    @ApiModelProperty(value = "account/phone",example = "zhangsan")
    String account;
    @ApiModelProperty(value = "密码",example = "zhangsan")
    String password;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginInfo{" +
                "account='" + account + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
