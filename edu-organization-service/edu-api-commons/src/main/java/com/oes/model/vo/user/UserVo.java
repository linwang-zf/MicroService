package com.oes.model.vo.user;

import com.oes.model.entity.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author : JQK
 * @date : 2020-07-13 19:30
 * @description :
 */
@Setter
@Getter
@ToString
public class UserVo extends User {

    private String profilePhotoUrl;

}
