package com.zjf.product.input;

import lombok.Data;

import java.math.BigDecimal;

/**
 * Created by Harry on 2019/3/31.
 */
@Data
public class ProductInputDTO {

    private Long productId;

    private String productName;

    private BigDecimal price;

    private String productDesc;

}
