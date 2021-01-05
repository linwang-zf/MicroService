package com.oes.query;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author dxh924
 * @Title:
 * @Package
 * @Description:
 * @date 2020/10/1414:54
 */
@Data
@NoArgsConstructor
public class PaymentQuery {
    private Integer orderId;
    private String operator;
    private String payType;
    private String payer;
    private String payerPhone;
}
