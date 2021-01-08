package com.oes.eduauthentication.service;

import cn.hutool.core.collection.CollUtil;
import com.oes.eduauthentication.constant.RedisConstant;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 资源与角色匹配关系管理业务类
 */
@Service
public class ResourceServiceImpl {

    private Map<String, List<String>> resourceRolesMap;

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @PostConstruct
    public void initData() {
        resourceRolesMap = new TreeMap<>();
        resourceRolesMap.put("/user/**",CollUtil.toList("ROLE_NORMAL_USER"));
        resourceRolesMap.put("/student/**",CollUtil.toList("ROLE_NORMAL_USER","ROLE_STUDENT"));
        resourceRolesMap.put("/orgnization/**", CollUtil.toList("ROLE_TOTAL_ADMIN","ROLE_GENERAL_ADMIN"));
        redisTemplate.opsForHash().putAll(RedisConstant.RESOURCE_ROLES_MAP, resourceRolesMap);
    }
}
