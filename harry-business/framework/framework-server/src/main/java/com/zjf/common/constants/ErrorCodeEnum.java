package com.zjf.common.constants;

import com.zjf.common.core.exception.ErrorType;

/**
 * 错误码(按系统索要码段)，系统之间不允许code一样的情况
 *
 * @author Harry
 */

public enum ErrorCodeEnum implements ErrorType {

    /**
     * 313131 demo
     */
    TEST(313131, "错误码(按系统索要码段)，系统之间不允许code一样的情况");
    private int code;
    private String msg;

    ErrorCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }
}
