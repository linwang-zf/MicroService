package com.oes.controller;

import com.oes.vo.WorkerVO;
import com.oes.entity.OrganizationWorker;
import com.oes.model.dto.BaseResultDTO;
import com.oes.service.WorkerService;
import com.oes.util.http.HttpResult;
import com.oes.util.http.HttpStatus;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author : yanjundong
 * @date : 2020-03-30 13:28
 * @description : 员工管理
 */

@RestController
@Api(tags = {"一般管理员"})
public class WorkersController {
    @Resource
    private  WorkerService workerService;





    @GetMapping("/worker/organization/{orgId}")
    @ApiOperation("按照机构id查询所有已审核员工")
    public HttpResult getWorkerByOrgId(@PathVariable long orgId) {
        // 只返回一般管理员
        List<WorkerVO> orgWorkers = workerService.getOrgWorkers(orgId);
        if (orgWorkers.isEmpty()) return HttpResult.ok("该机构尚未有员工", orgWorkers);
        else return HttpResult.ok("查询成功", orgWorkers);
    }

    @GetMapping("/worker/{orgId}/unchecked")
    @ApiOperation("通过机构查询所有未审核员工")
    public HttpResult getAllUnchecked(@PathVariable String orgId) {
        return workerService.getAllUnchecked(Long.valueOf(orgId));
    }

    @GetMapping("/worker/organization/{org_id}/{userId}/{roleId}")
    @ApiOperation("获取一个机构的员工详细信息")
    public HttpResult getWorkerInfoByUserId(@PathVariable String org_id,@PathVariable String userId, @PathVariable String roleId) {
        WorkerVO workerVO = workerService.queryWorkerByUserId(Long.valueOf(org_id),Long.parseLong(userId),Integer.parseInt(roleId));

        if (workerVO == null) return HttpResult.error();
        else return HttpResult.ok("查询成功", workerVO);
    }


    @GetMapping("/worker/{id}")
    @ApiOperation(value = "通过员工id获得员工的全部角色记录")
    public HttpResult getWorkerById(@PathVariable String id) {
        BaseResultDTO result = workerService.getOrgWorkerById(Long.valueOf(id));
        if (result.isSuccess()) {
            return HttpResult.ok(result.getMessage(), result.getData());
        } else {
            return HttpResult.error(result.getMessage());
        }
    }

    @PostMapping("/worker")
    @ApiOperation("工作人员信息登记")
    public HttpResult addWorker(@RequestBody OrganizationWorker worker) {
        return workerService.addWorker(worker);
    }

    @PutMapping("/worker")
    @ApiOperation("工作人员信息修改")
    public HttpResult updateWorker(@RequestBody OrganizationWorker worker) {
        return workerService.updateWorker(worker);
    }



    /**
     * 角色绑定
     */
    @ApiOperation("角色绑定")
    @PostMapping("/worker/role")
    public HttpResult bindWorker(long userId, int roleId) {
        HttpResult r = new HttpResult();
        if (workerService.bindWorker(userId, roleId)) {
            r.setMsg("绑定成功");
        } else {
            r.setMsg("绑定失败");
            r.setCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);
        }
        return r;
    }

    @ApiOperation("绑定机构和总管理员")
    @GetMapping("/worker/role/totalAdmin")
    public HttpResult bindTotalAdmin(long userId, long orgId) {
        boolean b = workerService.bindTotalAdmin(userId, orgId);
        if (b) return HttpResult.ok("绑定成功", true);
        else return HttpResult.error("绑定失败");
    }

    /**
     * 绑定关系作废
     */
    @ApiOperation("绑定关系作废")
    @DeleteMapping("/worker/role")
    public HttpResult unBindWorker(@ApiParam("被解绑的用户id") @RequestParam long userid, @ApiParam("机构id") @RequestParam long orgId,
                                   @ApiParam("解绑的角色名字") @RequestParam String roleName) {
        boolean b = workerService.unBindWorker(orgId, userid, roleName);
        HttpResult result = new HttpResult();
        if (b) {
            result.setMsg("解绑成功");
        } else {
            result.setMsg("解绑失败");
            result.setCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);
        }
        return result;
    }


}
