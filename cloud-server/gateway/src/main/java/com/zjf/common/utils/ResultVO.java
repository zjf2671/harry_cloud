package com.zjf.common.utils;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 返回数据结果集
 * 
 * @author harry.zhang
 * 
 */
@Data
@ApiModel(value="ResultVO对象", description="返回结果对象")
public class ResultVO<T> implements Serializable{

	private static final long serialVersionUID = 1L;

	public static final int OK_CODE = 200;

	@ApiModelProperty(value = "返回状态码", dataType = "int")
	private Integer code;

	@ApiModelProperty("消息描述")
	private String msg;

	@ApiModelProperty("返回数据")
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
