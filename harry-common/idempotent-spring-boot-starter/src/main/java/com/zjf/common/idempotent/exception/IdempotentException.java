package com.zjf.common.idempotent.exception;

/**
 * 自定义幂等异常
 * @author Harry
 */
public class IdempotentException extends RuntimeException {

    public IdempotentException(String message) {
        super(message);
    }

}
