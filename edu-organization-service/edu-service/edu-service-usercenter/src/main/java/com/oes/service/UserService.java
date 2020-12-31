package com.oes.service;

import com.alibaba.fastjson.JSONObject;
import com.oes.Exceptions.database.DBOperateException;
import com.oes.dao.AuthenticatedUsersDao;
import com.oes.dao.RolesDao;
import com.oes.dao.UsersDao;
import com.oes.model.dto.BaseResultDTO;
import com.oes.model.entity.AuthenticatedUser;
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

    public HttpResult getUserAuthInfoById(Integer userId){
        User user = usersDao.queryById(userId);
        AuthenticatedUser auth = authenticatedUsersDao.queryById(userId);
        JSONObject object = new JSONObject();
        object.put("user", user);
        object.put("auth", auth);
        return HttpResult.ok("获取成功", object);
    }
}
