package com.zjf.common.core.validator;

import com.zjf.common.core.exception.BusinessException;
import org.apache.commons.lang.StringUtils;

/**
 * 数据校验
 * @author harry.zhang
 * 
 */
public abstract class Assert {

    public static void isBlank(String str, String message) {
        if (StringUtils.isBlank(str)) {
            throw new BusinessException(message);
        }
    }

    public static void isNull(Object object, String message) {
        if (object == null) {
            throw new BusinessException(message);
        }
    }
}
