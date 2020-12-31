package com.oes.service;

import com.alibaba.fastjson.JSONObject;
import com.oes.config.Url;
import com.oes.dao.OrderListDao;
import com.oes.entity.OrderList;
import com.oes.model.query.user.PaymentQuery;
import com.oes.model.vo.user.UserVo;
import com.oes.query.OrderListQuery;
import com.oes.util.http.HttpResult;
import com.oes.util.page.PageUtils;
import com.oes.vo.OrderItemVO;
import com.oes.vo.OrderListVO;
import com.oes.vo.OrderListVOS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.*;

/**
 * @author zhang chenghuai
 * @Title:
 * @Package
 * @Description:
 * @date 2020/9/179:48
 */
@Service
public class OrderListService {
    @Autowired
    private OrderListDao orderListDao;

    @Autowired
    private CourseMerService courseMerService;

    @Autowired
    private MerchandiseService merchandiseService;

    @Resource
    private RestTemplate restTemplate;

    public List<OrderList> findByOrderId(Integer orderId) {
        return orderListDao.findByOrderId(orderId);
    }

    @Transactional
    public List<OrderItemVO> generateOrderItemVO(OrderListQuery orderListQuery) {
        // 获取订单明细
        List<OrderItemVO> orderItemVOS = toOrderListVO(orderListDao.find(orderListQuery));
        return orderItemVOS;
    }

    public List<OrderItemVO> generateOrderItemVO(Integer orderId) {
        // 获取订单明细
        List<OrderItemVO> orderItemVOS = toOrderListVO(orderListDao.findByOrderId(orderId));
        return orderItemVOS;
    }


    public OrderListVO getOrderListVO(Integer orderId) {
        List<OrderItemVO> orderItemVOS = generateOrderItemVO(orderId);
        if (orderItemVOS.size() > 0)
            return new OrderListVO(orderItemVOS);
        return null;
    }


    public OrderListVOS generateOrderListVO(OrderListQuery orderListQuery) {
        // 获取订单明细
        List<OrderItemVO> orderItemVOS = toOrderListVO(find(orderListQuery));
        HashMap<Integer, List<OrderItemVO>> oivMap = new HashMap<>();
        // 筛选指定orgId的课程商品，并生成结果列表
        List<OrderListVO> orderListVOS = new ArrayList<>();
        if (null == orderListQuery.getOrgId() || 0 == orderListQuery.getOrgId()) {
            for (OrderItemVO oiv : orderItemVOS) {
                if (!oivMap.containsKey(oiv.getOrderId())) {
                    oivMap.put(oiv.getOrderId(), new ArrayList<>());
                }
                oivMap.get(oiv.getOrderId()).add(oiv);
            }
        } else {
            for (OrderItemVO oiv : orderItemVOS) {
                JSONObject oivObj = oiv.getOrderItem();
                System.out.println(oivObj);
                if (oivObj.getJSONObject("object").getInteger("orgId").equals(orderListQuery.getOrgId())) {
                    if (!oivMap.containsKey(oiv.getOrderId())) {
                        oivMap.put(oiv.getOrderId(), new ArrayList<>());
                    }
                    oivMap.get(oiv.getOrderId()).add(oiv);
                }
            }
        }

        // 获取键
        Set<Integer> set = oivMap.keySet();
        for (Integer orderId : set) {
            // 添加到orderList列表
            orderListVOS.add(new OrderListVO(oivMap.get(orderId)));
        }
        // 按时间逆序排序
        Collections.sort(orderListVOS, new Comparator<OrderListVO>() {
            @Override
            public int compare(OrderListVO o1, OrderListVO o2) {
                if (o1.getOrderTime().before(o2.getOrderTime())) {
                    return 1;
                }
                return -1;
            }
        });
        if (orderListQuery.getPageNum() == null || orderListQuery.getPageSize() == null) {
            return new OrderListVOS(set.size(), orderListVOS);
        }
        return new OrderListVOS(set.size(), PageUtils.startPage(orderListVOS, orderListQuery.getPageNum(), orderListQuery.getPageSize()));
    }



    public List<OrderList> find(OrderListQuery orderListQuery) {
        return orderListDao.find(orderListQuery);
    }

    public void cancelToBePaidOrderList() {
        orderListDao.cancelToBePaidOrderList();
    }

    public List<OrderItemVO> toOrderListVO(List<OrderList> orderLists) {
        List<OrderItemVO> orderItemVOS = new ArrayList<>();
        for (OrderList ol : orderLists) {
            orderItemVOS.add(new OrderItemVO(ol));
        }
        return orderItemVOS;
    }

    public boolean increaseStock(Integer orderId) {
        List<OrderList> orderLists = orderListDao.findByOrderId(orderId);
        if (merchandiseService.increaseStock(orderLists)&&courseMerService.increaseStock(orderLists)) {
            return true;
        }
        return false;
    }

    @Transactional
    public int cancelOrderList(Integer orderId) {
        if (increaseStock(orderId)) {
            return orderListDao.cancelOrderList(orderId);
        }
        return 0;
    }

    /**
     * 发起支付请求，并添加对应的选课记录
     * @param paymentQuery
     * @return
     */
    @Transactional
    public boolean payment(PaymentQuery paymentQuery) {
        BigDecimal transaction = new BigDecimal(0);
        if (orderListDao.payment(paymentQuery.getOrderId(), paymentQuery.getPayType(),
                paymentQuery.getOperator(), new Date(), transaction, paymentQuery.getPayer(), paymentQuery.getPayerPhone()) > 0) {
            List<OrderList> orderLists = orderListDao.findByOrderId(paymentQuery.getOrderId());
            // 自动添加选课记录
            for (OrderList ol : orderLists) {
                int courseId = courseMerService.findByMerId(ol.getMerId()).getCourseId();
                HttpResult result = restTemplate.exchange
                        (Url.SERVICE_COURSE+"/student/"+ol.getUserId().longValue()+"/course/"+courseId+"/application", HttpMethod.GET,null,
                                new ParameterizedTypeReference<HttpResult>() {
                                }).getBody();
                if (!(result.getCode() == 200)) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

}
