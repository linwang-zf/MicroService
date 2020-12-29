package com.oes.cotroller;

import com.alibaba.fastjson.JSONObject;

import com.oes.model.dto.BaseResultDTO;
import com.oes.model.entity.Organization;
import com.oes.model.query.OrganizationQuery;
import com.oes.service.OrganizationsService;
import com.oes.util.http.HttpResult;
import com.oes.vo.OrganizationVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author : yanjundong
 * @date : 2020-03-30 13:28
 * @description : 机构管理
 */

@RestController
@Api(tags = {"机构管理"})
public class OrganizationController {
    @Resource
    private OrganizationsService organizationService;

    @PostMapping("/organization")
    @ApiOperation(value = "机构信息录入")
    @ApiImplicitParam(name = "organization", value = "机构信息", dataType = "Organization", required = true)
    public HttpResult addOrganization(@RequestBody Organization organization) {
        int row = organizationService.addOrganization(organization);
        if (row > 0) {
            return HttpResult.ok("机构信息录入成功", organization.getOrgId());
        } else {
            return HttpResult.error("机构信息录入失败");
        }
    }

/*    @GetMapping("/organization/{org_id}/qrcode")
    @ApiOperation(value = "根据机构id获取机构的二维码")
    @ApiImplicitParam(name = "org_id", value = "机构id", required = true)
    public void getOrgQRCode(@PathVariable String org_id, HttpServletResponse response) {
        byte[] orgQRCode = organizationService.getOrgQRCode(Long.valueOf(org_id));
        try {
            response.setHeader("Content-Type", "image/png");
            response.getOutputStream().write(orgQRCode);
            response.flushBuffer();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    /**
     * 机构信息修改
     */
    @PutMapping("/organization")
    @ApiOperation(value = "机构信息修改")
    @ApiImplicitParam(name = "organization", value = "机构信息", required = true, dataType = "Organization")
    public HttpResult updateOrganization(@RequestBody Organization organization) {
        int row = organizationService.updateOrganization(organization);
        return HttpResult.ok("信息修改成功!");
    }

    @GetMapping("/organization")
    @ApiOperation("通过机构名称查询机构(支持模糊查询)")
    @ApiImplicitParam(name = "orgName", value = "机构名称的模糊信息", required = true)
    public HttpResult queryOrgByName(@RequestParam String orgName) {
        List<Organization> organizations = organizationService.queryByName(orgName);
        return HttpResult.ok("查询成功", organizations);
    }

    @GetMapping("/organizations/all")
    @ApiOperation("查询全部机构")
    public HttpResult getAllOrgs() {
        List<Organization> organizations = organizationService.queryByName("");
        return HttpResult.ok("查询成功", organizations);
    }

    @GetMapping("/organization/{org_id}")
    @ApiOperation("获取机构的详细信息")
    @ApiImplicitParam(name = "org_id", value = "机构id", required = true)
    public HttpResult getOrg(@PathVariable String org_id) {
        OrganizationVo orgInfo = organizationService.getOrgInfoById(Long.valueOf(org_id));
        if (orgInfo != null) {
            JSONObject object = new JSONObject();
            object.put("orgInfo", orgInfo);
            return HttpResult.ok("获取成功", object);
        } else return HttpResult.error("获取失败");
    }

    @DeleteMapping("/organization/{id}")
    @ApiImplicitParam(name = "id", value = "机构id", required = true)
    @ApiOperation(value = "机构注销")
    public HttpResult deleteOrganization(@PathVariable long id) {
        OrganizationVo orgInfo = organizationService.getOrgInfoById(id);
        int row = organizationService.deleteOrg(id);
        if (row > 0) {
            return HttpResult.ok("机构注销成功!");
        }
        return HttpResult.error("机构注销失败");
    }

    @GetMapping("/organization/user/{userId}")
    @ApiOperation(value = "根据用户id查询是否注册过机构")
    @ApiImplicitParam(name = "userId", value = "用户id", required = true)
    public HttpResult getOrgByUserId(@PathVariable String userId) {
        BaseResultDTO resultDTO = organizationService.getRegisterStateByUserId(Long.valueOf(userId));
        if (resultDTO.isSuccess()) {
            return HttpResult.ok(resultDTO.getMessage(), resultDTO.getData());
        } else {
            return HttpResult.error(resultDTO.getMessage());
        }
    }

    @GetMapping("/organization/process")
    @ApiOperation("注册进度查看")
    @ApiImplicitParam(name = "orgId", value = "机构id", required = true)
    public HttpResult getRegisterProgress(@RequestParam long orgId) {
        BaseResultDTO resultDTO = organizationService.getRegisterProgress(orgId);
        if (resultDTO.isSuccess()) {
            return HttpResult.ok(resultDTO.getMessage(), resultDTO.getData());
        } else {
            return HttpResult.error(resultDTO.getMessage());
        }
    }

    @PostMapping("/organization/getPassOrganization")
    @ApiOperation("平台/二级管理员获取全部审核通过的机构列表")
    public HttpResult getOrgPass(@RequestBody OrganizationQuery query){
        BaseResultDTO resultDTO = organizationService.getOrganizationsByState(query);
        if(resultDTO.isSuccess()){
            return HttpResult.ok("获取成功", resultDTO.getData());
        }else{
            return HttpResult.error("获取失败");
        }
    }

    @PostMapping("/organization/getOrganizations")
    @ApiOperation("获取所有机构的信息（分页/过滤/带图片）")
    public HttpResult getOrganizations(@RequestBody OrganizationQuery organizationQuery){
        List<OrganizationVo> organizations = organizationService.getOrganizations(organizationQuery);
        if(organizations != null){
            return HttpResult.ok("获取成功",organizations);
        }else {
            return HttpResult.error("获取失败");
        }
    }

    @GetMapping("/organization/getOrganizationTeachers")
    @ApiOperation("查询所有机构的所有教师")
    public HttpResult getOrganizationTeachers() {
        BaseResultDTO baseResultDTO = organizationService.getOrganizationTeacher();
        if(baseResultDTO.isSuccess()) {
            return HttpResult.ok(baseResultDTO.getData());
        } else {
            return HttpResult.error("查询失败");
        }
    }



}
