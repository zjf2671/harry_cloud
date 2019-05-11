package com.zjf.common.exception;

import com.zjf.common.user.ResultDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 异常处理器
 *
 * @author harry.zhang
 */
@RestControllerAdvice
public class WebExceptionHandler {
	private Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 处理自定义异常
	 */
	@ExceptionHandler(BusinessException.class)
	public ResultDTO handleBusinessException(BusinessException e){
		logger.error(e.getMessage(), e);
		return ResultDTO.error(e.getCode(),e.getMsg());
	}

	@ExceptionHandler(Exception.class)
	public ResultDTO handleException(Exception e){
		logger.error(e.getMessage(), e);
		return ResultDTO.error();
	}
}
