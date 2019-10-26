package com.zjf.common.core.exception;

/**
 * 抽象返回code msg, 各系统枚举实现此接口定义系统中的code
 * @author Harry
 */
public interface ErrorType {
    /**
     * 返回code
     *
     * @return
     */
    int getCode();

    /**
     * 返回msg
     *
     * @return
     */
    String getMsg();
}
