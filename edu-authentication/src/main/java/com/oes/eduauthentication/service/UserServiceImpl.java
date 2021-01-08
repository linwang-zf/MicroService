package com.oes.eduauthentication.service;

import cn.hutool.core.collection.CollUtil;
import com.oes.eduauthentication.constant.MessageConstant;
import com.oes.eduauthentication.dao.RolesDao;
import com.oes.eduauthentication.dao.UsersDao;
import com.oes.eduauthentication.entity.Role;
import com.oes.eduauthentication.entity.User;
import com.oes.eduauthentication.model.SecurityUser;
import com.oes.eduauthentication.model.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 用户管理业务类
 * Created by macro on 2020/6/19.
 */
@Service
@Slf4j
public class UserServiceImpl implements UserDetailsService {

    @Resource
    private UsersDao usersDao;
    @Resource
    private RolesDao rolesDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("登录用户名：" + username);
        User accountUser = usersDao.queryByAccount(username);
        if(accountUser == null) {
            throw new InternalAuthenticationServiceException("用户名或密码错误!");
        }
        // 用dao根据用户名从数据库查出用户信息
        // User类实现了UserDetails接口 可以根据需求定制自己的UserDetails实现类返回
        // 密码校验逻辑 告诉security从数据库读出的密码是多少
        // principal里的就是UserDetails返回的内容
        return getUserDetails(accountUser);
    }

    public UserDetails loadUserByPhone(String phone) throws UsernameNotFoundException {
        log.info("登录手机号：" + phone);
        User phoneUser = usersDao.queryByPhone(phone);
        return getUserDetails(phoneUser);
    }

    private UserDetails getUserDetails(User user) {
        int defaultRole = user.getDefaultRole();
        int role1 = user.getRole1();
        int role2 = user.getRole2();
        int role3 = user.getRole3();
        Set<Integer> roleIds = new HashSet<>();
        if (defaultRole != 0) roleIds.add(defaultRole);
        if (role1 != 0) roleIds.add(role1);
        if (role2 != 0) roleIds.add(role2);
        if (role3 != 0) roleIds.add(role3);
        List<Role> roles = rolesDao.selectBatch(roleIds);
        if(roles != null) {
            log.info("roles非空");
            log.info(roles.toString());
        }
        StringBuilder authorityString = new StringBuilder();
        for (Role role : roles) {
            authorityString.append(role.getRoleName().toUpperCase()).append(",");
            log.info(role.getRoleName());
        }
        authorityString.deleteCharAt(authorityString.length()-1);
        log.info(authorityString.toString());
        log.info("id: {}, account: {} 用户已登录...", user.getUserid(), user.getAccount());
        return new CustomUserDetails((int) user.getUserid(), 0, user.getAccount(), user.getPassword(), AuthorityUtils.commaSeparatedStringToAuthorityList(authorityString.toString()));
    }

}
