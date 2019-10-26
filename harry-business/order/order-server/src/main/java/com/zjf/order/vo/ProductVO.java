package com.zjf.order.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 *
 * @author Harry
 * @date 2019/4/2
 */
@Data
public class ProductVO {

    private Long productId;

    private String productName;

    private BigDecimal price;

    private String productDesc;

}
