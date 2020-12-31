package com.oes.controller;


import com.oes.entity.Merchandise;
import com.oes.service.MerchandiseService;
import com.oes.util.http.HttpResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author zhang chenghuai
 * @Title:
 * @Package
 * @Description:
 * @date 2020/9/219:31
 */
@RestController
@Api(tags = {"商品管理"})
public class MerchandiseController {
    @Autowired
    private MerchandiseService merchandiseService;

    @ApiOperation(value = "根据商品类型查询所有商品")
    @GetMapping("/merchandise/findAll/{merType}")
    public HttpResult findMerchandiseByType(@PathVariable String merType) {
        HttpResult httpResult = new HttpResult();
        List<Merchandise> merchandiseList = merchandiseService.findMerchandiseByType(merType);
        if (merchandiseList.size() > 0) {
            return httpResult.ok("获取"+merType+"类商品成功", merchandiseList);
        } else {
            return httpResult.error("暂无"+merType+"类商品");
        }
    }

    @ApiOperation(value = "根据商品id查询商品")
    @GetMapping("/merchandise/find/{merId}")
    public HttpResult findMerchandiseById(@PathVariable Integer merId) {
        HttpResult httpResult = new HttpResult();
        Merchandise merchandise = merchandiseService.findMerchandiseById(merId);
        if (merchandise != null) {
            return httpResult.ok("获取商品成功", merchandise);
        } else {
            return httpResult.error("暂无对应商品");
        }
    }

    @ApiOperation(value = "根据商品状态查询商品")
    @GetMapping("/merchandise/find/state/{merState}")
    public HttpResult findMerchandiseByState(@PathVariable Integer merState) {
        HttpResult httpResult = new HttpResult();
        List<Merchandise> merchandiseList = merchandiseService.findMerchandiseByState(merState);
        if (merchandiseList.size() > 0) {
            return httpResult.ok("获取商品成功", merchandiseList);
        } else {
            return httpResult.error("暂无对应商品");
        }
    }

//    @ApiOperation(value = "将指定id的商品上架")
//    @PutMapping("/merchandise/putOn/{merId}")
//    public HttpResult putOnShelves(@PathVariable Integer merId) {
//        HttpResult httpResult = new HttpResult();
//        List<Merchandise> merchandiseList = merchandiseService.putOnShelves(merId);
//        if (merchandiseList.size() > 0) {
//            return httpResult.ok("获取商品成功", merchandiseList);
//        } else {
//            return httpResult.error("暂无对应商品");
//        }
//    }
//
//    @ApiOperation(value = "将指定id的商品下架")
//    @PutMapping("/merchandise/pullOff/{merId}")
//    public HttpResult pullOffShelves(@PathVariable Integer merId) {
//        HttpResult httpResult = new HttpResult();
//        List<Merchandise> merchandiseList = merchandiseService.pullOffShelves(merId);
//        if (merchandiseList.size() > 0) {
//            return httpResult.ok("获取商品成功", merchandiseList);
//        } else {
//            return httpResult.error("暂无对应商品");
//        }
//    }
}
