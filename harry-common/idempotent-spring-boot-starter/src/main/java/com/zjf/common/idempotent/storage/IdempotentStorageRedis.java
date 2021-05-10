package com.zjf.common.idempotent.storage;

import com.zjf.common.idempotent.enums.IdempotentStorageTypeEnum;
import com.zjf.common.redis.util.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * redis幂等存储实现
 *
 * @author Harry
 */
@Slf4j
public class IdempotentStorageRedis implements IdempotentStorage {

    @Autowired
    private RedisUtils redisUtils;

    @Override
    public IdempotentStorageTypeEnum type() {
        return IdempotentStorageTypeEnum.REDIS;
    }

    @Override
    public void setValue(String key, String value, long expireTime) {
        log.debug("Redis Set key:{}, Value:{}, expireTime:{}", key, value, expireTime);
        redisUtils.set(key, value, expireTime);
    }

    @Override
    public String getValue(String key) {
        String value = redisUtils.get(key);
        log.debug("Redis Get key:{}, Value:{}", key, value);
        return value;
    }
}
