package com.zjf;

import com.didispace.swagger.butler.EnableSwaggerButler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author Harry
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
@EnableFeignClients("com.zjf.client")
@EnableSwaggerButler
public class GatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}

}
