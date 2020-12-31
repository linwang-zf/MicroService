package com.oes.entity;

import com.oes.model.entity.User;

import com.oes.util.json.JsonUtils;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * order_list
 * @author 
 */
@Data
@Setter
@Getter
@NoArgsConstructor
public class OrderList implements Serializable {
    /**
     * 订单号
     */
    private Integer orderId;

    /**
     * 商品id，关联商品表中的mer_id
     */
    private Integer merId;

    /**
     * 主键2，用户id
     */
    private Integer userId;

    /**
     * 主键3，订单生成时间
     */
    private Date orderTime;

    /**
     * 描述订单内容
     */
    private String orderItem;

    /**
     * 订单状态：0-已取消，1-未支付，2-已支付
     */
    private Byte orderState;

    /**
     * 支付方式：支付宝、微信……
     */
    private String payType;

    /**
     * 支付时间
     */
    private Date payTime;

    /**
     * 商品单价
     */
    private BigDecimal orderPrice;

    /**
     * 订单总额
     */
    private BigDecimal total;

    /**
     * 操作员
     */
    private String operator;

    /**
     * 商品折扣
     */
    private BigDecimal discount;

    /**
     * 实际交易额
     */
    private BigDecimal transaction;

    /**
     * 付款人
     */
    private String payer;

    /**
     * 付款人手机号
     */
    private String payerPhone;


    /**
     * 推送订单的人
     */
    private String recommender;


    private static final long serialVersionUID = 1L;

    public OrderList(Integer orderId, Integer userId, CourseMer courseMer, Date orderTime, BigDecimal totalPrice, User user, String recommender) {
        this.orderId = orderId;
        this.merId = courseMer.getMerId();
        this.userId = userId;
        this.orderTime = orderTime;
        this.orderItem = JsonUtils.toJsonString(courseMer);
        // order_state = 0-已取消，1-待支付，2-已支付
        this.orderState = 1;
        this.payType = null;
        this.payTime = null;
        this.orderPrice = courseMer.getCoursePrice();
        this.total = totalPrice;
        this.discount = new BigDecimal(0);
        this.payer = user.getAccount();
        this.payerPhone = user.getPhone();
        this.recommender = recommender;
    }

    public OrderList(Integer orderId, Integer userId, CourseMer courseMer, Date orderTime, BigDecimal totalPrice, String operator, BigDecimal discount) {
        this.orderId = orderId;
        this.merId = courseMer.getMerId();
        this.userId = userId;
        this.orderTime = orderTime;
        this.orderItem = JsonUtils.toJsonString(courseMer);
        // order_state = 0-已取消，1-待支付，2-已支付
        this.orderState = 1;
        this.payType = null;
        this.payTime = null;
        this.orderPrice = courseMer.getCoursePrice();
        this.total = totalPrice;
        this.operator = operator;
        this.discount = discount;
    }

    public OrderList(Integer orderId, Integer merId, Integer userId, Date orderTime, String orderItem,
                     Byte orderState, String payType, Date payTime, BigDecimal orderPrice, BigDecimal total, String operator, BigDecimal discount) {
        this.orderId = orderId;
        this.merId = merId;
        this.userId = userId;
        this.orderTime = orderTime;
        this.orderItem = orderItem;
        this.orderState = orderState;
        this.payType = payType;
        this.payTime = payTime;
        this.orderPrice = orderPrice;
        this.total = total;
        this.operator = operator;
        this.discount = discount;
    }
}