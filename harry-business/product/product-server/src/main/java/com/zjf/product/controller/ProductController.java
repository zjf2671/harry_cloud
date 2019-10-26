package com.zjf.product.controller;

import com.zjf.product.ResultDTO;
import com.zjf.product.output.ProductOuputDTO;
import com.zjf.product.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *
 * @author Harry
 * @date 2019/4/1
 */
@RestController
@RequestMapping("/product")
@Slf4j
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping(value = "/list")
    public ResultDTO<List<ProductOuputDTO>> list(@RequestBody List<Long> productIdList) throws InterruptedException {
        ResultDTO<List<ProductOuputDTO>> resultDTO = new ResultDTO<>();
        log.info("productIdList====>:{}",productIdList);
        Thread.sleep(100);
        List<ProductOuputDTO> list = productService.list(productIdList);
        resultDTO.setData(list);
        return resultDTO;
    }
}
