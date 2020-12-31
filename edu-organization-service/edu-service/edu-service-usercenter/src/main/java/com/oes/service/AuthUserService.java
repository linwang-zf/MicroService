package com.oes.service;

import com.alibaba.fastjson.JSONObject;
import com.oes.constant.enums.BusinessType;
import com.oes.dao.AuthenticatedUsersDao;
import com.oes.dao.PicturesDao;
import com.oes.dao.UsersDao;
import com.oes.model.dto.BaseResultDTO;
import com.oes.model.entity.AuthenticatedUser;
import com.oes.model.entity.Picture;
import com.oes.model.entity.User;
import com.oes.util.http.HttpResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class AuthUserService {
    @Resource
    private AuthenticatedUsersDao authUserDao;
    @Resource
    private PicturesDao picturesDao;
    @Resource
    private  UsersDao usersDao;


    /** 查询认证用户 */
    public AuthenticatedUser queryById(long userid) {
        AuthenticatedUser user;
        user = authUserDao.queryById(userid);
        return user;
    }

    /**
     * 增加认证用户，需要同时更新用户表和认证用户表
     * @param user
     * @return
     */
    @Transactional
    public BaseResultDTO addAuthUser(AuthenticatedUser user) {

        Picture picture = picturesDao.queryById(user.getIdcardPhoto());
        if (Objects.isNull(picture)) {
            log.error("没有查询到id为{}的身份证照片", user.getIdcardPhoto());
            throw new IllegalArgumentException(String.format("没有查询到id为 %d 的身份证照片", user.getIdcardPhoto()));
        }

        /*String idValidResult = IdentityCardValidDistinguish.distinguish(user.getName(), user.getIdcard());
        JSONObject validResult = JSONObject.parseObject(idValidResult).getJSONArray("infoList").getJSONObject(0).
                getJSONArray("veritem").getJSONObject(0);

        String validStatus = validResult.getString("content");*/

        JSONObject validResult = new JSONObject();
        validResult.put("desc", "测试使用");
        String validStatus = "00";

        if ("00".equals(validStatus)) {
            authUserDao.insert(user);

            User updateUser = new User();
            updateUser.setUserid(user.getUserid());
            updateUser.setAuthenticated(true);
            usersDao.update(updateUser);
            log.info("新增加认证用户，id为 {}, name为 {}", user.getUserid(), user.getName());
            return new BaseResultDTO(BusinessType.INSERT, true, "认证成功");
        } else {
            String msg = validResult.getString("desc");
            return new BaseResultDTO(false, msg);
        }
    }

    /**
     * 更新认证用户信息
     * @param user
     * @return
     */
    public int updateUser(AuthenticatedUser user) {
        return authUserDao.update(user);
    }

    /**
     * 增加身份证照片
     * @param picture
     * @return
     */
    public BaseResultDTO addIdCardPhoto(Picture picture) {
        /*String photoResult = IdentityCardOCRDistinguish.distinguish(picture.getData());
        int status = JSONObject.parseObject(photoResult).getJSONObject("message").getInteger("status");
        if (status == 2) {
            picturesDao.insert(picture);
            JSONArray items = JSONObject.parseObject(photoResult).getJSONArray("cardsinfo").getJSONObject(0).getJSONArray("items");
            JSONObject object = new JSONObject();
            object.put("photoId", picture.getPictureId());
            for (int i = 0; i < items.size(); i++) {
                if (items.getJSONObject(i).getString("desc").equals("姓名"))
                    object.put("realName", items.getJSONObject(i).getString("content"));
                if (items.getJSONObject(i).getString("desc").equals("公民身份号码"))
                    object.put("cardId", items.getJSONObject(i).getString("content"));
            }
            return new BaseResultDTO(BusinessType.INSERT, true,"识别成功", object);
        } else {
            return new BaseResultDTO(false, "识别失败");
        }*/
        // TODO 暂时关闭身份证验证功能
        JSONObject object = new JSONObject();
        object.put("photoId", 265);
        object.put("realName", "测试名称");
        object.put("cardId", "412586998563001258");
        return new BaseResultDTO(BusinessType.INSERT, true,"识别成功", object);
    }


    /******************************对外API************/
    public List<AuthenticatedUser>  getAllAuth(){
        return  authUserDao.queryAll();
    }

    public List<AuthenticatedUser> queryNamesById(List<Integer> userIds){
        return authUserDao.queryNamesByIds(userIds);
    }
}
