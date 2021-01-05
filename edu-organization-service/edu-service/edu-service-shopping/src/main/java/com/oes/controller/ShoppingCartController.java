package com.oes.controller;

import com.alibaba.fastjson.JSONObject;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.oes.model.dto.BaseResultDTO;
import com.oes.service.CourseMerService;
import com.oes.service.ShoppingCartService;
import com.oes.util.http.HttpResult;
import com.oes.util.http.HttpStatus;
import com.oes.vo.ShoppingCartVO;
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
 * @date 2020/9/1619:45
 */
@RestController
@DefaultProperties(defaultFallback = "Global_FallbackMethod")
@Api(tags = {"购物车"})
public class ShoppingCartController {
    @Autowired
    private ShoppingCartService shoppingCartService;
    @Autowired
    private CourseMerService courseMerService;


    @ApiOperation(value = "根据用户id查询所有商品列表")
    @GetMapping("/shoppingCart/{userId}")
    public HttpResult getMer(@PathVariable Integer userId) {
        HttpResult httpResult = new HttpResult();
        List<ShoppingCartVO> shoppingCartVOs = shoppingCartService.findAllByUserId(userId);
        return httpResult.ok("获取所有商品列表成功", shoppingCartVOs);
    }

    @ApiOperation(value = "根据用户id查询未选中的商品列表")
    @GetMapping("/shoppingCart/{userId}/unSelected")
    public HttpResult getSelectedMer(@PathVariable Integer userId) {
        HttpResult httpResult = new HttpResult();
        List<ShoppingCartVO> shoppingCartVOs = shoppingCartService.findByUserId(userId, 0);
        return httpResult.ok("获取未选中商品列表成功", shoppingCartVOs);
    }

    @ApiOperation(value = "根据用户id查询已选中的商品列表")
    @GetMapping("/shoppingCart/{userId}/selected")
    public HttpResult getUnSelectedMer(@PathVariable Integer userId) {
        HttpResult httpResult = new HttpResult();
        List<ShoppingCartVO> shoppingCartVOs = shoppingCartService.findByUserId(userId, 1);
        return httpResult.ok("获取已选中商品列表成功", shoppingCartVOs);
    }

    @ApiOperation(value = "全选商品")
    @PutMapping("/shoppingCart/{userId}/selectAll")
    public HttpResult selectAll(@PathVariable Integer userId) {
        HttpResult httpResult = new HttpResult();
        if (shoppingCartService.selectAll(userId) > 0) {
            return httpResult.ok("全选商品成功");
        }
        return httpResult.error("暂无商品可选");
    }

    @ApiOperation(value = "取消全选商品")
    @PutMapping("/shoppingCart/{userId}/unSelectAll")
    public HttpResult unSelectAll(@PathVariable Integer userId) {
        HttpResult httpResult = new HttpResult();
        if (shoppingCartService.unSelectAll(userId) > 0) {
            return httpResult.ok("取消全选商品成功");
        }
        return httpResult.error("暂无商品可选");
    }

    @ApiOperation(value = "选中指定商品")
    @PutMapping("/shoppingCart/{userId}/{merId}/select")
    public HttpResult selectIt(@PathVariable Integer userId, @PathVariable Integer merId) {
        HttpResult httpResult = new HttpResult();
        if (shoppingCartService.selectIt(userId, merId) > 0) {
            return httpResult.ok("成功选中商品");
        }
        return httpResult.error("暂无商品可选");
    }

    @ApiOperation(value = "取消选中指定商品")
    @PutMapping("/shoppingCart/{userId}/{merId}/unSelect")
    public HttpResult unSelectIt(@PathVariable Integer userId, @PathVariable Integer merId) {
        HttpResult httpResult = new HttpResult();
        if (shoppingCartService.unSelectIt(userId, merId) > 0) {
            return httpResult.ok("取消选中商品");
        }
        return httpResult.error("暂无商品可选");
    }

    @ApiOperation(value = "将选中的商品生成订单，同时从购物车里移除")
    @PostMapping("/shoppingCart/order/{userId}")
    public HttpResult generateOrderList(@PathVariable Integer userId) {
        HttpResult httpResult = new HttpResult();
        Integer orderId = shoppingCartService.generateOrderList(userId, true);
        if (orderId != 0) {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("orderId", orderId);
            return httpResult.ok("已成功提交订单", jsonObject);
        }
        return httpResult.error("暂无订单可提交");
    }

    @ApiOperation(value = "根据商品id将其从购物车中删除")
    @DeleteMapping("/shoppingCart/{userId}/{merId}")
    public HttpResult deleteMer(@PathVariable Integer userId, @PathVariable Integer merId) {
        HttpResult httpResult = new HttpResult();
        if (shoppingCartService.delete(userId, merId) == 1) {
            return httpResult.ok("已成功删除商品");
        }
        return httpResult.error("商品已被删除");
    }

    @ApiOperation(value = "根据商品id将课程商品加入对应用户的购物车")
    @HystrixCommand
    @PostMapping("/shoppingCart/{recommenderId}/{userId}/{merId}")
    public HttpResult addMer(@PathVariable Integer recommenderId,
                             @PathVariable Integer userId, @PathVariable Integer merId) {
        HttpResult httpResult = new HttpResult();
        BaseResultDTO result = shoppingCartService.isApplied(recommenderId,userId,merId);
        if (result.isSuccess()) {
            // 如果已选该课程
            return httpResult.ok(httpResult.getMsg(),httpResult.getData());
        } else {
            return httpResult.error(result.getMessage());
        }
    }

    public HttpResult Global_FallbackMethod(){
        return HttpResult.error(HttpStatus.SC_REQUEST_TIMEOUT,"请求超时，请稍后再试");
    }
}
