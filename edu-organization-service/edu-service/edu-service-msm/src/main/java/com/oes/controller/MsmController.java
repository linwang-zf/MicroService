package com.oes.controller;

import com.alibaba.fastjson.JSONObject;
import com.oes.constant.consist.CodeConstants;
import com.oes.model.query.user.PhoneCode;
import com.oes.service.MsmService;
import com.oes.util.http.HttpResult;
import com.oes.util.redis.RedisOperator;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.Random;

@RestController
@CrossOrigin
public class MsmController {
    @Resource
    private MsmService msmService;
    @Resource
    private RedisOperator redisOperator;

    //发送短信请求
    @GetMapping("send/{phone}")
    public HttpResult sendVerifyCode(@PathVariable String phone){
        String key = CodeConstants.PHONE_VERIFY_CODE_REDIS_KEY_PREFIX + phone;
        //1 从redies获取验证码，如果获取到直接返回
        boolean isHasKey = redisOperator.hasKey(key);
        if(isHasKey){
            return HttpResult.ok("短信验证码发送成功");
        }
        //2 如果redies获取不到，进行短信发送
        System.out.print("---------send----");
        //生成随机值，传递给发送短信接口进行发送
        Random random = new Random();
        int i = random.nextInt(999999);
        String verifyCode = String.format("%06d", i);
        //调用service发送短信的方法
        boolean isSend = msmService.send(phone, verifyCode);
        if (isSend) {
            //发送成功，则把发送成功的验证码放到redis里面
            //设置有效时间
            redisOperator.set(key, verifyCode, CodeConstants.VERIFY_CODE_EXPIRE);
            return HttpResult.ok("短信验证码发送成功");
        } else {
            return HttpResult.error("短信验证码发送失败");
        }
    }

}
