package com.zjf.common.exception;

import com.zjf.common.core.exception.BusinessException;
import com.zjf.common.core.utils.vo.ResultVO;
import feign.FeignException;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.exceptions.TooManyResultsException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 网关过来的统一异常处理器
 *
 * @author harry.zhang
 */
@RestControllerAdvice(annotations = ApiIgnore.class)
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

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResultVO handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e){
		log.error(e.getMessage(), e);
		return ResultVO.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
	}

	@ExceptionHandler(DuplicateKeyException.class)
	public ResultVO handleDuplicateKeyException(DuplicateKeyException e){
		log.error(e.getMessage(), e);
		return ResultVO.error("数据库中已存在该记录");
	}
	@ExceptionHandler(TooManyResultsException.class)
	public ResultVO handleTooManyResultsException(TooManyResultsException e){
		log.error(e.getMessage(), e);
		return ResultVO.error("请输入正确的字符长度");
	}

	@ExceptionHandler(Exception.class)
	public ResultVO handleException(Exception e){
		log.error(e.getMessage(), e);
		return ResultVO.error("XXXXXX系统出现异常,请联系管理员！");
	}

	@ExceptionHandler(FeignException.class)
	public ResultVO handleException(FeignException e){
		log.error(e.getMessage(), e);
		return ResultVO.error(e.status(),e.getMessage());
	}
}
