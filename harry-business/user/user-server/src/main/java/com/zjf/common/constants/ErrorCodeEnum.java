package com.zjf.common.constants;

/**
 * 错误码
 * @author Harry
 */

public enum ErrorCodeEnum {

    TOKEN_EXPIRED(100001,"token expired"),
    NO_TOKEN(100002,"no token"),
    TOKEN_ERROR(100003,"token error"),
    USER_PASSWORD_ERROR(10001,"账号或密码不正确"),
    USER_LOCK(10002,"账号已被锁定,请联系管理员");

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
