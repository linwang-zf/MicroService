package com.oes.query;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * @author dxh924
 * @Title:
 * @Package
 * @Description:
 * @date 2020/10/1415:14
 */
@Data
@NoArgsConstructor
public class DiscountQuery {
    private Integer orderId;
    private Integer merId;
    private String operator;
    private BigDecimal discount;
}
