package com.oes.controller;


import com.oes.query.OrderListQuery;
import com.oes.service.OrderListService;
import com.oes.util.http.HttpResult;
import com.oes.vo.OrderItemVO;
import com.oes.vo.OrderListVOS;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zhang chenghuai
 * @Title:
 * @Package
 * @Description:
 * @date 2020/9/1321:22
 */
@RestController
@Api(tags = {"订单"})
public class OrderListController {
    @Autowired
    private OrderListService orderListService;

    @ApiOperation(value = "查询订单明细")
    @PostMapping("/order/")
    public HttpResult getOrderList(@RequestBody OrderListQuery orderListQuery) {
        HttpResult httpResult = new HttpResult();
        List<OrderItemVO> orderItemVOS = orderListService.generateOrderItemVO(orderListQuery);
        if (orderItemVOS.size() > 0) {
            return httpResult.ok("获取订单明细成功", orderItemVOS);
        }
        return httpResult.error("暂无订单");
    }

    @ApiOperation(value = "查询订单")
    @PostMapping("/order/vo")
    public HttpResult getOrderListVO(@RequestBody OrderListQuery orderListQuery) {
        HttpResult httpResult = new HttpResult();
        OrderListVOS orderListVOS = orderListService.generateOrderListVO(orderListQuery);
        if (null != orderListVOS) {
            return httpResult.ok("获取订单成功", orderListVOS);
        }
        return httpResult.error("暂无订单");
    }



    @ApiOperation(value = "取消订单")
    @PutMapping("/order/{orderId}")
    public HttpResult cancelOrderList(@PathVariable Integer orderId) {
        HttpResult httpResult = new HttpResult();
        if (orderListService.cancelOrderList(orderId) > 0) {
            return httpResult.ok("成功取消订单");
        }
        return httpResult.error("订单不存在或已被取消");
    }
}
