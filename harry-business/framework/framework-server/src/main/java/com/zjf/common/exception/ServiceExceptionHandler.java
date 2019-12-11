package com.zjf.common.exception;

import com.zjf.common.core.exception.BusinessException;
import com.zjf.common.core.utils.dto.ResultDTO;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.exceptions.TooManyResultsException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * feign接口统一异常处理器
 *
 * @author harry.zhang
 */
@RestControllerAdvice(annotations = Api.class)
@Slf4j
public class ServiceExceptionHandler {
	/**
	 * 处理自定义异常
	 */
	@ExceptionHandler(BusinessException.class)
	public ResultDTO handleBusinessException(BusinessException e){
		log.error(e.getMessage(), e);
		return ResultDTO.error(e.getCode(),e.getMsg());
	}

	@ExceptionHandler(DuplicateKeyException.class)
	public ResultDTO handleDuplicateKeyException(DuplicateKeyException e){
		log.error(e.getMessage(), e);
		return ResultDTO.error("数据库中已存在该记录");
	}

	@ExceptionHandler(TooManyResultsException.class)
	public ResultDTO handleTooManyResultsException(TooManyResultsException e){
		log.error(e.getMessage(), e);
		return ResultDTO.error("请输入正确的字符长度");
	}

	@ExceptionHandler(Exception.class)
	public ResultDTO handleException(Exception e){
		log.error(e.getMessage(), e);
		return ResultDTO.error("XXXXXX系统出现异常,请联系管理员！");
	}
}
