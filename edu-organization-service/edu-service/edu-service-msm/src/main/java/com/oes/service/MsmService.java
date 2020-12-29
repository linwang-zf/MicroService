package com.oes.service;

import com.alibaba.fastjson.JSONObject;
import com.oes.constant.consist.CodeConstants;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.oes.util.redis.RedisOperator;

import javax.annotation.Resource;
import java.util.Random;

@Service
public class MsmService {
    @Resource
    private RedisOperator redisOperator;

    /** 发送短信验证码 */
    public boolean send(String phone,String verifyCode) {
        RestTemplate restTemplate = new RestTemplate();
        String url = CodeConstants.VERIFYCODEURL + phone + "&content=" + verifyCode + CodeConstants.VERIFYCODEEND;
        String result = restTemplate.postForObject(url, new JSONObject(), String.class);

        return !Boolean.parseBoolean(result);
    }
}
