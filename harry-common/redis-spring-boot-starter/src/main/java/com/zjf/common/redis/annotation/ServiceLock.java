package com.zjf.common.redis.annotation;
import java.lang.annotation.*;

/**
 * 自定义注解 同步锁(只能用于第一个事务开始的地方)
 * @author harry.zhang
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})    
@Retention(RetentionPolicy.RUNTIME)    
@Documented    
public  @interface ServiceLock {

	/**
	 * 支持#p0 + #p1参数索引，支持s#参数1 + #参数2的spel表达式方式
	 * @return
	 */
	 String lockPath()  default "";
}