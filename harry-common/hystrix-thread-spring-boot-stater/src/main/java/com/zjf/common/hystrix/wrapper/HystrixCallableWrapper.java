package com.zjf.common.hystrix.wrapper;

import java.util.concurrent.Callable;

/**
 * 用于包装hystrix中Callable实例的接口
 *
 * @author Harry
 */
public interface HystrixCallableWrapper {

    /**
     * 包装Callable实例
     *
     * @param callable 待包装实例
     * @param <T>      返回类型
     * @return 包装后的实例
     */
    <T> Callable<T> wrap(Callable<T> callable);

}
