
package com.oes.controller;


import com.oes.model.entity.CourseCategory;
import com.oes.service.CourseCategoryService;
import com.oes.util.http.HttpResult;
import com.oes.vo.CourseCategoryVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;
import java.util.Map;


/**
 * @author : yanjundong
 * @date : 2020-04-09 18:02
 * @description : 课程类别的 controller 层
 */


@RestController
@Api(value = "CourseCategoryController",tags = {"课程类别"})
public class CourseCategoryController {
    @Resource
    private CourseCategoryService courseCategoryService;

    @GetMapping("/coursecategory")
    @ApiOperation(value = "获取课程类别树")
    public HttpResult getCourseCategoryTree() {
        List<Map<String, Object>> result = courseCategoryService.getCategoryTree();

        if (null != result)
            return HttpResult.ok(result);
        else
            return HttpResult.error("获取课程类别失败");
    }

    @GetMapping("/coursecategory/queryAllCourseCategoryIdAndName")
    @ApiOperation(value = "获取所有课程类别的ID和Name")
    public HttpResult getCourseCategory(){
        List<CourseCategoryVO> categories = courseCategoryService.getAllCourseCategory();
        if (null != categories){
            return HttpResult.ok("查询成功",categories);
        } else {
            return HttpResult.ok("查询为空", Collections.EMPTY_LIST);
        }
    }

    //服务间对外API
    @GetMapping("/coursecategory/{categoryId}")
    public HttpResult getCourseCategoryById(@PathVariable Integer categoryId){
        CourseCategory category = courseCategoryService.getCourseCategoryById(categoryId);
        if (null != category){
            return HttpResult.ok("查询成功",category);
        } else {
            return HttpResult.ok("查询为空", Collections.EMPTY_LIST);
        }

    }
}

