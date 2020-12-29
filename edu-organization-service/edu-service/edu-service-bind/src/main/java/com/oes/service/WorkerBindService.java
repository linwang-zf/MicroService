package com.oes.service;

import com.oes.Exceptions.database.DBOperateException;
import com.oes.dao.OrganizationWorkersDao;
import com.oes.entity.OrganizationWorker;
import com.oes.model.dto.BaseResultDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author : JQK
 * @date : 2020-07-22 10:11
 * @description :
 */
@Service
@Slf4j
public class WorkerBindService {

    private final OrganizationWorkersDao workersDao;

    public WorkerBindService(final OrganizationWorkersDao workersDao) {
        this.workersDao = workersDao;
    }

    public int getRegisterProcess(long userid, int roleId) {
        OrganizationWorker worker = workersDao.queryById(userid, roleId);
        return worker.getChecked();
    }

    /** 查询用户是否绑定过机构 */
    public BaseResultDTO workerIsBindOrg(Integer userId) {
        List<OrganizationWorker> organizationWorkers = workersDao.queryByUserId(userId);
        Map<String, Object> data = new HashMap<>();
        if (null == organizationWorkers || organizationWorkers.isEmpty()) {
            data.put("isBind", false);
        } else {
            data.put("isBind", true);
        }
        return new BaseResultDTO(true, data);
    }

    /**
     * 管理员确认员工挂靠请求
     */
    public BaseResultDTO confirmWorkerAttach(long roleId, long userId, boolean pass, String reason) {
        OrganizationWorker organizationWorker = workersDao.queryById(userId, roleId);
        if (Objects.isNull(organizationWorker)) {
            return new BaseResultDTO(false, "请确定该员工信息是否存在");
        }
        if (pass) {
            organizationWorker.setChecked(2);
        } else {
            organizationWorker.setChecked(3);
            organizationWorker.setFail_reason(reason);
        }
        int row = workersDao.update(organizationWorker);
        if (row > 0) {
            log.info("id为{}的教师挂靠{}", userId, (pass) ? ("成功，挂靠角色id为：" + roleId) : ("失败，原因为：" + reason));
            return new BaseResultDTO(true, (pass) ? "挂靠成功" : "挂靠已拒绝");
        } else {
            throw new DBOperateException("挂靠失败，数据库处理错误");
        }
    }

}
