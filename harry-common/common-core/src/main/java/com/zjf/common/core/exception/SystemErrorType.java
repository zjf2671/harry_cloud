package com.zjf.common.core.exception;

import lombok.Getter;

/**
 * 系统共同
 * @author Harry
 */

@Getter
public enum SystemErrorType implements ErrorType {

    SYSTEM_ERROR(500, "系统异常,请联系管理员"),
    SYSTEM_BUSY(11405, "系统繁忙,请稍候再试"),

    GATEWAY_NOT_FOUND_SERVICE(11404, "服务未找到"),
    GATEWAY_ERROR(11502, "网关异常"),
    GATEWAY_CONNECT_TIME_OUT(11408, "网关超时"),

    ARGUMENT_NOT_VALID(11406, "请求参数校验不通过"),
    UPLOAD_FILE_SIZE_LIMIT(11400, "上传文件大小超过限制"),

    DUPLICATE_PRIMARY_KEY(11407,"唯一键冲突");

    /**
     * 错误类型码
     */
    private int code;
    /**
     * 错误类型描述信息
     */
    private String msg;

    SystemErrorType(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
