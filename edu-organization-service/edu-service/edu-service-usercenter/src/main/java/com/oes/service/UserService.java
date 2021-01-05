package com.oes.service;

import com.alibaba.fastjson.JSONObject;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.oes.Exceptions.database.DBOperateException;
import com.oes.dao.AuthenticatedUsersDao;
import com.oes.dao.RolesDao;
import com.oes.dao.UsersDao;
import com.oes.model.dto.BaseResultDTO;
import com.oes.model.entity.AuthenticatedUser;
import com.oes.model.entity.Role;
import com.oes.model.entity.User;
import com.oes.model.example.UserExample;
import com.oes.model.query.user.UserRegisterQuery;
import com.oes.model.query.user.UserUpdateQuery;
import com.oes.model.vo.user.UserVo;
import com.oes.util.http.HttpResult;
import com.oes.util.http.HttpStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Slf4j
public class UserService {
    @Value("${server.base-url}")
    private String baseUrl;
    @Resource
    private UsersDao usersDao;
    @Resource
    private RolesDao rolesDao;
    @Resource
    private AuthenticatedUsersDao authenticatedUsersDao;

    /** 用户注册*/
    @Transactional
    public BaseResultDTO register(UserRegisterQuery query) {
        BaseResultDTO checkInfo = checkUserInfo(query);
        if (!checkInfo.isSuccess()) {
            return checkInfo;
        }

        User user = new User();
        BeanUtils.copyProperties(query, user);
        try {
            int row = usersDao.insert(user);
        } catch(Exception e) {
            log.error("用户注册信息插入数据库失败，信息：{}", user);
            throw new DBOperateException("用户注册失败");
        }

        return new BaseResultDTO(true, "注册成功");
    }

    /**修改用户信息*/
    @Transactional
    public BaseResultDTO update(UserUpdateQuery query) {
        BaseResultDTO check = checkUpdateUserInfo(query);

        if (!check.isSuccess()) {
            return check;
        }

        User user = new User();
        BeanUtils.copyProperties(query, user);
        try {
            int row = usersDao.update(user);
        } catch(Exception e) {
            log.error("用户修改信息插入数据库失败，信息：{}", user);
            throw new DBOperateException("修改信息失败");
        }

        return new BaseResultDTO(true, "修改成功");
    }

    /**根据用户id查询用户信息*/
    public UserVo queryById(long id) {
        User user = usersDao.queryById(id);
        UserVo userVo = new UserVo();
        BeanUtils.copyProperties(user, userVo);
        log.info("--userVo:"+userVo.toString());
        if (user.getProfilePhoto() != 0)
            userVo.setProfilePhotoUrl(baseUrl + user.getProfilePhoto());
        return userVo;
    }

    /**根据用户删除用户*/
    public HttpResult deleteUser(long id) {
        HttpResult result = new HttpResult();
        int deleteById = 0;
        try {
            deleteById = usersDao.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (deleteById == 1) {
            result.setMsg("删除用户成功");
            result.setData(true);
        } else {
            result.setCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);
            result.setData(false);
            result.setMsg("删除用户失败");
        }
        return result;
    }

    /**查询用户是否已认证*/
    public HttpResult verifyAuthentication(long userid) {
        User user = usersDao.queryById(userid);
        HttpResult result = new HttpResult();
        result.setData(user != null && user.isAuthenticated());
        if (user == null) result.setMsg("用户不存在");
        else result.setMsg(user.isAuthenticated() ? "用户已认证" : "用户未认证");
        return result;
    }

    /*查询用户名是否已被注册*/
    public boolean checkedAccount(String account) {
        User user = usersDao.queryByAccount(account);
        return user == null;
    }

    /**手机是否已注册
     * TODO
     * */
    public BaseResultDTO checkedPhone(String phone) {
        List<User> users = usersDao.checkPhone(phone);
        if(users.size() == 0) {
            return new BaseResultDTO(true, "手机号可用");
        } else {
            return new BaseResultDTO(false, "手机号已被注册");
        }
    }
    /***********************************************************/
    /** 检查注册用户信息的合法性*/
    private BaseResultDTO checkUserInfo(UserRegisterQuery userInfo) {
        String phone = userInfo.getPhone();
        String mail = userInfo.getMail();
        String account = userInfo.getAccount();
        if (account == null) {
            return new BaseResultDTO(false, "账号不能为空");
        }
        if (mail == null) {
            return new BaseResultDTO(false, "邮箱不能为空");
        }
        if (phone == null) {
            return new BaseResultDTO(false, "手机号码不能为空");
        }
        UserExample example = new UserExample();

        UserExample.Criteria accountCriteria = example.createCriteria();
        accountCriteria.andAccountEqualTo(account);
        if (usersDao.countByExample(example) > 0) {
            return new BaseResultDTO(false, "该账号已经被使用");
        }

        example.clear();
        UserExample.Criteria phoneCriteria = example.createCriteria();
        phoneCriteria.andPhoneEqualTo(phone);
        if (usersDao.countByExample(example) > 0) {
            return new BaseResultDTO(false, "该手机号已经被使用");
        }
        example.clear();
        UserExample.Criteria mailCriteria = example.createCriteria();
        mailCriteria.andMailEqualTo(mail);
        if (usersDao.countByExample(example) > 0) {
            return new BaseResultDTO(false, "该邮箱已经被使用");
        }

        return new BaseResultDTO(true);
    }

