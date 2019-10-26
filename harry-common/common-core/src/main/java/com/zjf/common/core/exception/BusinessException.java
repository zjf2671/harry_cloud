package com.zjf.common.core.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * @Description: 自定义异常 
 * @author harry.zhang
 * @CreateDate:	2018年3月25日
 * @version 1.0
 */
@Getter
@Setter
public class BusinessException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	/**
	 * 异常对应的错误类型
	 */
	private ErrorType errorType;

    private String msg;
    private int code = 500;

	public BusinessException(ErrorType errorType) {
		super(errorType.getMsg());
		this.errorType = errorType;
		this.code = errorType.getCode();
		this.msg = errorType.getMsg();
	}
    
    public BusinessException(String msg) {
		super(msg);
		this.msg = msg;
	}
	
	public BusinessException(String msg, Throwable e) {
		super(msg, e);
		this.msg = msg;
	}
	
	public BusinessException(String msg, int code) {
		super(msg);
		this.msg = msg;
		this.code = code;
	}

	public BusinessException(ErrorType errorType, Throwable e) {
		super(errorType.getMsg(), e);
		this.errorType = errorType;
		this.msg = errorType.getMsg();
		this.code = errorType.getCode();
	}
	
	public BusinessException(String msg, int code, Throwable e) {
		super(msg, e);
		this.msg = msg;
		this.code = code;
	}

}
