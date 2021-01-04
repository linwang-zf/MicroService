package com.oes.controller;

import com.alibaba.fastjson.JSONObject;


import com.oes.query.OrderPostData;
import com.oes.query.PaymentAddr;
import com.oes.service.TransactionService;
import com.oes.util.EncryptionUtils;
import com.oes.util.http.HttpResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author dxh924
 * @Title:
 * @Package
 * @Description:
 * @date 11/5/20207:47 PM
 */
@RestController
@Api(tags = {"对接订单支付系统(交易管理)"})
public class TransactionController {


    @Autowired
    private TransactionService tranService;

//    @ApiOperation(value = "根据订单id生成加密订单数据")
//    @GetMapping("/transaction/encrypt/{orderId}")
//    public HttpResult encryptOrder (@PathVariable Integer orderId) { // 258348316
//       return HttpResult.ok("成功生成订单加密数据", tranService.getPostData(orderId));
//    }
//
//    @ApiOperation(value = "根据订单id生成订单数据")
//    @GetMapping("/transaction/order/{orderId}")
//    public HttpResult getOrder (@PathVariable Integer orderId) { // 258348316
//        return HttpResult.ok("成功生成订单数据", tranService.getOrderResult(orderId));
//    }
//
//    @ApiOperation(value = "获取加密支付地址")
//    @PostMapping("/transaction/payAddr/")
//    public HttpResult getEncryptPayAddr (@RequestBody OrderPostData orderPostData) { // 258348316
//        return HttpResult.ok("成功获取加密支付地址", tranService.doPost(orderPostData));
//    }

    @ApiOperation(value = "根据id获取加密支付地址")
    @GetMapping("/transaction/payAddr/{orderId}")
    public HttpResult getEncryptPayAddr (@PathVariable Integer orderId) { // 258348316
        OrderPostData orderPostData = tranService.getPostData(orderId);
        String encryptStr = tranService.doPost(orderPostData);
        System.out.println(encryptStr);
        String payAddr = EncryptionUtils.RSADecrypt(encryptStr);
        PaymentAddr paymentAddr = JSONObject.parseObject(payAddr, PaymentAddr.class);
        System.out.println("支付地址：" +  paymentAddr.getInfo());
        if (paymentAddr.getInfo().equals("不能添加同一订单")) {
            return HttpResult.error("请勿重复提交订单");
        }
        // 使用前台公钥对支付地址进行加密并替换特殊字符
        payAddr = tranService.encryptPayAddr(paymentAddr.getInfo());
        return HttpResult.ok("成功获取加密支付地址", payAddr);
    }



//    @ApiOperation(value = "解密密文")
//    @GetMapping("/transaction/decrypt/{encryptStr}")
//    public HttpResult decrypt(@PathVariable String encryptStr) {
//        String str = EncryptionUtils.replaceAfterReceive(encryptStr);
//        return HttpResult.ok("解密密文", EncryptionUtils.RSADecrypt(str));
//    }
//
//
//    @ApiOperation(value = "根据订单json生成加密订单数据")
//    @PostMapping("/transaction/")
//    public HttpResult encypt (@RequestBody OrderResult orderResult) { // 258348316
//        return HttpResult.ok("成功生成订单加密数据", tranService.getPostData(tranService.RSA(orderResult)));
//    }
//
//    @ApiOperation(value = "加密支付地址")
//    @PostMapping("/transaction/encrypt/payAddr/")
//    public HttpResult encryptPayAddr(@RequestBody PaymentAddr paymentAddr) {
//        return HttpResult.ok("加密后的支付地址", tranService.encryptPayAddr(paymentAddr.getInfo()));
//    }

    @ApiOperation(value = "接收回调订单信息")
    @PostMapping("/transaction/notifyUrl/")
    public void encryptPayAddr(@RequestParam("result") String result, @RequestParam("sign") String sign) {
        System.out.println("result = " + result);
        System.out.println("sign = " + sign);
        System.out.println("after decrypt, result = " + EncryptionUtils.RSADecrypt(result));
    }
}
