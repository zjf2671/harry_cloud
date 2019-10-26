package com.zjf.product.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Harry
 * @date 2019/4/1
 */
@RestController
@RequestMapping
public class EnvController {

    @Value("${env}")
    private String env;

    @RequestMapping("/getEnv")
    public String getEnv(){
        return env;
    }
}
