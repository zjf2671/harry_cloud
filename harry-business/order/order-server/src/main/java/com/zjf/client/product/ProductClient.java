package com.zjf.client.product;

import com.zjf.product.api.ProductApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 继承服务方提供的接口
 * @author Harry
 */
@FeignClient(value = "product", fallback = ProductClientFallBack.class)
public interface ProductClient extends ProductApi {


}
