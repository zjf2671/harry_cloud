package com.zjf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

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

	@Autowired
	private GracefulShutdownTomcat gracefulShutdownTomcat;

	@Bean
	public ServletWebServerFactory servletContainer() {
		TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
		tomcat.addConnectorCustomizers(gracefulShutdownTomcat);
		return tomcat;
	}

}
