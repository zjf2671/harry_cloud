package com.zjf.common.exception;

import com.zjf.common.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

/**
 * @Description: 异常处理器
 * @author harry.zhang
 */
@RestControllerAdvice
@Slf4j
public class WebExceptionHandler {

	/**
	 * 处理自定义异常
	 */
	@ExceptionHandler(BusinessException.class)
	public R handleBusinessException(BusinessException e) {
		R r = new R();
		r.put("code", e.getCode());
		r.put("msg", e.getMessage());

		return r;
	}

	@ExceptionHandler(NoHandlerFoundException.class)
	public R handlerNoFoundException(Exception e) {
		log.error(e.getMessage(), e);
		return R.error(404, "路径不存在，请检查路径是否正确");
	}

	@ExceptionHandler(DuplicateKeyException.class)
	public R handleDuplicateKeyException(DuplicateKeyException e) {
		log.error(e.getMessage(), e);
		return R.error("数据库中已存在该记录");
	}


	@ExceptionHandler(Exception.class)
	public R handleException(Exception e) {
		log.error(e.getMessage(), e);
		return R.error();
	}

}
