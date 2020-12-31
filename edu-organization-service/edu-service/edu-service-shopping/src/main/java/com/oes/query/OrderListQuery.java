package com.oes.query;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * @author dxh924
 * @Title:
 * @Package
 * @Description:
 * @date 2020/10/1415:29
 */
@Data
@NoArgsConstructor
public class OrderListQuery {
    private Integer orderId;
    private Integer userId;
    private Integer orgId;
    private Byte orderState;
    private String payType;
    private String payer;
    private String payerPhone;
    @NotNull(message = "当前页码不为空")
    @Min(value = 0, message = "页码不小于0")
    private Integer pageNum;
    @NotNull(message = "页面大小不为空")
    @Min(value = 0, message = "页面大小不小于0")
    private Integer pageSize;
}
