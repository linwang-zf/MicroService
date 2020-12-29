package com.oes.model.query.user;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author : JQK
 * @date : 2020-07-25 21:40
 * @description :
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(parent = UserRegisterQuery.class)
public class UserUpdateQuery extends UserRegisterQuery {

    private long userid;

}
