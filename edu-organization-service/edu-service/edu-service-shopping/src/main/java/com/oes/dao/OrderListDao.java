package com.oes.dao;


import com.oes.entity.OrderList;
import com.oes.query.OrderListQuery;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Mapper
public interface OrderListDao {
    int insert(OrderList record);

    int insertSelective(OrderList record);

    List<OrderList> find(OrderListQuery orderListQuery);

    List<OrderList> findByUserId(Integer userId, Integer state);

    List<OrderList> findAllByUserId(Integer userId);

    List<OrderList> findByOrderId(Integer orderId);

    int cancelOrderList(Integer orderId);

    int cancelToBePaidOrderList();

    int payment(Integer orderId, String payType, String operator, Date payTime, BigDecimal transaction, String payer, String payerPhone);

    //int payment2(Integer orderId, String payType, String operator, Date payTime, BigDecimal transaction);

    int setDiscount(Integer orderId, Integer merId, String operator, BigDecimal discount);
}