package com.zjf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author Harry
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients("com.zjf.client")
public class FrameworkServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(FrameworkServerApplication.class, args);
	}

}
