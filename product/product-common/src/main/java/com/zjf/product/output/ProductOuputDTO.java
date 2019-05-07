package com.zjf.product.output;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by Harry on 2019/3/31.
 */
@Data
public class ProductOuputDTO {

    private Long productId;

    private String productName;

    private BigDecimal price;

    private String productDesc;

}
