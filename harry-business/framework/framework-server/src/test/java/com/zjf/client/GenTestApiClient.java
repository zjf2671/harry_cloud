package com.zjf.client;

import com.zjf.framework.api.gen.GenTest1Api;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @Description TODO
 * @Author Harry
 * @Date 2019/9/30 9:14
 **/
@FeignClient("gen")
public interface GenTestApiClient extends GenTest1Api {



}
