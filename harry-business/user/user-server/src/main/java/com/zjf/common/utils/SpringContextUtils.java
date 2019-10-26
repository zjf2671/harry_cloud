package com.zjf.common.utils;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * Spring Context 工具类
 * 
 * @author harry.zhang
 * 
 */
@Component
public class SpringContextUtils implements ApplicationContextAware {
	public static ApplicationContext applicationContext; 

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		SpringContextUtils.applicationContext = applicationContext;
	}

	public static Object getBean(String name) {
		return applicationContext.getBean(name);
	}

	public static <T> T getBean(String name, Class<T> requiredType) {
		return applicationContext.getBean(name, requiredType);
	}

	public static boolean containsBean(String name) {
		return applicationContext.containsBean(name);
	}

	public static boolean isSingleton(String name) {
		return applicationContext.isSingleton(name);
	}

	public static Class<? extends Object> getType(String name) {
		return applicationContext.getType(name);
	}
	
	public static HttpServletRequest getHttpServletRequest() {
		if(RequestContextHolder.getRequestAttributes() != null){
			return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		}
		return null;

	}

}