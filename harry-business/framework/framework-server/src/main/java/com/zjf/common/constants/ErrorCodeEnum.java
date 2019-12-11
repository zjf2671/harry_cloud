package com.zjf.common.constants;

/**
 * 错误码(按系统索要码段)，系统之前不允许code一样的情况
 *
 * @author Harry
 */

public enum ErrorCodeEnum {

    USER_PASSWORD_ERROR(10001, "账号或密码不正确");
    private int code;
    private String value;

    ErrorCodeEnum(int code, String value) {
        this.code = code;
        this.value = value;
    }

    public int getCode() {
        return code;
    }

    public String getValue() {
        return value;
    }


}
