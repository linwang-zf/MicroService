package com.oes.controller;

import com.alibaba.druid.support.json.JSONUtils;
import com.oes.config.Url;
import com.oes.constant.consist.VerifyCodeConstants;
import com.oes.model.dto.BaseResultDTO;
import com.oes.model.entity.User;
import com.oes.model.query.FindPwdQuery;
import com.oes.model.query.LoginInfo;
import com.oes.model.query.user.PhoneCode;
import com.oes.service.UserLoginService;
import com.oes.util.http.HttpResult;
import com.oes.util.jwt.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
@Api(tags = {"用户登录、忘记密码等"})
public class UserLoginController {
    @Resource
    private UserLoginService userLoginService;
    @Resource
    private RestTemplate restTemplate;

    @ApiOperation("用户登录")
    @PostMapping("/user/login")
    public HttpResult login(@RequestBody LoginInfo loginInfo, HttpServletResponse response) {
        BaseResultDTO resultDTO = userLoginService.verifyAccount(loginInfo);
        System.out.println("result"+resultDTO.getData());
        if (resultDTO.isSuccess()) {
            User userByInfo = (User) resultDTO.getData();
            System.out.print(resultDTO.getData());
            String userId = String.valueOf(userByInfo.getUserid());
            //创建用户令牌信息
            Map<String, Object> tokenMap = new HashMap<String, Object>();
            tokenMap.put("role", userByInfo.getDefaultRole());//用户角色
            tokenMap.put("account", userByInfo.getAccount());
            tokenMap.put("userId", userId);
            String token = JwtUtil.createJWT(userId, JSONUtils.toJSONString(tokenMap), null);
            //把令牌信息存入到Cookie
            Cookie cookie = new Cookie("Authorization",token);
//            cookie.setDomain("localhost");
            response.addCookie(cookie);
            return HttpResult.ok(resultDTO.getMessage(), resultDTO.getData());
        } else {
            return HttpResult.error(resultDTO.getMessage());
        }
    }

    @ApiOperation("手机验证码登录")
    @PostMapping("/user/loginBySms")
    public HttpResult loginBySms(@RequestBody PhoneCode phoneCode) {
        BaseResultDTO resultDTO = userLoginService.loginBySms(phoneCode);
        if (resultDTO.isSuccess()) {
            return HttpResult.ok(resultDTO.getMessage(), resultDTO.getData());
        } else {
            return HttpResult.error(resultDTO.getMessage());
        }
    }

    @GetMapping("/user/verifyCode")
    @ApiOperation("发送短信验证码")
    @ApiImplicitParam(name = "phone", value = "手机号", required = true)
    public HttpResult sendVerifyCode(@RequestParam String phone) {
        //boolean isSend = userLoginService.sendVerifyCode(phone);
        return restTemplate.getForObject(Url.SERVICE_MSM+"/send/"+phone, HttpResult.class);
    }

    @PostMapping("/user/findPwdBySms")
    @ApiOperation(value = "通过手机验证码修改密码")
    @ApiImplicitParam(name = "query", value = "修改密码的参数信息", required = true, dataType = "FindPwdQuery")
    public HttpResult findPwdBySms(@RequestBody FindPwdQuery query) {
        BaseResultDTO resultDTO = userLoginService.findPwdBySms(query);
        if (resultDTO.isSuccess()) {
            return HttpResult.ok(resultDTO.getMessage());
        } else {
            return HttpResult.error(resultDTO.getMessage());
        }
    }

    @PostMapping("/user/phoneVerification")
    @ApiOperation("验证手机")
    public HttpResult verifyPhone(@RequestBody PhoneCode phoneCode) {
        BaseResultDTO resultDTO = userLoginService.verifyPhone(phoneCode.getPhone(), phoneCode.getVerifyCode());
        if (resultDTO.isSuccess()) {
            return HttpResult.ok(resultDTO.getMessage(), true);
        } else {
            return HttpResult.error(resultDTO.getMessage(), false);
        }
    }
    /**
     * TODO
     * @param userInfo
     * @return
     */
    @GetMapping("/user/findPwd")
    @ApiOperation(value = "找回密码，通过用户的account/邮箱/手机发送验证码")
    @ApiImplicitParam(name = "userInfo", value = "用户的account/mail/phone", required = true)
    public HttpResult sendVerifyCodeByUserInfo(@RequestParam String userInfo) {
        BaseResultDTO resultDTO = userLoginService.forgetPwd(userInfo);
        if (resultDTO.isSuccess()) {
            return HttpResult.ok(resultDTO.getMessage());
        } else {
            return HttpResult.error(resultDTO.getMessage());
        }
    }



}
