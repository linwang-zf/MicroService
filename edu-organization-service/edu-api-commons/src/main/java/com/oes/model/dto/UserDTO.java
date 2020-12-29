package com.oes.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.oes.model.enums.Gender;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Date;

public class UserDTO {
    private long userid;

    private String account;

    private String nickname;

    private String phone;

    private String mail;

    private Gender gender;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date birth;

    public long getUserid() {
        return userid;
    }

    public void setUserid(long userid) {
        this.userid = userid;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("{");
        sb.append("\"userid\":")
                .append(userid);
        sb.append(",\"account\":\"")
                .append(account).append('\"');
        sb.append(",\"nickname\":\"")
                .append(nickname).append('\"');
        sb.append(",\"phone\":\"")
                .append(phone).append('\"');
        sb.append(",\"mail\":\"")
                .append(mail).append('\"');
        sb.append(",\"gender\":")
                .append(gender);
        sb.append(",\"birth\":\"")
                .append(birth).append('\"');
        sb.append('}');
        return sb.toString();
    }
}
