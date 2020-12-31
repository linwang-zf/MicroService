package com.oes.vo;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.oes.entity.ShoppingCart;
import com.oes.util.json.JsonUtils;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author zhang chenghuai
 * @Title:
 * @Package
 * @Description:
 * @date 2020/9/1717:32
 */
@JsonIgnoreProperties(value = {"handler"})
@Data
public class ShoppingCartVO implements Serializable {
    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 关联商品表中的mer_id
     */
    private Integer merId;

    /**
     * JSON字段用于描述商品信息
     */
    private JSONObject merInfo;

    /**
     * 商品价格
     */
    private BigDecimal merPrice;

    /**
     * 商品推荐人
     */
    private String recommender;

    /**
     * 购物车状态：0-未提交订单，1-已提交订单
     */
    private Byte cartState;

    public ShoppingCartVO(ShoppingCart shoppingCart) {
        this.userId = shoppingCart.getUserId();
        this.merId = shoppingCart.getMerId();
        this.merInfo = JsonUtils.toJsonObject(shoppingCart.getMerInfo());
        this.merPrice = shoppingCart.getMerPrice();
        this.cartState = shoppingCart.getCartState();
        this.recommender = shoppingCart.getRecommender();
    }
}
