package com.oes.service;


import com.alibaba.fastjson.JSONObject;
import com.oes.config.Url;
import com.oes.dao.OrderListDao;
import com.oes.dao.ShoppingCartDao;
import com.oes.entity.CourseMer;
import com.oes.entity.OrderList;
import com.oes.entity.ShoppingCart;
import com.oes.model.dto.BaseResultDTO;
import com.oes.model.vo.user.UserVo;
import com.oes.util.ShoppingUtils;
import com.oes.util.http.HttpResult;
import com.oes.util.json.JsonUtils;
import com.oes.vo.ShoppingCartVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author zhang chenghuai
 * @Title:
 * @Package
 * @Description:
 * @date 2020/9/1619:46
 */
@Service
public class ShoppingCartService {
    @Autowired
    private ShoppingCartDao shoppingCartDao;

    @Autowired
    private OrderListDao orderListDao;

    @Autowired
    private CourseMerService courseMerService;

    @Autowired
    private MerchandiseService merchandiseService;

    @Resource
    private RestTemplate restTemplate;
    @Resource
    private ShoppingCartService shoppingCartService;

    @Transactional
    public int insert(Integer userId, Integer merId, Integer recommenderId) {
        CourseMer courseMer = courseMerService.findByMerId(merId);
        //TODO 1.1-2 测试
       // String recommender = userService.queryById(recommenderId).getAccount();
        HttpResult<UserVo> result = restTemplate.exchange
                (Url.SERVICE_USERCENTER+"/user/"+recommenderId, HttpMethod.GET, null,
                        new ParameterizedTypeReference<HttpResult<UserVo>>() {
                        }).getBody();
        String recommender = result.getData().getAccount();
        if (courseMer == null || shoppingCartDao.isExisted(userId, merId)!=null ) { // 如果课程商品不存在或已被加入购物车
            return 0;
        }
        ShoppingCart shoppingCart = new ShoppingCart(userId, merId, JsonUtils.toJsonString(courseMer),
                courseMer.getCoursePrice(), (byte) 0, recommender);
        return shoppingCartDao.insert(shoppingCart);
    }

    public List<ShoppingCartVO> findAllByUserId(Integer userId) {
        List<ShoppingCart> shoppingCarts = shoppingCartDao.findAllByUserId(userId);
        return toShoppingCartVO(shoppingCarts);
    }

    public List<ShoppingCartVO> findByUserId(Integer userId, Integer state) {
        List<ShoppingCart> shoppingCarts = shoppingCartDao.findByUserId(userId, state);
        return toShoppingCartVO(shoppingCarts);
    }

    public int selectAll(Integer userId) {
        return shoppingCartDao.selectAll(userId);
    }

    public int unSelectAll(Integer userId) {
        return shoppingCartDao.unSelectAll(userId);
    }

    public int setOrderList(OrderList orderList) {
        return orderListDao.insertSelective(orderList);
    }

    public int selectIt(Integer userId, Integer merId) {
        return shoppingCartDao.selectIt(userId, merId);
    }

    public int unSelectIt(Integer userId, Integer merId) {
        return shoppingCartDao.unSelectIt(userId, merId);
    }

    public int delete(Integer userId, Integer merId) {
        return shoppingCartDao.delete(userId, merId);
    }

    /**
     * 提交订单后减少库存
     * @param merId
     * @return
     */
    public int reduceStock(Integer merId) {
        if (courseMerService.reduceStock(merId) == 0 || merchandiseService.reduceStock(merId) == 0) {
            return 0;
        }
        return 1;
    }

