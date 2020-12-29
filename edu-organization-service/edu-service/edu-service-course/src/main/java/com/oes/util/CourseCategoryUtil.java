package com.oes.util;


import com.oes.model.entity.CourseCategory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author : yanjundong
 * @date : 2020-04-13 17:02
 * @description : 课程类别的工具类
 */

public class CourseCategoryUtil {

    /*处理课程类别树，方便前端显示*/
    public static void handleCategorys(List<CourseCategory> categorys, List<Map<String, Object>> result) {
        if (null !=categorys && !categorys.isEmpty()) {
            for (CourseCategory category : categorys) {
                Map<String, Object> item = new HashMap<>();
                item.put("value", category.getCategoryId());
                item.put("label", category.getName());
                item.put("level", category.getLevel());
                item.put("parentId", category.getParentId());

                List<Map<String, Object>> children = new ArrayList<>();
                handleCategorys(category.getChildren(), children);
                if (children.size() > 0)
                    item.put("children", children);
                result.add(item);
            }
        }
    }

}
