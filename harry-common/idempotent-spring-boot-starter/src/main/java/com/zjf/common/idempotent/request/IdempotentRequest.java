package com.zjf.common.idempotent.request;

import lombok.Builder;
import lombok.Data;

/**
 * 幂等请求参数实体
 * @author Harry
 */
@Data
@Builder
public class IdempotentRequest {

    /**
     * 幂等Key
     */
    private String key;

    /**
     * 储过期时间 秒
     */
    private int storageExpireTime;

    /**
     * 锁的过期时间 秒
     */
    private int lockExpireTime;

}
