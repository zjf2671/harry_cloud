package com.zjf.product.service.impl;

import com.google.common.collect.Lists;
import com.zjf.product.output.ProductOuputDTO;
import com.zjf.product.service.ProductService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Harry
 * @date 2019/4/1
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Override
    public List<ProductOuputDTO> list(List<Long> productIdList) {
        List<ProductOuputDTO> productOuputDTOList = Lists.newArrayList();
        for (int i = 0; i < 10; i++) {
            ProductOuputDTO productOuputDTO = new ProductOuputDTO();
            productOuputDTO.setProductId(Long.parseLong(i + ""));
            productOuputDTO.setProductName("product-" + i);
            productOuputDTO.setProductDesc("Desc-" + i);
            productOuputDTO.setPrice(BigDecimal.valueOf(Long.parseLong(i + "")));
            productOuputDTOList.add(productOuputDTO);
        }
        if (productIdList != null && productIdList.size() > 0) {
            Iterator<ProductOuputDTO> iterator = productOuputDTOList.iterator();
            while (iterator.hasNext()) {
                ProductOuputDTO productOuputDTO = iterator.next();
                if (!productIdList.contains(productOuputDTO.getProductId())) {
                    iterator.remove();
                }
            }
        }
        return productOuputDTOList;

    }

}
