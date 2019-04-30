package com.zjf.common.user;

import lombok.Data;

import java.io.Serializable;

/**
 * 返回数据
 * 
 * @author harry.zhang
 * 
 */
@Data
public class ResultVO<T> implements Serializable{

	private static final long serialVersionUID = 1L;

	public static final int OK_CODE = 200;

	private Integer code;

	private String msg;

	private T data;

	public ResultVO() {
		this.code = OK_CODE;
		this.msg = "success";
	}
	
	public static ResultVO error() {
		return error(500, "未知异常，请联系管理员");
	}
	
	public static ResultVO error(String msg) {
		return error(500, msg);
	}
	
	public static ResultVO error(int code, String msg) {
		ResultVO r = new ResultVO();
		r.setCode(code);
		r.setMsg(msg);
		return r;
	}

	public static <T> ResultVO ok(T t) {
		ResultVO r = new ResultVO();
		r.setData(t);
		return r;
	}
	
	public static ResultVO ok() {
		return new ResultVO();
	}

}
