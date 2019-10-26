package com.zjf.order.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Harry
 * @date 2019/4/2
 */
@Data
public class OrderVO {

    private Long orderId;

    private List<ProductVO> productVOList;

    private Long userId;

    private BigDecimal price;

}
