package com.zjf.common.idempotent.aspect;

import com.zjf.common.idempotent.annotation.Idempotent;
import com.zjf.common.idempotent.constants.IdempotentConstants;
import com.zjf.common.idempotent.exception.IdempotentException;
import com.zjf.common.idempotent.request.IdempotentRequest;
import com.zjf.common.core.utils.SpelUtil;
import com.zjf.common.idempotent.DistributedIdempotent;
import com.zjf.common.idempotent.context.ContextHolder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;

/**
 * @author Harry
 */
@Aspect
public class DistributedIdempotentAspect extends AbstractIdempotentAspectSupport {

    @Autowired
    private DistributedIdempotent distributedIdempotent;

    @Around(value = "@annotation(idempotent)")
    public Object around(ProceedingJoinPoint joinPoint, Idempotent idempotent) throws Throwable {
        Object[] args = joinPoint.getArgs();
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();

        String key = "";
        if (StringUtils.hasText(idempotent.spelKey())) {
            key = SpelUtil.parse(joinPoint.getTarget(), idempotent.spelKey(), method, args);
        } else {
            key = ContextHolder.getCurrentContext().get(IdempotentConstants.IDEMPOTEMT_ID_NAME);
        }

        String userInputKey = idempotent.value();
        if (!StringUtils.hasText(userInputKey)) {
            userInputKey = joinPoint.getTarget().getClass().getName()+":"+method.getName();
        }
        String idempotentKey = userInputKey + ":" + key;

        IdempotentRequest request = IdempotentRequest.builder().key(idempotentKey)
                .storageExpireTime(idempotent.storageExpireTime())
                .lockExpireTime(idempotent.lockExpireTime())
                .build();

        try {
            return distributedIdempotent.execute(request, () -> {
                try {
                    return joinPoint.proceed();
                } catch (Throwable e) {
                    throw new RuntimeException(e);
                }
            }, () -> {
                throw new IdempotentException("重复请求");
            });
        } catch (IdempotentException ex) {
            return handleIdempotentException(joinPoint, idempotent, ex);
        }
    }

}
