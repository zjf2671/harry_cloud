package com.zjf.common.core.utils.dto;

import com.zjf.common.core.exception.ErrorType;
import lombok.Data;

import java.io.Serializable;

/**
 * 返回数据
 * 
 * @author harry.zhang
 * 
 */
@Data
public class ResultDTO<T> implements Serializable{

	private static final long serialVersionUID = 1L;

	public static final int OK_CODE = 200;

	private Integer code;

	private String msg;

	private T data;

	public ResultDTO() {
		this.code = OK_CODE;
		this.msg = "success";
	}

	public static ResultDTO error(ErrorType errorType) {
		return error(errorType.getCode(), errorType.getMsg());
	}
	
	public static ResultDTO error() {
		return error(500, "系统出现异常，请联系管理员");
	}
	
	public static ResultDTO error(String msg) {
		return error(500, msg);
	}
	
	public static ResultDTO error(int code, String msg) {
		ResultDTO r = new ResultDTO();
		r.setCode(code);
		r.setMsg(msg);
		return r;
	}

	public static <T> ResultDTO<T> ok(T t) {
		ResultDTO<T> r = new ResultDTO<>();
		r.setData(t);
		return r;
	}
	public static <T> ResultDTO<T> ok(int code, T t) {
		ResultDTO<T> r = new ResultDTO<>();
		r.setCode(code);
		r.setData(t);
		return r;
	}
	
	public static ResultDTO ok() {
		return new ResultDTO();
	}

}
