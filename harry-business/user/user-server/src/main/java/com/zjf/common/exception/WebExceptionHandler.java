package com.zjf.common.exception;

import com.zjf.common.utils.ResultVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
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
	public ResultVO handleBusinessException(BusinessException e){
		logger.error(e.getMessage(), e);
		return ResultVO.error(e.getCode(),e.getMsg());
	}

	@ExceptionHandler(DuplicateKeyException.class)
	public ResultVO handleDuplicateKeyException(DuplicateKeyException e){
		logger.error(e.getMessage(), e);
		return ResultVO.error("数据库中已存在该记录");
	}

	@ExceptionHandler(Exception.class)
	public ResultVO handleException(Exception e){
		logger.error(e.getMessage(), e);
		return ResultVO.error();
	}
}