    /**
     * 从购物车生成订单
     * @param userId
     * @param isOnline 是否线上支付
     * @return
     */
    @Transactional
    public Integer generateOrderList(Integer userId, boolean isOnline) {
        Integer orderId;
        // 先获取已选中的商品
        List<ShoppingCart> selectedMer;
        if (isOnline) {
            selectedMer = shoppingCartDao.findByUserId(userId, 1);
        } else {
            selectedMer = shoppingCartDao.findByUserId(userId, 0);
        }
        if (selectedMer.size() > 0) {
            // 生成订单id
            orderId = ShoppingUtils.createOrderId(selectedMer.get(0).getUserId(), selectedMer.get(0).getMerId());
            Date orderTime = new Date();
            // 计算订单总价
            BigDecimal totalPrice = calTotalPrice(selectedMer);
            for (ShoppingCart shoppingCart : selectedMer) {
                // 通过商品id获取课程商品信息
                CourseMer courseMer = courseMerService.findByMerId(shoppingCart.getMerId());
                HttpResult<UserVo> result = restTemplate.exchange
                        (Url.SERVICE_USERCENTER+"/user/"+userId, HttpMethod.GET, null,
                                new ParameterizedTypeReference<HttpResult<UserVo>>() {
                                }).getBody();
                UserVo user = result.getData();
                // 根据课程商品信息写入订单表并从购物车表中删除，商品库存-1
                OrderList orderList = new OrderList(orderId, userId, courseMer, orderTime,
                        totalPrice, user, shoppingCart.getRecommender());
                if (setOrderList(orderList) == 0 || delete(shoppingCart.getUserId(), shoppingCart.getMerId()) == 0
                || reduceStock(shoppingCart.getMerId()) == 0 ) {
                    return 0;
                }
            }
            return orderId;
        }
        return 0;
    }

    public Integer generateOrderList(Integer userId, Integer recommenderId, Integer merId) {
        // 生成订单id
        Integer orderId = ShoppingUtils.createOrderId(userId, merId);
        Date orderTime = new Date();
        CourseMer courseMer = courseMerService.findByMerId(merId);
        //String recommmender = userService.queryById(recommenderId).getAccount();
        HttpResult<UserVo> result = restTemplate.exchange
                (Url.SERVICE_USERCENTER+"/user/"+recommenderId, HttpMethod.GET, null,
                        new ParameterizedTypeReference<HttpResult<UserVo>>() {
                        }).getBody();
        UserVo user = result.getData();
        String recommender = user.getAccount();
            // 根据课程商品信息写入订单表并从购物车表中删除，商品库存-1
        OrderList orderList = new OrderList(orderId, userId, courseMer, orderTime,
                courseMer.getCoursePrice(), user, recommender);
        if (setOrderList(orderList) == 0 || reduceStock(merId) == 0) {
            return 0;
        }
        return orderId;
    }

    public List<ShoppingCartVO> toShoppingCartVO(List<ShoppingCart> shoppingCarts) {
        List<ShoppingCartVO> shoppingCartVOs = new ArrayList<>();
        for (ShoppingCart shoppingCart : shoppingCarts) {
            shoppingCartVOs.add(new ShoppingCartVO(shoppingCart));
        }
        return shoppingCartVOs;
    }

    public BigDecimal calTotalPrice(List<ShoppingCart> shoppingCarts) {
        BigDecimal totalPrice = new BigDecimal(0);
        for (ShoppingCart cs : shoppingCarts) {
            totalPrice = totalPrice.add(cs.getMerPrice());
        }
        return totalPrice;
    }

    public BaseResultDTO isApplied(Integer recommenderId, Integer userId, Integer merId){
        int courseId = courseMerService.findByMerId(merId).getCourseId();
        boolean isApplied = restTemplate.getForObject(Url.SERVICE_COURSE+"/student/api/apply?userId"+userId+"&courseId="+courseId,Boolean.class);
        if (isApplied) {
            // 如果已选该课程
            return new BaseResultDTO(false,"用户已购买该课程，请勿重复添加购物车");
        } else {
            if (shoppingCartService.insert(userId, merId, recommenderId) == 1) {
                return new BaseResultDTO(true,"已成功将商品加入购物车");
            }
            return new BaseResultDTO(false, "请勿重复操作");
        }
    }

}
