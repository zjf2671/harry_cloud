package com.zjf;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author Harry
 * @EnableCircuitBreaker //开启断路器
 * @EnableHystrixDashboard //开启Hystrix dashboard
 */

@EnableFeignClients(basePackages = "com.zy.client")
@EnableHystrixDashboard
//@SpringBootApplication
//@EnableDiscoveryClient
//@EnableCircuitBreaker
@SpringCloudApplication
public class OrderServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderServerApplication.class, args);
	}

}
