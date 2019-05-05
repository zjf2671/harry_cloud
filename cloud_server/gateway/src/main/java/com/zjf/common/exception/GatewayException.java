package com.zjf.common.exception;

import com.netflix.zuul.exception.ZuulException;
import org.springframework.http.HttpStatus;

/**
 * @Description: 自定义异常
 * @author harry.zhang
 */
public class GatewayException extends ZuulException {

	private String msg;
	private int code = HttpStatus.INTERNAL_SERVER_ERROR.value();

	public GatewayException(String msg) {
		super(msg, HttpStatus.INTERNAL_SERVER_ERROR.value(), msg);
		this.msg = msg;
	}

	public GatewayException(String msg, Throwable e) {
		super(e, HttpStatus.INTERNAL_SERVER_ERROR.value(), msg);
		this.msg = msg;
	}

	public GatewayException(String msg, int code) {
		super(msg, code, msg);
		this.msg = msg;
		this.code = code;
	}

	public GatewayException(String msg, int code, Throwable e) {
		super(e, msg, code, msg);
		this.msg = msg;
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}


}
