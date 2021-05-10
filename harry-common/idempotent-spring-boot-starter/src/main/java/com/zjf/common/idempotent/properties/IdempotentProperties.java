package com.zjf.common.idempotent.properties;

import com.zjf.common.idempotent.enums.IdempotentStorageTypeEnum;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 存储配置类 可扩展
 * @author Harry
 */
@Data
@ConfigurationProperties(prefix = "zjf.idempotent")
public class IdempotentProperties {

    /**
     * 存储类型
     * @see IdempotentStorageTypeEnum
     */
    private String storageType = IdempotentStorageTypeEnum.REDIS.name();


}
