package com.zjf.order.service;

import com.zjf.order.vo.OrderVO;

import java.util.List;

/**
 *
 * @author Harry
 * @date 2019/4/2
 */
public interface OrderService {

    List<OrderVO> list(Long userId);


}
