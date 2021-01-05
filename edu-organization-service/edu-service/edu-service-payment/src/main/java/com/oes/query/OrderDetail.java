package com.oes.query;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author dxh924
 * @Title:
 * @Package
 * @Description:
 * @date 11/5/20208:09 PM
 */
@Data
@NoArgsConstructor
public class OrderDetail {
    /**
     * 商品分类
     */
    private String category;
    /**
     * 商品单价
     */
    // private BigDecimal price;
    private String price;
    /**
     * 商品id
     */
    // private Integer good_id;
    private String good_id;
    /**
     * 商品标题
     */
    private String good_title;
    /**
     * 商铺id，可空
     */
    // private Integer shop_id;
    private String shop_id;
    /**
     * 商品名称，可空
     */
    private String shop_name;
    private String remark1;
    private String remark2;
    private String remark3;
    private String remark4;
    private String remark5;
}
