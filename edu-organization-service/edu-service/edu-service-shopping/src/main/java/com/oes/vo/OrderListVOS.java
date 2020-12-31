package com.oes.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author dxh924
 * @Title:
 * @Package
 * @Description:
 * @date 2020/10/1511:38
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderListVOS {
    private Integer totalSize;
    private List<OrderListVO> orderListVOS;
}
