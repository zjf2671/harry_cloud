package com.zjf.common.idempotent;

import com.zjf.common.idempotent.request.IdempotentRequest;

import java.util.function.Supplier;

/**
 * 幂等接口
 *
 */
public interface DistributedIdempotent {

    /**
     * 幂等执行
     * @param key 幂等Key
     * @param lockExpireTime 锁的过期时间
     * @param storageExpireTime 存储过期时间
     * @param execute 要执行的逻辑
     * @param fail Key已经存在，幂等拦截后的执行逻辑
     * @return
     */
    <T> T execute(String key, int lockExpireTime, int storageExpireTime, Supplier<T> execute, Supplier<T> fail);


    /**
     * 幂等执行
     * @param request 幂等参数
     * @param execute 要执行的逻辑
     * @param fail Key已经存在，幂等拦截后的执行逻辑
     * @return
     */
    <T> T execute(IdempotentRequest request, Supplier<T> execute, Supplier<T> fail);

    /**
     * 幂等执行
     * @param key 幂等Key
     * @param lockExpireTime 锁的过期时间
     * @param storageExpireTime 存储过期时间
     * @param execute 要执行的逻辑
     * @param fail Key已经存在，幂等拦截后的执行逻辑
     * @return
     */
    void execute(String key, int lockExpireTime, int storageExpireTime, Runnable execute, Runnable fail);

    /**
     * 幂等执行
     * @param request 幂等参数
     * @param execute 要执行的逻辑
     * @param fail Key已经存在，幂等拦截后的执行逻辑
     * @return
     */
    void execute(IdempotentRequest request, Runnable execute, Runnable fail);

}
