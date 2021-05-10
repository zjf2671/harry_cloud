package com.zjf.common.idempotent;

import com.zjf.common.idempotent.aspect.DistributedIdempotentAspect;
import com.zjf.common.idempotent.properties.IdempotentProperties;
import com.zjf.common.idempotent.storage.IdempotentStorageFactory;
import com.zjf.common.idempotent.storage.IdempotentStorageRedis;
import com.zjf.common.redis.RedisAutoConfigure;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Harry
 */
@Configuration
@ImportAutoConfiguration(IdempotentProperties.class)
@AutoConfigureAfter(RedisAutoConfigure.class)
public class IdempotentAutoConfiguration {

    @Bean
    public DistributedIdempotent distributedIdempotent() {
        return new DistributedIdempotentImpl();
    }

    @Bean
    public DistributedIdempotentAspect distributedIdempotentAspect() {
        return new DistributedIdempotentAspect();
    }

    @Bean
    public IdempotentStorageFactory idempotentStorageFactory() {
        return new IdempotentStorageFactory();
    }

    @Bean
    public IdempotentStorageRedis idempotentStorageRedis() {
        return new IdempotentStorageRedis();
    }

}
