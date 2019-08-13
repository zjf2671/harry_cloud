package com.zjf.order.service.impl;

import com.google.common.collect.Lists;
import com.zjf.client.product.ProductClient;
import com.zjf.common.util.BeanCommonUtils;
import com.zjf.order.service.OrderService;
import com.zjf.order.vo.OrderVO;
import com.zjf.order.vo.ProductVO;
import com.zjf.product.ResultDTO;
import com.zjf.product.output.ProductOuputDTO;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Harry
 * @date 2019/4/2
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private ProductClient productClient;

    @Override
    public List<OrderVO> list(Long userId) {

        //构造order数据
        List<OrderVO> orderVOList = Lists.newArrayList();
        for(int i=0; i<5; i++){
            OrderVO orderVO = new OrderVO();
            orderVO.setOrderId(Long.parseLong(i+""));
            orderVO.setUserId(Long.parseLong(i+""));
            orderVO.setPrice(BigDecimal.valueOf(Long.parseLong(i+"")));
            List<Long> productIds = Lists.newArrayList();
            for(int j=0; j < 3; j++){
                Long randomId = Long.parseLong(RandomUtils.nextInt(10)+"");
                productIds.add(randomId);
            }
            List<ProductVO> productVOList = Lists.newArrayList();
            ResultDTO<List<ProductOuputDTO>> resultDTO = productClient.list(productIds);
            if(resultDTO.getCode()==200 && resultDTO.getData() != null){
                BeanCommonUtils.copyListProperties(resultDTO.getData(), productVOList, ProductVO.class);
            }
            orderVO.setProductVOList(productVOList);
            orderVOList.add(orderVO);
        }

        //通过用户查询order数据
        Iterator<OrderVO> iterator = orderVOList.iterator();
        while (iterator.hasNext()){
            OrderVO vo = iterator.next();
            if(!vo.getUserId().equals(userId)){
                iterator.remove();
            }
        }

        return orderVOList;

    }
}
