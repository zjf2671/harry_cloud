package com.zjf.common.idempotent.storage;

import com.zjf.common.idempotent.enums.IdempotentStorageTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

/**
 * 幂等存储工厂类
 * @author Harry
 */
public class IdempotentStorageFactory {

    @Autowired
    private List<IdempotentStorage> idempotentStorageList;

    public IdempotentStorage getIdempotentStorage(IdempotentStorageTypeEnum type) {
        Optional<IdempotentStorage> idempotentStorageOptional = idempotentStorageList.stream().filter(t -> t.type() == type).findAny();
        return idempotentStorageOptional.orElseThrow(NullPointerException::new);
    }

}
