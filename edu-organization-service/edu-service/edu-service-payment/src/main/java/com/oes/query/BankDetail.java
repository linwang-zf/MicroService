package com.oes.query;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author dxh924
 * @Title:
 * @Package
 * @Description:
 * @date 11/5/20208:07 PM
 */
@Data
@NoArgsConstructor
public class BankDetail {
    /**
     * 企业银行账户号码
     */
    private String bank_number;
    /**
     * 企业银行账户名称
     */
    private String bank_number_name;
    /**
     * 订单详情
     */
    private List<OrderDetail> order_detail;
}
