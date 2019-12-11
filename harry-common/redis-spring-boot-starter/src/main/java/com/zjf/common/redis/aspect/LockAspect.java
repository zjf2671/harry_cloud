package com.zjf.common.redis.aspect;

import com.zjf.common.core.utils.SpelUtil;
import com.zjf.common.redis.annotation.ServiceLock;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.integration.redis.util.RedisLockRegistry;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 * 同步锁 AOP
 * @author harry.zhang
 */
@Component
@Aspect
@Slf4j
public class LockAspect {

	@Autowired
	private RedisLockRegistry redisLockRegistry;

	/**
	 * Service层切点
	 */
	@Pointcut("@annotation(com.zjf.common.redis.annotation.ServiceLock)")
	public void lockAspectPoint() {

	}

	@Around("lockAspectPoint()")
	public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Method method = signature.getMethod();
		String lockPath = getRediskey(joinPoint);
		Lock lock = redisLockRegistry.obtain(lockPath);
		//如果锁被其它线程获取，3秒之内重试获取
		boolean acquire = false;
		Object obj = null;
		try {
			acquire = lock.tryLock(3, TimeUnit.SECONDS);
			if (acquire) {
				log.debug(Thread.currentThread() + "-------------->acquire lock");
				obj = joinPoint.proceed();
			} else {
				log.info(Thread.currentThread() + "-------------->acquire lock fail");
			}
		}catch (Throwable e){
			log.error("tryLock exception----->>>>>",e);
		}finally {
			if (acquire) {
				lock.unlock();
				log.debug(Thread.currentThread() + "-------------->release lock");
			}
		}
		return obj;

	}

	/**
	 * 获取拦截到的请求方法
	 *
	 * @param joinPoint 切点
	 * @return lockPath
	 */
	private String getRediskey(ProceedingJoinPoint joinPoint) {
		Signature signature = joinPoint.getSignature();
		MethodSignature methodSignature = (MethodSignature) signature;
		Method targetMethod = methodSignature.getMethod();
		Object target = joinPoint.getTarget();
		Object[] arguments = joinPoint.getArgs();
		ServiceLock annotation = AnnotationUtils.findAnnotation(targetMethod, ServiceLock.class);
		String lockPath = "'"+targetMethod.toGenericString()+"'";
		if (annotation != null && StringUtils.isNotBlank(annotation.lockPath())) {
			lockPath += "+"+annotation.lockPath();
		}
		return SpelUtil.parse(target, lockPath, targetMethod, arguments);

	}
}