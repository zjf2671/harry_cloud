package com.zjf.common.hystrix.wrapper;

import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategy;

import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.Callable;

/**
 * 类似 SecurityContextConcurrencyStrategy，插件式扩展实现：被执行目标方法执行前后的处理包装
 *
 * @author Harry
 */
public class MusesHystrixConcurrencyStrategy extends HystrixConcurrencyStrategy {

    private final Collection<HystrixCallableWrapper> wrappers;

    public MusesHystrixConcurrencyStrategy(Collection<HystrixCallableWrapper> wrappers) {
        this.wrappers = wrappers;
    }

    @Override
    public <T> Callable<T> wrapCallable(Callable<T> callable) {
        return new CallableWrapperChain<>(callable, wrappers.iterator()).wrapCallable();
    }

    private static class CallableWrapperChain<T> {

        private final Callable<T> callable;

        private final Iterator<HystrixCallableWrapper> wrappers;

        CallableWrapperChain(Callable<T> callable, Iterator<HystrixCallableWrapper> wrappers) {
            this.callable = callable;
            this.wrappers = wrappers;
        }

        Callable<T> wrapCallable() {
            Callable<T> delegate = callable;
            while (wrappers.hasNext()) {
                delegate = wrappers.next().wrap(delegate);
            }
            return delegate;
        }
    }
}
