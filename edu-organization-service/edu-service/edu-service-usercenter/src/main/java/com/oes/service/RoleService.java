package com.oes.service;

import com.oes.dao.RolesDao;
import com.oes.model.entity.Role;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class RoleService {
    @Resource
    private RolesDao rolesDao;

    public Role getByRoleName(String roleName){
        return rolesDao.queryByName(roleName);
    }

}
