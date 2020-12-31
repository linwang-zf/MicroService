package com.oes.service;


import com.alibaba.fastjson.JSONObject;
import com.oes.config.Url;
import com.oes.dao.CourseMerDao;
import com.oes.dao.MerchandiseDao;
import com.oes.entity.CourseMer;
import com.oes.entity.Merchandise;
import com.oes.entity.OrderList;
import com.oes.model.dto.BaseResultDTO;
import com.oes.model.entity.Courses;
import com.oes.model.entity.Organization;
import com.oes.model.entity.Teacher;
import com.oes.query.CourseMerQuery;
import com.oes.util.ShoppingUtils;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;


/**
 * @author dxh924
 * @Title:
 * @Package
 * @Description:
 * @date 2020/9/1616:57
 */
@Service
public class CourseMerService {
    @Autowired
    private CourseMerDao courseMerDao;

    @Autowired
    private MerchandiseDao merchandiseDao;

    @Autowired
    private OrderListService orderListService;

    @Autowired
    private MerchandiseService merchandiseService;

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Resource
    private RestTemplate restTemplate;



    /**
     * 上架商品id为merId的课程
     * @param merId
     * @return
     */
    @Transactional
    public int putOnCourseMer(Integer merId) {
        if (merchandiseService.putOnMerchandise(merId) == 1) {
            return courseMerDao.putOnCourseMer(merId);
        }
        return 0;
    }

    /**
     * 下架商品id为merId的课程
     * @param merId
     * @return
     */
    @Transactional
    public int pullOffCourseMer(Integer merId) {
        if (merchandiseService.pullOffMerchandise(merId) == 1) {
            return courseMerDao.pullOffCourseMer(merId);
        }
        return 0;
    }

    /**
     * 根据课程商品状态查询课程商品
     * @param merState
     * @return
     */
    public List<CourseMer> findCourseMerByState(Integer merState) {
        return courseMerDao.findCourseMerByState(merState);
    }

    /**
     * 根据商品id查询课程商品信息
     * @param merId
     * @return
     */
    public CourseMer findByMerId(Integer merId) {
        return courseMerDao.findByMerId(merId);
    }

    /**
     * 根据课程id查询课程商品信息
     * @param courseId
     * @return
     */
    public List<CourseMer> findByCourseId(Integer courseId) {
        return courseMerDao.findByCourseId(courseId);
    }

    /**
     * 根据机构id查询课程商品信息
     * @param orgId
     * @return
     */
    public List<CourseMer> findByOrgId(Integer orgId) {
        return courseMerDao.findByOrgId(orgId);
    }

    /**
     * 根据教师id查询课程商品信息
     * @param teacherId
     * @return
     */
    public List<CourseMer> findByTeacherId(Integer teacherId) {
        return courseMerDao.findByTeacherId(teacherId);
    }


    /**
     * 减少1件库存
     * @param merId
     * @return
     */
    public int reduceStock(Integer merId) {
        return courseMerDao.reduceStock(merId);
    }

    public boolean increaseStock(List<OrderList> orderLists) {
        if (orderLists == null) {
            return false;
        }
        for (OrderList ol : orderLists) {
            if (courseMerDao.increaseStock(ol.getMerId()) == 0 ) {
                return false;
            }
        }
        return true;
    }

    //查询机构所有试听课
    public List<CourseMer> queryCourseMerByOrgId(Integer orgId){
        List<CourseMer> courseMers = courseMerDao.findByOrgIds(orgId, "试听课");
        return courseMers;
    }

    public List<CourseMer> findCourseMer(CourseMerQuery courseMerQuery) {
        return courseMerDao.findCourseMer(courseMerQuery);
    }

    public int findCourseMerCount(CourseMerQuery courseMerQuery) {
        return courseMerDao.findCourseMerCount(courseMerQuery);
    }

    public int findTotal() {
        return courseMerDao.findTotal();
    }

    /**
     * 将课程商品插入商品表
     * @param courseMer
     * @return
     */
    public int insert(CourseMer courseMer) {
        // 加入商品表
        System.out.println(courseMer);
        Merchandise merchandise;
        if(courseMer.getMerType().equals("试听课")){
            merchandise = new Merchandise(courseMer.getMerId(),  "试听课", new Date(), new BigDecimal(BigInteger.valueOf(0)), courseMer.getMerState(), courseMer.getMerStock());
        }else {
            merchandise = new Merchandise(courseMer.getMerId(),  "课程", new Date(), courseMer.getCoursePrice(), courseMer.getMerState(), courseMer.getMerStock());
        }
        System.out.println(merchandise);
        merchandiseDao.insert(merchandise);
        // 加入购物车表
//        ShoppingCart shoppingCart = new ShoppingCart(courseMer.getUserId(), merId, JsonUtils.toJsonString(courseMer).toString(), merchandise.getMerPrice(), (byte) 0);
//        shoppingCartService.insert(shoppingCart);
        return courseMerDao.insert(courseMer);
    }


    public BaseResultDTO buyCourse(Integer userId, Integer merId,Integer recommenderId){
        //TODO 1.1-1测试
        int courseId = findByMerId(merId).getCourseId();
        boolean isApplied = restTemplate.getForObject(Url.SERVICE_COURSE+"/student/api/apply?userId"+userId+"&courseId="+courseId,Boolean.class);
        if (!isApplied) {
            // 如果该课程还未选修
            Integer orderId = shoppingCartService.generateOrderList(userId, merId, recommenderId);
            if (orderId == 0) {
                return new BaseResultDTO(false,"选课失败");
            }
            JSONObject object = new JSONObject();
            object.put("orderId",orderId);
            return new BaseResultDTO(true,"选课成功，请在订单中查看课程信息",object);
        } else {
            return new BaseResultDTO(false,"该学生已选修该课程");
        }
    }
}
