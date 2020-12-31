package com.oes.controller;

import com.oes.model.entity.Role;
import com.oes.service.RoleService;
import com.oes.util.http.HttpResult;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoleController {
    private RoleService roleService;
    @GetMapping("/role/name")
    @ApiOperation("根据角色名获取roleId")
    public HttpResult getRoleId(@RequestParam String roleName){
        Role role = roleService.getByRoleName(roleName);
        if(role != null){
            return HttpResult.ok("查询成功",role);
        }else{
            return HttpResult.error("没有对应角色ID");
        }
    }
}
