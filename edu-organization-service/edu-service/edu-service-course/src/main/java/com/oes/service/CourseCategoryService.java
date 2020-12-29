package com.oes.service;


import com.oes.constant.consist.RedisKeyConstants;
import com.oes.dao.CourseCategoryDao;
import com.oes.model.dto.BaseResultDTO;
import com.oes.model.entity.CourseCategory;
import com.oes.util.CourseCategoryUtil;
import com.oes.util.redis.RedisOperator;
import com.oes.vo.CourseCategoryVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author : yanjundong
 * @date : 2020-04-09 17:26
 * @description : 课程类别
 */

@Service
@Slf4j
public class CourseCategoryService {
    @Resource
    private CourseCategoryDao courseCategoryDao;
    @Resource
    private RedisOperator redisOperator;
    private final Lock lock = new ReentrantLock();



    /**
     * 获取课程类别树
     */
    public List<Map<String, Object>> getCategoryTree() {
        Object value = redisOperator.get(RedisKeyConstants.PRE_COURSE_CATEGORY + "tree");
        List<Map<String, Object>> result = null;
        if (null == value) {
            /*成功获取到锁*/
            if (lock.tryLock()) {
                try {
                    List<CourseCategory> categories = courseCategoryDao.queryByParentId(-1);
                    if (null == categories) {
                        log.error("从mysql获取课程目录树失败");
                        return null;
                    }
                    result = new ArrayList<>(categories.size());
                    CourseCategoryUtil.handleCategorys(categories, result);
                    redisOperator.set(RedisKeyConstants.PRE_COURSE_CATEGORY + "tree", result, 60 * 60 * 5);
                } finally {
                    lock.unlock();  /*释放锁*/
                }
            } else {
                try {
                    Thread.sleep(100);
                    result = getCategoryTree();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } else {
            result = (List<Map<String, Object>>) value;
        }
        return result;

    }

    /**
     * 返回课程目录的叶子节点
     *
     * @return 目录信息
     */
    public List<CourseCategory> getLeafCategory() {
        List<CourseCategory> courseCategories = courseCategoryDao.getCategoryLeaf();
        return courseCategories;
    }

    public List<CourseCategoryVO> getAllCourseCategory() {
        return courseCategoryDao.getAllCourseCategory();
    }

    public CourseCategory getCourseCategoryById(Integer categoryId){
        return courseCategoryDao.queryById(categoryId);
    }
}
