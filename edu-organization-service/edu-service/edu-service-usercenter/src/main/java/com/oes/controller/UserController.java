package com.oes.controller;


import com.oes.model.dto.BaseResultDTO;

import com.oes.model.query.user.UserRegisterQuery;
import com.oes.model.query.user.UserUpdateQuery;
import com.oes.model.vo.user.UserVo;
import com.oes.service.UserService;

import com.oes.util.http.HttpResult;
import com.oes.util.http.HttpStatus;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

import static com.sun.activation.registries.LogSupport.log;


@RestController
@Api(tags = {"普通用户"})
public class UserController {
    @Resource
    private UserService userService;

    @PostMapping("/user")
    @ApiOperation("用户注册")
    public HttpResult register(@RequestBody UserRegisterQuery query) {
        log("-----------用户注册-------");
        BaseResultDTO resultDTO = userService.register(query);
        if (resultDTO.isSuccess()) {
            return HttpResult.ok(resultDTO.getMessage());
        } else {
            return HttpResult.error(resultDTO.getMessage());
        }
    }

    @ApiOperation("更新用户信息")
    @PutMapping("/user")
    public HttpResult updateUser(@RequestBody UserUpdateQuery query) {
        BaseResultDTO resultDTO = userService.update(query);
        if (resultDTO.isSuccess()) {
            return HttpResult.ok(resultDTO.getMessage());
        } else {
            return HttpResult.error(resultDTO.getMessage());
        }
    }

    @ApiOperation("根据id查询用户")
    @GetMapping("/user/{id}")
    public HttpResult queryUser(@ApiParam(value = "用户id", example = "1") @PathVariable String id) {
        UserVo user = userService.queryById(Long.valueOf(id));
        if (user!=null){
            return HttpResult.ok("查询成功",user);
        }else return HttpResult.error(HttpStatus.SC_BAD_REQUEST,"查询失败");
    }

    @ApiOperation("根据id删除用户")
    @DeleteMapping("/user/{id}")
    public HttpResult deleteUser(@ApiParam(value = "用户id", example = "1") @PathVariable String id) {
        return userService.deleteUser(Long.valueOf(id));
    }

    @ApiOperation("查询是否认证")
    @GetMapping("/user/Authentication")
    public HttpResult verifyAuthentication(long userid) {
        return userService.verifyAuthentication(userid);
    }

    @ApiOperation("查询用户名是否存在")
    @GetMapping("/user/account/repetition")
    public HttpResult checkAccount(String account) {
        if (userService.checkedAccount(account)) return HttpResult.ok("用户名可用");
        else return HttpResult.error(HttpStatus.SC_BAD_REQUEST, "用户名已存在");
    }


    @GetMapping("/user/phone/repetition")
    @ApiOperation("手机是否已注册")
    public HttpResult checkPhone(@RequestParam String phone) {
        BaseResultDTO baseResultDTO = userService.checkedPhone(phone);
        if (baseResultDTO.isSuccess()) {
            return HttpResult.ok(baseResultDTO.getMessage());
        } else {
            return HttpResult.error(baseResultDTO.getMessage());
        }
    }
}
