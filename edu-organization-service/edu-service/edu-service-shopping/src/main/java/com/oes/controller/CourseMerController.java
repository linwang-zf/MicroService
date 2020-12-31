package com.oes.controller;

import com.alibaba.fastjson.JSONObject;

import com.oes.entity.CourseMer;
import com.oes.model.dto.BaseResultDTO;
import com.oes.query.CourseMerQuery;
import com.oes.service.CourseMerService;
import com.oes.service.MerchandiseService;
import com.oes.service.ShoppingCartService;
import com.oes.util.http.HttpResult;
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
 * @date 2020/9/1616:56
 */
@RestController
@Api(tags = {"课程商品管理"})
public class CourseMerController {
    @Autowired
    private CourseMerService courseMerService;

    @Autowired
    private MerchandiseService merchandiseService;

    @Autowired
    private ShoppingCartService shoppingCartService;



    @ApiOperation(value = "上架课程商品")
    @PutMapping("/courseMer/putOn/{merId}")
    public HttpResult putOnCourseMer(@PathVariable Integer merId) {
        if (courseMerService.putOnCourseMer(merId) == 1) {
            return new HttpResult().ok("已上架商品id为" + merId + "的课程");
        }
        return new HttpResult().error("课程商品已上架或不存在");
    }

    @ApiOperation(value = "下架课程商品")
    @PutMapping("/courseMer/pullOff/{merId}")
    public HttpResult pullOffCourseMer(@PathVariable Integer merId) {
        if (courseMerService.pullOffCourseMer(merId) == 1) {
            return new HttpResult().ok("已下架商品id为" + merId + "的课程");
        }
        return new HttpResult().error("课程商品已下架或不存在");
    }

    @ApiOperation(value = "根据课程商品状态查询课程商品")
    @GetMapping("/courseMer/find/state/{merState}")
    public HttpResult findCourseMerByState(@PathVariable Integer merState) {
        HttpResult httpResult = new HttpResult();
        List<CourseMer> courseMerList = courseMerService.findCourseMerByState(merState);
        if (courseMerList.size() > 0) {
            return httpResult.ok("获取商品成功", courseMerList);
        } else {
            return httpResult.error("暂无对应商品");
        }
    }

    @ApiOperation(value = "根据课程商品id查询课程商品")
    @GetMapping("/courseMer/find/merId/{merId}")
    public HttpResult findByMerId(@PathVariable Integer merId) {
        HttpResult httpResult = new HttpResult();
        CourseMer courseMer = courseMerService.findByMerId(merId);
        if (courseMer != null) {
            return httpResult.ok("获取商品成功", courseMer);
        } else {
            return httpResult.error("暂无对应商品");
        }
    }

    @ApiOperation(value = "根据课程id查询课程商品")
    @GetMapping("/courseMer/find/courseId/{courseId}")
    public HttpResult findByCourseId(@PathVariable Integer courseId) {
        HttpResult httpResult = new HttpResult();
        List<CourseMer> courseMerList = courseMerService.findByCourseId(courseId);
        if (courseMerList.size() > 0) {
            return httpResult.ok("获取商品成功", courseMerList);
        } else {
            return httpResult.error("暂无对应商品");
        }
    }

    @ApiOperation(value = "根据机构id查询课程商品")
    @GetMapping("/courseMer/find/orgId/{orgId}")
    public HttpResult findByOrgId(@PathVariable Integer orgId) {
        HttpResult httpResult = new HttpResult();
        List<CourseMer> courseMerList = courseMerService.findByOrgId(orgId);
        if (courseMerList.size() > 0) {
            return httpResult.ok("获取商品成功", courseMerList);
        } else {
            return httpResult.error("暂无对应商品");
        }
    }

    @ApiOperation(value = "根据教师id查询课程商品")
    @GetMapping("/courseMer/find/teacherId/{teacherId}")
    public HttpResult findByTeacherId(@PathVariable Integer teacherId) {
        HttpResult httpResult = new HttpResult();
        List<CourseMer> courseMerList = courseMerService.findByTeacherId(teacherId);
        if (courseMerList.size() > 0) {
            return httpResult.ok("获取商品成功", courseMerList);
        } else {
            return httpResult.error("暂无对应商品");
        }
    }

    @ApiOperation(value = "条件查询课程商品，参数包含机构id和商品状态和课程名")
    @PostMapping("/courseMer/find")
    public HttpResult findCourseMer(@RequestBody CourseMerQuery courseMerQuery) {
        HttpResult httpResult = new HttpResult();
        List<CourseMer> courseMerList = courseMerService.findCourseMer(courseMerQuery);
        System.out.println(courseMerList);
        JSONObject result = new JSONObject();
        result.put("total", courseMerService.findCourseMerCount(courseMerQuery));
        result.put("list", courseMerList);
        if (courseMerList.size() > 0) {
            return httpResult.ok("获取商品成功", result);
        } else {
            return httpResult.error("暂无对应商品");
        }
    }
    @ApiOperation(value = "管理员代学生购课")
    @PostMapping("/courseMer/{recommenderId}/{userId}/buy/{merId}")
    public HttpResult buyCourse(@PathVariable Integer userId, @PathVariable Integer merId,
                                @PathVariable Integer recommenderId) {
        HttpResult httpResult = new HttpResult();
        BaseResultDTO result = courseMerService.buyCourse(userId, merId, recommenderId);
        if(result.isSuccess()){
            return httpResult.ok(result.getMessage(),result.getData());
        }else{
            return httpResult.error(result.getMessage());
        }
    }
    @ApiOperation(value = "查询机构所属的所有试听课")
    @GetMapping("/courseMer/{orgId}")
    public HttpResult queryCourseMerByOrgId(@RequestParam(required = false) Integer orgId) {
        HttpResult httpResult = new HttpResult();
        List<CourseMer> courseMers = courseMerService.queryCourseMerByOrgId(orgId);
        if(courseMers == null){
            return httpResult.error("查询失败");
        }else if(courseMers.size() == 0){
            return httpResult.ok("该机构尚未推出试听课");
        }else {
            return httpResult.ok("查询到的试听课",courseMers);
        }
    }

}
