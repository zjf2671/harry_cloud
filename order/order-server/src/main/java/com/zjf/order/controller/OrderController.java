package com.zjf.order.controller;

import com.zjf.client.product.ProductClient;
import com.zjf.order.service.OrderService;
import com.zjf.order.vo.OrderVO;
import com.zy.product.ResultDTO;
import com.zy.product.output.ProductOuputDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Harry
 * @date 2019/4/2
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductClient productClient;


    @GetMapping(value = "/list")
    public List<OrderVO> list(Long userId){

        List<OrderVO> orderVOList = orderService.list(userId);

        return orderVOList;
    }
    @GetMapping(value = "/productForOrder")
    public ResultDTO<List<ProductOuputDTO>> productForOrder(Long userId){

        ResultDTO<List<ProductOuputDTO>> resultDTO = productClient.list(Arrays.asList(1L,2L));

        return resultDTO;
    }

}
