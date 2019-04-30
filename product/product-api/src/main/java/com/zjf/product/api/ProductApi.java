package com.zjf.product.api;

import com.zy.product.ResultDTO;
import com.zy.product.output.ProductOuputDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * 服务提供者暴露接口
 * @author Harry
 * @date 2019/4/1
 */
public interface ProductApi {

    /**
     * 查询商品列表
     * @param productIdList
     * @return
             */
    @PostMapping("/product/list")
    ResultDTO<List<ProductOuputDTO>> list(@RequestBody List<Long> productIdList);

}
