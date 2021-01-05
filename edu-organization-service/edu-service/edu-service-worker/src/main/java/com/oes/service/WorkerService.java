package com.oes.service;

import com.alibaba.fastjson.JSONObject;
import com.oes.Exceptions.common.RepeatOperationException;
import com.oes.config.Url;
import com.oes.constant.enums.BusinessType;
import com.oes.vo.WorkerVO;
import com.oes.dao.OrganizationWorkersDao;
import com.oes.dto.WorkerDTO;
import com.oes.entity.OrganizationWorker;
import com.oes.model.dto.AuthUserDTO;
import com.oes.model.dto.BaseResultDTO;
import com.oes.model.dto.UserDTO;

import com.oes.model.entity.AuthenticatedUser;
import com.oes.model.entity.Role;
import com.oes.model.entity.User;
import com.oes.util.http.HttpResult;
import com.oes.util.http.HttpStatus;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class WorkerService {

    @Resource
    OrganizationWorkersDao workersDao;


    @Resource
    private RestTemplate restTemplate;

    /**
     * 为机构id为orgId的绑定员工，此时绑定的角色为roleName
     *
     * @return 是否绑定成功
     */
    @Transactional
    public boolean bindWorker(long userId, int roleId) {
        int workerUpdate;
        boolean b;
        OrganizationWorker ifExist = workersDao.queryById(userId, roleId);
        if (null != ifExist) {
            throw new RepeatOperationException("worker.role.repeat", "不能重复绑定角色");
        }
        try {
            List<OrganizationWorker> organizationWorkers = workersDao.queryByUserId(userId);
            OrganizationWorker organizationWorker = organizationWorkers.get(0);
            OrganizationWorker worker = new OrganizationWorker();
            worker.setChecked(2);
            worker.setOrganizationId(organizationWorker.getOrganizationId());
            worker.setRelationship(organizationWorker.getRelationship());
            worker.setRelativeName(organizationWorker.getRelativeName());
            worker.setRelativePhone(organizationWorker.getRelativePhone());
            worker.setStandbyPhone(organizationWorker.getStandbyPhone());
            worker.setRole(roleId);
            worker.setWageCardId(organizationWorker.getWageCardId());
            worker.setUserid(userId);
//            worker.setRoleSubLevel(organizationWorker.getRoleSubLevel());
            workerUpdate = workersDao.insert(worker);                    //插入workers表
            b = restTemplate.getForObject(Url.SERVICE_USERCENTER+"user/api/roleId?userId="+worker.getUserid()+"&roleId="+roleId,boolean.class);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return workerUpdate == 1 && b;
    }




    @Transactional
    public boolean unBindWorker(long orgId, long userId, String roleName) {
        int updateDel;
        boolean b;
        try {

             HttpResult<JSONObject> result = restTemplate.exchange
                    (Url.SERVICE_USERCENTER+"user/api/roleName?userId="+userId+"&roleName="+roleName,HttpMethod.DELETE,null,
                            new ParameterizedTypeReference<HttpResult<JSONObject>>() {
                            }).getBody();
             int roleId = (int) result.getData().get("roleId");
             b = (boolean) result.getData().get("isRemove");
            updateDel = workersDao.deleteById(userId, roleId);//删除worker表

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return updateDel == 1 && b;
    }




    public WorkerVO queryWorkerByUserId(long org_id, long userId, int roleId){

        OrganizationWorker organizationWorker = workersDao.queryById(userId,roleId);
        HttpResult<JSONObject> result = restTemplate.exchange
                (Url.SERVICE_USERCENTER+"/user/auth/api/"+userId, HttpMethod.GET, null,
                        new ParameterizedTypeReference<HttpResult<JSONObject>>() {
                        }).getBody();
        User user = (User) result.getData().get("user");
        AuthenticatedUser auth = (AuthenticatedUser) result.getData().get("auth");
        UserDTO userDTO = new UserDTO();
        AuthUserDTO authUserDTO = new AuthUserDTO();
        WorkerDTO workerDTO = new WorkerDTO();

        BeanUtils.copyProperties(user,userDTO);
        BeanUtils.copyProperties(auth,authUserDTO);
        BeanUtils.copyProperties(workerDTO,organizationWorker);

        WorkerVO workerVO = new WorkerVO();
        workerVO.setBaseInfo(userDTO);
        workerVO.setAuthInfo(authUserDTO);
        workerVO.setWorkerInfo(workerDTO);

        return workerVO;
    }



    public List<OrganizationWorker> queryAllBind(long orgId) {
        return workersDao.queryCheckedByOrgId(orgId);
    }


    public List<WorkerVO> getOrgWorkers(long orgId) {
        List<WorkerVO> workerVOS = new ArrayList<>();
        List<OrganizationWorker> organizationWorkers;
        organizationWorkers = workersDao.queryCheckedByOrgId(orgId);
        convertWorkerToVO(workerVOS, organizationWorkers);
        return workerVOS;
    }


    public HttpResult getOrgWorkerByIdAndRole(long id, String role) {
        List<OrganizationWorker> organizationWorkers = null;
        HttpResult result = new HttpResult();
        try {
            HttpResult<Role> roleResult = restTemplate.exchange(Url.SERVICE_USERCENTER+"/role/name?roleName="+role,HttpMethod.GET,null,
                    new ParameterizedTypeReference<HttpResult<Role>>() {
                    }).getBody();
            Role role1 =roleResult.getData();
            organizationWorkers = workersDao.queryByOrgAndRoleId(id, role1.getRoleid());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (organizationWorkers != null) {
            result.setMsg("查询成功");
            result.setData(organizationWorkers);
        } else {
            result.setMsg("查询失败");
            result.setCode(HttpStatus.SC_BAD_REQUEST);
        }
        return result;
    }

    public BaseResultDTO getOrgWorkerById(long id) {
        List<OrganizationWorker> organizationWorkers = null;
        JSONObject object = new JSONObject();
        try {
            organizationWorkers = workersDao.queryByUserId(id);
            HttpResult<JSONObject> result = restTemplate.exchange
                    (Url.SERVICE_USERCENTER+"/user/auth/api/"+id, HttpMethod.GET, null,
                            new ParameterizedTypeReference<HttpResult<JSONObject>>() {
                            }).getBody();
            User user = (User) result.getData().get("user");
            AuthenticatedUser auth = (AuthenticatedUser) result.getData().get("auth");
            JSONObject userInfo = new JSONObject();
            userInfo.put("mail", user.getMail());
            userInfo.put("gender", user.getGender().getValue());
            userInfo.put("phone", user.getPhone());
            userInfo.put("birth", user.getBirth());
            userInfo.put("account", user.getAccount());
            object.put("baseInfo", userInfo);
            object.put("auth", auth);
            object.put("workerInfo", organizationWorkers);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (organizationWorkers != null && !organizationWorkers.isEmpty()) {
            return new BaseResultDTO(BusinessType.SELECT, true, "查询成功", object);
        } else {
            return new BaseResultDTO(false, "查询失败");
        }
    }


    @Transactional
    public HttpResult addWorker(OrganizationWorker worker) {
        HttpResult result = new HttpResult();
        int insert = 0;
        try {
            //worker.setChecked(1);
            worker.setChecked(0);
            insert = workersDao.insert(worker);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (insert != 0) {
            result.setMsg("信息登记成功");
            result.setData(worker.getUserid());
        } else {
            result.setMsg("信息登记失败");
            result.setCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);
        }
        return result;
    }

    public HttpResult updateWorker(OrganizationWorker organizationWorker) {
        HttpResult result = new HttpResult();
        int update = 0;
        try {
            update = workersDao.update(organizationWorker);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (update != 0) {
            result.setMsg("信息修改成功");
            result.setData(organizationWorker.getUserid());
        } else {
            result.setMsg("信息修改失败");
            result.setCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);
        }
        return result;
    }

    public HttpResult getAllUnchecked(Long orgId) {
        List<WorkerVO> workerVOS = new ArrayList<>();
        List<OrganizationWorker> organizationWorkers;
        organizationWorkers = workersDao.queryUncheckedByOrgId(orgId);
        convertWorkerToVO(workerVOS, organizationWorkers);
        return HttpResult.ok("未审核员工查询成功", workerVOS);
    }

    private void convertWorkerToVO(List<WorkerVO> workerVOS, List<OrganizationWorker> workers) {
        workers.forEach(worker -> {
            long userid = worker.getUserid();
            HttpResult<JSONObject> result = restTemplate.exchange
                    (Url.SERVICE_USERCENTER+"/user/auth/api/"+userid, HttpMethod.GET, null,
                            new ParameterizedTypeReference<HttpResult<JSONObject>>() {
                            }).getBody();
            User user = (User) result.getData().get("user");
            AuthenticatedUser auth = (AuthenticatedUser) result.getData().get("auth");

            WorkerDTO workerDTO = new WorkerDTO();
            UserDTO userDTO = new UserDTO();
            AuthUserDTO authUserDTO = new AuthUserDTO();

            BeanUtils.copyProperties(worker, workerDTO);
            BeanUtils.copyProperties(user, userDTO);
            BeanUtils.copyProperties(auth, authUserDTO);

            WorkerVO workerVO = new WorkerVO(userDTO, authUserDTO, workerDTO);
            workerVOS.add(workerVO);
        });
    }

    public boolean bindTotalAdmin(long userId, long orgId) {
        HttpResult<Role> roleResult = restTemplate.exchange(Url.SERVICE_USERCENTER+"/role/name?roleName=total_admin",HttpMethod.GET,null,
                new ParameterizedTypeReference<HttpResult<Role>>() {
                }).getBody();
        Role role =roleResult.getData();
        OrganizationWorker orgWorker = new OrganizationWorker();
        orgWorker.setUserid(userId);
        orgWorker.setOrganizationId(orgId);
        orgWorker.setChecked(2);
        orgWorker.setRole(role.getRoleid());
        orgWorker.setRoleSubLevel(1);
        int update = workersDao.insert(orgWorker);
        return update == 1;
    }

    public WorkerVO queryWorkerByUserIdAndRoleId(long org_id,long userid,int roleId){

        OrganizationWorker organizationWorker = workersDao.queryById(userid, roleId);
        HttpResult<JSONObject> result = restTemplate.exchange
                (Url.SERVICE_USERCENTER+"/user/auth/api/"+userid, HttpMethod.GET, null,
                        new ParameterizedTypeReference<HttpResult<JSONObject>>() {
                        }).getBody();
        User user = (User) result.getData().get("user");
        AuthenticatedUser auth = (AuthenticatedUser) result.getData().get("auth");

        WorkerDTO workerDTO = new WorkerDTO();
        UserDTO userDTO = new UserDTO();
        AuthUserDTO authUserDTO = new AuthUserDTO();

        workerDTO.setFail_reason(organizationWorker.getFail_reason());
        workerDTO.setOrganizationId(organizationWorker.getOrganizationId());
        workerDTO.setRelationship(organizationWorker.getRelationship());
        workerDTO.setRelativeName(organizationWorker.getRelativeName());
        workerDTO.setRelativePhone(organizationWorker.getRelativePhone());
        workerDTO.setRole(organizationWorker.getRole());
        workerDTO.setRoleSubLevel(organizationWorker.getRoleSubLevel());
        workerDTO.setStandbyPhone(organizationWorker.getStandbyPhone());

        BeanUtils.copyProperties(user, userDTO);
        BeanUtils.copyProperties(auth, authUserDTO);
        //BeanUtils.copyProperties(organizationWorker, workerDTO);

        WorkerVO workerVO = new WorkerVO(userDTO, authUserDTO, workerDTO);
        return workerVO;
    }

}