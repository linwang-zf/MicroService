package com.oes.controller;


import com.oes.model.dto.BaseResultDTO;
import com.oes.model.entity.AuthenticatedUser;
import com.oes.model.entity.Picture;
import com.oes.service.AuthUserService;
import com.oes.util.http.HttpResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Api(tags = {"认证用户"})
public class AuthUserController {
    @Resource
    private AuthUserService authUserService;


    @ApiOperation("增加认证用户")
    @PostMapping("/AuthenticatedUser")
    public HttpResult addAuthenticatedUser(@RequestBody AuthenticatedUser user) {
        BaseResultDTO resultDTO = authUserService.addAuthUser(user);
        if (resultDTO.isSuccess()) {
            return HttpResult.ok(resultDTO.getMessage());
        } else {
            return HttpResult.error(resultDTO.getMessage());
        }
    }

    @ApiOperation("更新认证用户信息")
    @PutMapping("/AuthenticatedUser")
    public HttpResult updateAuthenticatedUser(@RequestBody AuthenticatedUser user){
        if (user.getUserid() == 0L) {
            return HttpResult.error("没有用户id信息");
        }
        int row = authUserService.updateUser(user);
        if (row > 0) {
            return HttpResult.ok("更新成功");
        } else {
            return HttpResult.error("更新失败");
        }
    }

    @ApiOperation("查询认证用户信息")
    @GetMapping("/AuthenticatedUser/{userid}")
    public HttpResult getAuthenticatedUser(@PathVariable long userid) {
        AuthenticatedUser authenticatedUser = authUserService.queryById(userid);
        if(authenticatedUser == null) {
            return HttpResult.error("该用户未认证");
        } else {
            return HttpResult.ok(authenticatedUser);
        }
    }

    @ApiOperation("上传身份证照片")
    @PostMapping("/AuthenticatedUser/idCardPhoto")
    public HttpResult postIdCardPhoto(@RequestBody Picture picture){
        BaseResultDTO resultDTO = authUserService.addIdCardPhoto(picture);
        if (resultDTO.isSuccess()) {
            return HttpResult.ok(resultDTO.getMessage(), resultDTO.getData());
        } else {
            return HttpResult.error(resultDTO.getMessage());
        }
    }


    /****************************************对外API*******************/
    /*
    * 1.courseService
    * */
    @GetMapping("/AuthenticatedUser/api/all")
    public HttpResult getAllAuth(){
        List<AuthenticatedUser> result = authUserService.getAllAuth();
        return HttpResult.ok("查询成功",result);
    }

    @PostMapping("/AuthenticatedUser/api/names")
    public HttpResult queryNamesById(@RequestBody List<Integer> userIds){
        List<AuthenticatedUser> result = authUserService.queryNamesById(userIds);
         return HttpResult.ok("查询成功", result);
    }
}
