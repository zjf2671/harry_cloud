package com.zjf.order.controller;

import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.zy.product.ResultDTO;
import com.zy.product.output.ProductOuputDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * 编程式测试服务降级和熔断
 * @author Harry
 * @date 2019/4/3
 */
@RestController
@DefaultProperties(defaultFallback = "defaultFallback") //默认降级策略
@Slf4j
public class HystrixController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/getProductList")
    @HystrixCommand(commandProperties = {
//            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds", value="50"),
//            @HystrixProperty(name="circuitBreaker.enabled", value="true"), //开起断路器
//            @HystrixProperty(name="circuitBreaker.requestVolumeThreshold", value="10"),
//            @HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds", value="10000"),
//            @HystrixProperty(name="circuitBreaker.errorThresholdPercentage", value="60")
    })
    public ResultDTO getProductList(@RequestParam("number") Integer number){
        if(number % 2 == 0){
            return null;
        }
        ResultDTO<List<ProductOuputDTO>> resultDTO = restTemplate.postForObject("http://PRODUCT/product/list", Arrays.asList(2), ResultDTO.class);
        return resultDTO;

    }

    public ResultDTO defaultFallback(){
        //"默认降级策略生效。。。。。。。。。。。"
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setErrorMsg("默认降级策略生效。。。。。。。。。。。");
        log.error("HystrixController.getProductList默认降级策略生效。。。。。。。。。。。");
        return resultDTO;
    }

}
