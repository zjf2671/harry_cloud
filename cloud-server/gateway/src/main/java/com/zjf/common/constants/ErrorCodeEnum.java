package com.zjf.common.constants;

/**
 * 错误码
 * @author Harry
 */

public enum ErrorCodeEnum {
    /**
     * token 过期
     */
    TOKEN_EXPIRED(100001,"token expired"),
    /**
     * 没有token
     */
    NO_TOKEN(100002,"no token"),
    /**
     * token 错误
     */
    TOKEN_ERROR(100003,"token error"),
    /**
     * token 无授权
     */
    TOKEN_UNAUTHORIZED(100004,"token Unauthorized");

    private int code;
    private String value;

    ErrorCodeEnum(int code, String value) {
        this.code = code;
        this.value = value;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
