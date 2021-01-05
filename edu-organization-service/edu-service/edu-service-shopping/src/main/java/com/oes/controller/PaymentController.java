package com.oes.controller;


import com.oes.query.DiscountQuery;
import com.oes.query.PaymentQuery;
import com.oes.service.OrderListService;
import com.oes.util.http.HttpResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dxh924
 * @Title:
 * @Package
 * @Description:
 * @date 2020/10/816:42
 */
@RestController
@Api(tags = {"支付管理"})
public class PaymentController {
    @Autowired
    private OrderListService orderListService;



    @ApiOperation(value = "支付请求，完成支付后自动添加选课记录")
    @PutMapping("/payment/")
    public HttpResult startPayment(@RequestBody PaymentQuery paymentQuery) {
        HttpResult httpResult = new HttpResult();
        if (orderListService.payment(paymentQuery)) {
            return httpResult.ok("支付成功，请在课程表里面查看课程信息");
        }
        return httpResult.error("支付失败");
    }


    @ApiOperation(value = "设置商品折扣")
    @PutMapping("/payment/discount")
    public HttpResult startPaymentByCash(@RequestBody DiscountQuery discountQuery) {
        HttpResult httpResult = new HttpResult();
        if (orderListService.setDiscount(discountQuery)) {
            return httpResult.ok("设置商品折扣成功");
        }
        return httpResult.error("设置商品折扣失败");
    }

}
