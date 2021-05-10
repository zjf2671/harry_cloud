package com.zjf.common.hystrix.wrapper;


import com.zjf.common.hystrix.util.DCThreadLocalUtil;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Callable;

/**
 * 实现HystrixCallableWrapper接口，实现数据在父子线程之间的传递
 *
 * @author Harry
 */
public final class DataContextAwareCallableWrapper implements HystrixCallableWrapper {

    @Override
    public <T> Callable<T> wrap(Callable<T> callable) {
        return new DataContextAwareCallable<>(callable, DCThreadLocalUtil.get());
    }

    static class DataContextAwareCallable<T> implements Callable<T> {

        private final Callable<T> delegate;
        private final Map<String, Object> contextMap;

        DataContextAwareCallable(Callable<T> callable, Map<String, Object> contextMap) {
            this.delegate = callable;
            this.contextMap = contextMap;
        }

        @Override
        public T call() throws Exception {
            try {
                if(Objects.nonNull(contextMap) && contextMap.size()>0)
                    DCThreadLocalUtil.setMap(contextMap);
                return delegate.call();
            } finally {
                DCThreadLocalUtil.clear();
            }
        }
    }
}
