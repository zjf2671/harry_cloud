package com.zjf.common.idempotent.storage;

import com.zjf.common.idempotent.enums.IdempotentStorageTypeEnum;

/**
 * 幂等存储接口
 *
 * @author Harry
 */
public interface IdempotentStorage {

    IdempotentStorageTypeEnum type();

    void setValue(String key, String value, long expireTime);

    String getValue(String key);

}