    /** 检查用户信息的合法性*/
    private BaseResultDTO checkUpdateUserInfo(UserUpdateQuery userInfo) {
        String phone = userInfo.getPhone();
        String mail = userInfo.getMail();
        String account = userInfo.getAccount();
        System.out.println(userInfo.getUserid());
        User user = usersDao.queryById(userInfo.getUserid());
        if (user != null) {
            if (user.getPhone().equals(phone)) {
                phone = null;
                userInfo.setPhone(null);
            }
            if (user.getMail().equals(mail)) {
                mail = null;
                userInfo.setMail(null);
            }
            if (user.getAccount().equals(account)) {
                account = null;
                userInfo.setAccount(null);
            }
        } else {
            return new BaseResultDTO(false, "没有查询到该用户信息");
        }
        UserExample example = new UserExample();

        if (account != null && account != "") {
            UserExample.Criteria accountCriteria = example.createCriteria();
            accountCriteria.andAccountEqualTo(account);
            accountCriteria.andUseridNotEqualTo((int) userInfo.getUserid());
            if (usersDao.countByExample(example) > 0) {
                return new BaseResultDTO(false, "该账号已经被使用");
            }
        }

        if (phone != null && phone != "") {
            example.clear();
            UserExample.Criteria phoneCriteria = example.createCriteria();
            phoneCriteria.andPhoneEqualTo(phone);
            phoneCriteria.andUseridNotEqualTo((int) userInfo.getUserid());
            if (usersDao.countByExample(example) > 0) {
                return new BaseResultDTO(false, "该手机号已经被使用");
            }
        }

        if (mail != null && mail != "") {
            example.clear();
            UserExample.Criteria mailCriteria = example.createCriteria();
            mailCriteria.andMailEqualTo(mail);
            mailCriteria.andUseridNotEqualTo((int) userInfo.getUserid());
            if (usersDao.countByExample(example) > 0) {
                return new BaseResultDTO(false, "该邮箱已经被使用");
            }
        }

        return new BaseResultDTO(true);
    }

    /*******************************************对外暴露*********************/
    public HttpResult insert(User user, String roleNmae){
        user.setDefaultRole(rolesDao.queryByName(roleNmae).getRoleid());
        try {
            int row = usersDao.insert(user);
        } catch(Exception e) {
            log.error("用户注册信息插入数据库失败，信息：{}", user);
            throw new DBOperateException("用户注册失败");
        }
        return HttpResult.ok("注册成功",user);
    }
    //++++++++服务熔断
    @HystrixCommand(fallbackMethod = "userAuthInfoError_fallback", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled", value = "true"), //是否开启断路器、
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"), //请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"), //时间窗口期、
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60") //失败率达到多少后跳闸
    })
    public HttpResult getUserAuthInfoById(Integer userId){
        //测试服务熔断
/*        if(userId < 0){
            throw new RuntimeException("userid 《 0");
        }*/
        User user = usersDao.queryById(userId);
        AuthenticatedUser auth = authenticatedUsersDao.queryById(userId);
        JSONObject object = new JSONObject();
        object.put("user", user);
        object.put("auth", auth);
        return HttpResult.ok("获取成功", object);
    }
    //allback method wasn't found 这是因为指定的 备用方法 和 原方法 的参数个数，类型不同造成的；
    public HttpResult userAuthInfoError_fallback(Integer userId){
        return HttpResult.error(HttpStatus.SC_REQUEST_TIMEOUT,"服务超时，请稍后再试");
    }
    //++++++++++++++++++++

    public boolean addRoles(Integer userId, String roleName) {
        User user = usersDao.queryById(userId);
        int roleId = rolesDao.queryByName(roleName).getRoleid();
        if (user.getRole1() == roleId || user.getRole2() == roleId || user.getRole3() == roleId) return true;
        if (user.getDefaultRole() == 0 || user.getDefaultRole() == 1) {
            user.setDefaultRole(roleId);
        } else if (user.getRole1() == 0 || user.getRole1() == 1) {
            user.setRole1(roleId);
        } else if (user.getRole2() == 0 || user.getRole2() == 1) {
            user.setRole2(roleId);
        } else if (user.getRole3() == 0 || user.getRole3() == 1) {
            user.setRole3(roleId);
        }
        return usersDao.update(user) == 1;
    }

    public boolean addRole(Integer userId, int roleId) {
        User user = usersDao.queryById(userId);
        if (user.getRole1() == roleId || user.getRole2() == roleId || user.getRole3() == roleId) return true;
        if (user.getDefaultRole() == 0 || user.getDefaultRole() == 1) {
            user.setDefaultRole(roleId);
        } else if (user.getRole1() == 0 || user.getRole1() == 1) {
            user.setRole1(roleId);
        } else if (user.getRole2() == 0 || user.getRole2() == 1) {
            user.setRole2(roleId);
        } else if (user.getRole3() == 0 || user.getRole3() == 1) {
            user.setRole3(roleId);
        }
        return usersDao.update(user) == 1;
    }

    public HttpResult removeRole(  Integer userId, String roleName) {
        int roleId = rolesDao.queryByName(roleName).getRoleid();
        User user = usersDao.queryById(userId);
        if (user.getDefaultRole() == roleId) {
            user.setDefaultRole(0);
        } else if (user.getRole1() == roleId) {
            user.setRole1(1);
        } else if (user.getRole2() == roleId) {
            user.setRole2(1);
        } else if (user.getRole3() == roleId) {
            user.setRole3(1);
        }
        JSONObject object = new JSONObject();
        object.put("isRemove",usersDao.update(user) == 1);
        object.put("roleId",roleId);
        if(usersDao.update(user) == 1){
            return HttpResult.ok("成功",object);
        }else {
            return HttpResult.error("失败");
        }
    }
}
