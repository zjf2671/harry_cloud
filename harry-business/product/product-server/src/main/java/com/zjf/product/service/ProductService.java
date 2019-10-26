package com.zjf.product.service;


import com.zjf.product.output.ProductOuputDTO;

import java.util.List;

/**
 * Created by Harry on 2019/4/1.
 */
public interface ProductService {

    List<ProductOuputDTO> list(List<Long> productIdList);
}
