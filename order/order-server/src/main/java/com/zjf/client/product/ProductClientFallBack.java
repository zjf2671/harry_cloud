package com.zjf.client.product;

import com.zjf.product.ResultDTO;
import com.zjf.product.output.ProductOuputDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 默认是public static, product服务降级类
 */
@Component
@Slf4j
class ProductClientFallBack implements ProductClient {
    @Override
    public ResultDTO<List<ProductOuputDTO>> list(List<Long> productIdList) {
        //处理降级策略
        log.error("----------------ProductClient.list 服务出现故障，已将服务进行降级--------------");
        ResultDTO<List<ProductOuputDTO>> resultDTO = new ResultDTO<>();
        resultDTO.setCode(-1);
        resultDTO.setErrorMsg("----------------ProductClient.list 服务出现故障，已将服务进行降级--------------");
        return resultDTO;
    }
}