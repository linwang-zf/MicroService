package com.oes.entity;

import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * merchandise
 * @author 
 */
@Data
@AllArgsConstructor
@Setter
@Getter
@NoArgsConstructor
public class Merchandise implements Serializable {
    /**
     * 标识一款商品
     */
    private Integer merId;

    /**
     * 商品类型
     */
    private String merType;

    /**
     * 商品入库（创建）时间
     */
    private Date merTime;

    /**
     * 商品价格
     */
    private BigDecimal merPrice;

    /**
     * 商品是否有效：0-下架，1-上架
     */
    private Byte merState;

    /**
     * 商品库存
     */
    private Integer merStock;

    private static final long serialVersionUID = 1L;
}