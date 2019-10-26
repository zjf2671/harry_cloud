package com.zjf.common.config;

import org.apache.http.client.HttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;

/**
 * @Description restTemplate config
 * @Author Harry
 * @Date 2019/4/4 14:52
 **/
@Configuration
public class RestTemplateConfig {

//    @Bean
//    public RestTemplate directRestTemplate(HttpClient httpClient){
//        RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory(httpClient));
//        restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(Charset.forName("UTF-8")));
//        return restTemplate;
//    }

    @Bean
    @LoadBalanced
    public RestTemplate lbRestTemplate(){
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate;
    }

}
