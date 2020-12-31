package com.oes.vo;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import com.oes.entity.OrderList;
import com.oes.util.json.JsonUtils;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author zhang chenghuai
 * @Title:
 * @Package
 * @Description:
 * @date 2020/9/1515:13
 */
@JsonIgnoreProperties(value = {"handler"})
@Data
public class OrderItemVO implements Serializable {
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
     * JSON字段描述订单内容
     */
    private JSONObject orderItem;

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
     * 商品折扣
     */
    private BigDecimal discount;

    /**
     * 付款人
     */
    private String payer;

    /**
     * 付款人手机号
     */
    private String payerPhone;

    /**
     * 推荐订单的人
     */
    private String recommender;



    public OrderItemVO(OrderList orderList) {
        this.orderId = orderList.getOrderId();
        this.merId = orderList.getMerId();
        this.userId = orderList.getUserId();
        this.orderTime = orderList.getOrderTime();
        this.orderItem = JsonUtils.toJsonObject(orderList.getOrderItem());
        this.orderState = orderList.getOrderState();
        this.payType = orderList.getPayType();
        this.payTime = orderList.getPayTime();
        this.orderPrice = orderList.getOrderPrice();
        if (orderList.getDiscount() != null) {
            this.discount = orderList.getDiscount();
        } else {
            this.discount = new BigDecimal(0);
        }
        if (orderList.getPayer() != null) {
            this.payer = orderList.getPayer();
        }
        if (orderList.getPayerPhone() != null) {
            this.payerPhone = orderList.getPayerPhone();
        }
        this.recommender = orderList.getRecommender();
    }
}
