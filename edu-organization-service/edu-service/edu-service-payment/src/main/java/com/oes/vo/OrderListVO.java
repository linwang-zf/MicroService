package com.oes.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author zhang chenghuai
 * @Title:
 * @Package
 * @Description:
 * @date 2020/9/189:37
 */
@JsonIgnoreProperties(value = {"handler"})
@Data
public class OrderListVO implements Serializable {
    /**
     * 订单号
     */
    private Integer orderId;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 订单生成时间
     */
    private Date orderTime;

    /**
     * 订单内容（商品列表）
     */
    private List<OrderItemVO> orderItemVOList;

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
     * 订单总金额
     */
    private BigDecimal total;

    /**
     * 实际交易额
     */
    private BigDecimal transaction;

    /**
     * 总折扣
     */
    private BigDecimal totalDiscount;

    /**
     * 付款人
     */
    private String payer;

    /**
     * 付款人手机号
     */
    private String payerPhone;

    /**
     *  推荐订单的人
     */
    private String recommender;


    public OrderListVO(List<OrderItemVO> orderItemVOs) {
        OrderItemVO oiv = orderItemVOs.get(0);
        this.orderId = oiv.getOrderId();
        this.userId = oiv.getUserId();
        this.orderTime = oiv.getOrderTime();
        this.orderItemVOList = orderItemVOs;
        this.orderState = oiv.getOrderState();
        this.payType = oiv.getPayType();
        this.payTime = oiv.getPayTime();
        this.total = new BigDecimal(0);
        this.transaction = new BigDecimal(0);
        this.totalDiscount = new BigDecimal(0);
        for (OrderItemVO itemVO : orderItemVOs) { // 计算订单总额和总折扣
            this.total = this.total.add(itemVO.getOrderPrice());
            this.totalDiscount = this.totalDiscount.add(itemVO.getDiscount());
        }
        // 计算实际交易额transaction
        this.transaction = this.total.subtract(this.totalDiscount);
        if (oiv.getPayer() != null) {
            this.payer = oiv.getPayer();
        }
        if (oiv.getPayerPhone() != null) {
            this.payerPhone = oiv.getPayerPhone();
        }
        this.recommender = oiv.getRecommender();
    }
}