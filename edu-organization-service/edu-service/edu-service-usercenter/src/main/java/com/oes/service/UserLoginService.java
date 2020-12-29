package com.oes.service;

import com.alibaba.druid.support.json.JSONUtils;
import com.oes.Exceptions.database.DBOperateException;
import com.oes.config.Url;
import com.oes.constant.consist.VerifyCodeConstants;
import com.oes.constant.enums.BusinessType;
import com.oes.dao.UsersDao;
import com.oes.model.dto.BaseResultDTO;
import com.oes.model.entity.User;
import com.oes.model.query.FindPwdQuery;
import com.oes.model.query.LoginInfo;
import com.oes.model.query.user.PhoneCode;
import com.oes.model.query.user.UserPrimaryKey;
import com.oes.util.http.HttpResult;
import com.oes.util.jwt.JwtUtil;
import com.oes.util.redis.RedisOperator;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

@Service
public class UserLoginService {
    @Resource
    private UsersDao usersDao;
    @Resource
    private RedisOperator redisOperator;
    @Resource
    private RestTemplate restTemplate;

    /** 验证用户登录 */
    public BaseResultDTO verifyAccount(LoginInfo loginInfo) {
        String userInfo = loginInfo.getAccount();
        String password = loginInfo.getPassword();
        User userByInfo = getUserByInfo(userInfo);
        if (null != userByInfo && password.equals(userByInfo.getPassword()))  {
            userByInfo.setPassword(""); /*抹除用户密码信息*/
            return new BaseResultDTO(BusinessType.SELECT, true, "登录成功", userByInfo);
        } else
            return new BaseResultDTO(false, "账号或密码错误");
    }

    /** 手机验证码登录
     * */
    public BaseResultDTO loginBySms(PhoneCode phoneCode) {
        String phone = phoneCode.getPhone();
        String verifyCode = phoneCode.getVerifyCode();
        String key = VerifyCodeConstants.PHONE_VERIFY_CODE_REDIS_KEY_PREFIX + phone;
        Object object = redisOperator.get(key);
        if (null == object) {
            return new BaseResultDTO(false, "验证码已过期");
        }
        String verifyCodeValue = (String)object;
        if (verifyCode.equals(verifyCodeValue)) {
            UserPrimaryKey userPrimaryKey = new UserPrimaryKey();
            userPrimaryKey.setPhone(phone);
            User user = usersDao.queryByPrimaryKey(userPrimaryKey);
            if (null == user) {
                return new BaseResultDTO(false, "该手机号未注册");
            } else {
                user.setPassword("");
                return new BaseResultDTO(BusinessType.SELECT, true, "验证成功", user);
            }
        } else {
            return new BaseResultDTO(false, "验证失败");
        }
    }

    /** 验证手机、验证码 */
    public BaseResultDTO verifyPhone(String phone, String verifyCode) {
        String key = VerifyCodeConstants.PHONE_VERIFY_CODE_REDIS_KEY_PREFIX + phone;
        Object object = redisOperator.get(key);
        if (null == object) {
            return new BaseResultDTO(false, "验证码已过期");
        } else {
            String verifyCodeValue = (String)object;
            if (verifyCode.equals(verifyCodeValue))
                return new BaseResultDTO(true, "验证成功");
            else
                return new BaseResultDTO(false, "验证失败");
        }
    }

    /** 忘记密码，向用户手机号发送验证码
     * userInfo：用户的account或mail
     * TODO
     * */
    public BaseResultDTO forgetPwd(String userInfo) {
        User user = getUserByInfo(userInfo);
        if (null == user) {
            return new BaseResultDTO(false, "没有查询到该用户信息");
        }
        String phone = user.getPhone();
        return restTemplate.getForObject(Url.SERVICE_MSM+"/send/"+phone, BaseResultDTO.class);

    }

    /** 通过验证码修改密码*/
    public BaseResultDTO findPwdBySms(FindPwdQuery query) {
        User user = getUserByInfo(query.getUserInfo());
        if (null == user) {
            return new BaseResultDTO(false, "没有查询到该用户信息");
        }

        String phone = user.getPhone();
        String verifyCode = query.getVerifyCode();
        String newPwd = query.getNewPwd();
        Object object = redisOperator.get(VerifyCodeConstants.PHONE_VERIFY_CODE_REDIS_KEY_PREFIX + phone);
        if (null == object)
            return new BaseResultDTO(false, "验证码已过期");
        String trueVerifyCode = (String) object;
        if (trueVerifyCode.equals(verifyCode)) {
            //验证码正确，修改密码
            User updateUser = new User();
            updateUser.setUserid(user.getUserid());
            updateUser.setPassword(newPwd);
            int row = usersDao.update(updateUser);
            if (row > 0)
                return new BaseResultDTO(true, "密码修改成功");
            else
                throw new DBOperateException("数据库修改错误");
        } else {
            //验证码错误
            return new BaseResultDTO(false, "验证码错误");
        }
    }
    /***************************************************************/
    /*
     * 通过用户的主键得到用户信息
     * userPrimaryKey: 可能为用户的 mail/account/phone
     * 查询失败，返回null
     * */
    private User getUserByInfo(String userPrimaryKey) {
        /*userInfo是否为邮箱*/
        boolean isEmail = userPrimaryKey.matches(VerifyCodeConstants.MAIL_REGEX);
        /*userInfo是否为手机*/
        boolean isPhone = userPrimaryKey.matches(VerifyCodeConstants.PHONE_REGEX);
        try {
            UserPrimaryKey primaryKey = new UserPrimaryKey();
            if (isEmail)
                primaryKey.setMail(userPrimaryKey);
            else if (isPhone)
                primaryKey.setPhone(userPrimaryKey);
            else
                primaryKey.setAccount(userPrimaryKey);
            User user = usersDao.queryByPrimaryKey(primaryKey);
            return user;
        } catch (Exception e) {
            return null;
        }
    }

}
