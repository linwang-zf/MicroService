package com.oes.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * shopping_cart
 * @author 
 */
@Data
@Setter
@Getter
public class ShoppingCart implements Serializable {
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
    private String merInfo;

    /**
     * 商品价格
     */
    private BigDecimal merPrice;

    /**
     * 购物车状态：0-未提交订单，1-已提交订单
     */
    private Byte cartState;

    /**
     * 商品推荐人
     */
    private String recommender;

    private static final long serialVersionUID = 1L;

    public ShoppingCart(Integer userId, Integer merId, String merInfo,
                        BigDecimal merPrice, Byte cartState, String recommender) {
        this.userId = userId;
        this.merId = merId;
        this.merInfo = merInfo;
        this.merPrice = merPrice;
        this.cartState = cartState;
        this.recommender = recommender;
    }
}