package com.oes.query;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author dxh924
 * @Title:
 * @Package
 * @Description: 订单数据中的备注都可空，但不能不传
 * @date 11/5/20208:04 PM
 */
@Data
@NoArgsConstructor
public class OrderResult {
    /**
     * 订单号
     */
    //private Integer order_id;
    private String order_id;
    /**
     * 支付方式，填"alipay"
     */
    private String pay_mode;
    /**
     * 支付通道，填"0000"
     */
    private String transtype;
    /**
     * 总金额
     */
    // private BigDecimal total_money;
    private String total_money;
    /**
     * 回调地址
     */
    private String notifyurl;

    private String fround_notifyurl;
    /**
     * 订单ip地址
     */
    private String order_ip;
    /**
     * 系统id
     */
    private String application_id;
    private String remark1;
    private String remark2;
    private String remark3;
    private String remark4;
    private String remark5;
    private List<BankDetail> bank_detail;
}
