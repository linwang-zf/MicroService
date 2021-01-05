package com.oes.controller;


import com.oes.model.dto.BaseResultDTO;
import com.oes.service.WorkerBindService;
import com.oes.util.http.HttpResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : JQK
 * @date : 2020-07-22 10:10
 * @description :
 */
@RestController
@Api(tags = {"员工挂靠"})
public class WorkerBindController {

    private final WorkerBindService workerBindService;

    public WorkerBindController(final WorkerBindService workerBindService) {
        this.workerBindService = workerBindService;
    }

    @GetMapping("/worker/process")
    @ApiOperation("查看员工挂靠进度")
    public HttpResult getRegisterProgress(@RequestParam long userid, @RequestParam int roleId) {
        int registerProcess = workerBindService.getRegisterProcess(userid, roleId);
        if (registerProcess == 1 || registerProcess == 0) return HttpResult.ok("未审核", 1);
        else if (registerProcess == 2) return HttpResult.ok("审核成功", registerProcess);
        else if (registerProcess == 3) return HttpResult.ok("审核失败", registerProcess);
        else return HttpResult.error("查询失败");
    }

    @GetMapping("/organization/worker/confirmation")
    @ApiOperation("管理员确认员工挂靠请求")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleId", value = "角色id", required = true),
            @ApiImplicitParam(name = "userId", value = "员工id", required = true),
            @ApiImplicitParam(name = "pass", value = "是否通过", required = true, dataType = "boolean"),
            @ApiImplicitParam(name = "reason", value = "原因")
    })
    public HttpResult confirmWorkerAttach(@RequestParam long roleId, @RequestParam long userId, @RequestParam boolean pass,
                                          @RequestParam(required = false) String reason) {
        BaseResultDTO resultDTO = workerBindService.confirmWorkerAttach(roleId, userId, pass, reason);
        if (resultDTO.isSuccess()) {
            return HttpResult.ok(resultDTO.getMessage());
        } else {
            return HttpResult.error(resultDTO.getMessage());
        }
    }

    @GetMapping("/worker/{userId}/isBind/organization")
    @ApiOperation("查询员工是否绑定机构")
    public HttpResult workerIsBindOrg(@PathVariable Integer userId) {
        BaseResultDTO resultDTO = workerBindService.workerIsBindOrg(userId);
        if (resultDTO.isSuccess()) {
            return HttpResult.ok(resultDTO.getData());
        } else {
            return HttpResult.error(resultDTO.getMessage());
        }
    }

}
