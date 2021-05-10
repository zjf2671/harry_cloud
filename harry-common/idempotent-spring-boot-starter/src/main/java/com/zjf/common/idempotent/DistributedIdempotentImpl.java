package com.zjf.common.idempotent;

import com.zjf.common.idempotent.enums.IdempotentStorageTypeEnum;
import com.zjf.common.idempotent.properties.IdempotentProperties;
import com.zjf.common.idempotent.request.IdempotentRequest;
import com.zjf.common.idempotent.storage.IdempotentStorage;
import com.zjf.common.idempotent.storage.IdempotentStorageFactory;
import com.zjf.common.core.lock.DistributedLock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/**
 * 幂等实现
 * @author Harry
 */
@Slf4j
public class DistributedIdempotentImpl implements DistributedIdempotent {

    @Autowired
    private DistributedLock distributedLock;

    @Autowired
    private IdempotentProperties idempotentProperties;

    @Autowired
    private IdempotentStorageFactory idempotentStorageFactory;

    /**
     * 锁名称后缀，区分锁和幂等的Key
     */
    private String lockSuffix = "_lock";

    /**
     * 幂等Key对应的默认值
     */
    private String idempotentDefaultValue = "1";

    @Override
    public <T> T execute(String key, int lockExpireTime, int storageExpireTime, Supplier<T> execute, Supplier<T> fail) {
        IdempotentRequest idempotentRequest = IdempotentRequest.builder().key(key)
                .lockExpireTime(lockExpireTime)
                .storageExpireTime(storageExpireTime)
                .build();
        return execute(idempotentRequest, execute, fail);
    }


    private <T> T orderExecute(IdempotentStorage redisIdempotentStorage , IdempotentRequest request, Object execute, Object fail) {
        String value = redisIdempotentStorage.getValue(request.getKey());

        // 没有数据，表示可以继续执行
        if (!StringUtils.hasText(value)) {
            T executeResult = getExecuteResult(execute);

            redisIdempotentStorage.setValue(request.getKey(), idempotentDefaultValue, request.getStorageExpireTime());

            return executeResult;
        }

        // 不能继续往下执行
        if (execute instanceof Supplier) {
            Supplier<T> failSupplier = (Supplier<T>) fail;
            return failSupplier.get();
        } else {
            Runnable failRunnable = (Runnable) execute;
            failRunnable.run();
            return null;
        }
    }

    private <T> T getExecuteResult(Object execute) {
        T executeResult = null;
        if (execute instanceof Supplier) {
            Supplier<T> executeSupplier = (Supplier<T>) execute;
            executeResult = executeSupplier.get();
        } else {
            Runnable executeRunnable = (Runnable) execute;
            executeRunnable.run();
        }
        return executeResult;
    }


    @Override
    public <T> T execute(IdempotentRequest request, Supplier<T> execute, Supplier<T> fail) {
        return distributedLock.lock(request.getKey() + lockSuffix, request.getLockExpireTime(), TimeUnit.SECONDS,  () -> {


            IdempotentStorage redisIdempotentStorage = idempotentStorageFactory.getIdempotentStorage(IdempotentStorageTypeEnum.valueOf(idempotentProperties.getStorageType()));


            return orderExecute(redisIdempotentStorage, request, execute, fail);


        }, fail);
    }

    @Override
    public void execute(String key, int lockExpireTime, int storageExpireTime, Runnable execute, Runnable fail) {
        IdempotentRequest idempotentRequest = IdempotentRequest.builder().key(key)
                .lockExpireTime(lockExpireTime)
                .storageExpireTime(storageExpireTime)
                .build();
        execute(idempotentRequest, execute, fail);
    }


    @Override
    public void execute(IdempotentRequest request, Runnable execute, Runnable fail) {
        distributedLock.lock(request.getKey() + lockSuffix, request.getLockExpireTime(), TimeUnit.SECONDS,  () -> {

            IdempotentStorage redisIdempotentStorage = idempotentStorageFactory.getIdempotentStorage(IdempotentStorageTypeEnum.valueOf(idempotentProperties.getStorageType()));

            orderExecute(redisIdempotentStorage, request, execute, fail);

        }, fail);
    }
}
