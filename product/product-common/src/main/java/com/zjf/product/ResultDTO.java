package com.zjf.product;

import java.io.Serializable;

/**
 * 返回结果集
 * @author harry.zhang
 *
 */
public class ResultDTO<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private int total ;
    
    private T data ;
    
    private int code = 200;
    
	private String errorMsg;
	
	/**
	 * 结果状态 200:成功, -1:异常失败 ;
	 */
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(int code, String errorMsg) {
		this.setCode(code);
		this.errorMsg = errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		if (code == 200) {
			this.setCode(-1);
		}
		this.errorMsg = errorMsg;
	}

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
    
}
