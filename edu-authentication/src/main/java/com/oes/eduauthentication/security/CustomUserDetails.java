package com.oes.eduauthentication.security;

import com.oes.eduauthentication.model.entity.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * user对象
 * 实现了spring-security的UserDetails
 */

public class CustomUserDetails implements UserDetails, Serializable {

    private Integer id;
    private String account;
    private String password;
    private List<GrantedAuthority> authorities;

    public CustomUserDetails(String account, String password, List<Role> roles) {
        this.account = account;
        this.password = password;
        this.setRoles(roles);
    }

    public CustomUserDetails(Integer id, String account, String password, List<Role> roles) {
        this.id = id;
        this.account = account;
        this.password = password;
        this.setRoles(roles);
    }

    public CustomUserDetails(Integer id, int tmp, String account, String password, List<GrantedAuthority> authorities) {
        this.id = id;
        this.account = account;
        this.password = password;
        this.authorities = authorities;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /** 把自定义的Role转化为 GrantedAuthority */
    public void setRoles(List<Role> roles) {
        this.authorities = new ArrayList<>(roles.size());
        roles.forEach(item -> this.authorities.add(() -> "ROLE_" + item.getRoleName().toUpperCase()));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return account;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
