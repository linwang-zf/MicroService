package com.oes.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @author dxh924
 * @Title:
 * @Package
 * @Description: 生成商品、订单id
 * @date 2020/9/1411:59
 */
public class ShoppingUtils {
    /**
     * 生成商品id，限制9位数
     * @return
     */
    public static Integer createMerId() {
        String dateStr = new SimpleDateFormat("HHmmss").format(new Date()).substring(1);
        String merId = dateStr + (new Random(System.currentTimeMillis()).nextInt(900) + 100);
        return Integer.parseInt(merId);
    }

    /**
     * 生成订单id
     * @param userId
     * @return
     */
    public static Integer createOrderId(Integer userId, Integer merId) {
        String orderId = String.valueOf(userId + merId).substring(0, 7) + new Random(System.currentTimeMillis()).nextInt(10);
        return Integer.parseInt(orderId);
    }
}
