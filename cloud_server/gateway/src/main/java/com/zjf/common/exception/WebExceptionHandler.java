package com.zjf.common.exception;

import com.zjf.common.utils.ResultVO;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 异常处理器
 *
 * @author harry.zhang
 */
@RestControllerAdvice
@Slf4j
public class WebExceptionHandler {

	/**
	 * 处理自定义异常
	 */
	@ExceptionHandler(BusinessException.class)
	public ResultVO handleBusinessException(BusinessException e){
		log.error(e.getMessage(), e);
		return ResultVO.error(e.getCode(),e.getMsg());
	}

	@ExceptionHandler(Exception.class)
	public ResultVO handleException(Exception e){
		log.error(e.getMessage(), e);
		return ResultVO.error();
	}

	@ExceptionHandler(FeignException.class)
	public ResultVO handleException(FeignException e){
		log.error(e.getMessage(), e);
		return ResultVO.error(e.status(),e.getMessage());
	}
}
