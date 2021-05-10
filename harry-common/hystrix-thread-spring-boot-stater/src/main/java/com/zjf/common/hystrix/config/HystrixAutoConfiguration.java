package com.zjf.common.hystrix.config;

import com.netflix.hystrix.strategy.HystrixPlugins;
import com.netflix.hystrix.strategy.concurrency.HystrixConcurrencyStrategy;
import com.netflix.hystrix.strategy.eventnotifier.HystrixEventNotifier;
import com.netflix.hystrix.strategy.executionhook.HystrixCommandExecutionHook;
import com.netflix.hystrix.strategy.metrics.HystrixMetricsPublisher;
import com.netflix.hystrix.strategy.properties.HystrixPropertiesStrategy;
import com.zjf.common.hystrix.wrapper.DataContextAwareCallableWrapper;
import com.zjf.common.hystrix.wrapper.HystrixCallableWrapper;
import com.zjf.common.hystrix.wrapper.MusesHystrixConcurrencyStrategy;
import com.zjf.common.hystrix.wrapper.RequestAttributeAwareCallableWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * 与 Hystrix 相关的公共配置
 *
 * @author Harry
 */
@Slf4j
@Configuration
public class HystrixAutoConfiguration {

    @Autowired(required = false)
    private List<HystrixCallableWrapper> wrappers;

    @Bean
    public HystrixCallableWrapper requestAttributeAwareCallableWrapper() {
        return new RequestAttributeAwareCallableWrapper();
    }

    @Bean
    public HystrixCallableWrapper dataContextAwareCallableWrapper() {
        return new DataContextAwareCallableWrapper();
    }

    @Bean
    public HystrixConcurrencyStrategy requestContextHystrixConcurrencyStrategy() {
        return new MusesHystrixConcurrencyStrategy(wrappers);
    }

    @PostConstruct
    public void init() {
        if (!CollectionUtils.isEmpty(wrappers)) {
            try {
                HystrixConcurrencyStrategy strategy = HystrixPlugins.getInstance().getConcurrencyStrategy();
                if (strategy instanceof MusesHystrixConcurrencyStrategy) {
                    return;
                }
                HystrixConcurrencyStrategy hystrixConcurrencyStrategy = new MusesHystrixConcurrencyStrategy(wrappers);

                // 获取原来的相关数据配置
                HystrixCommandExecutionHook commandExecutionHook = HystrixPlugins
                        .getInstance().getCommandExecutionHook();
                HystrixEventNotifier eventNotifier = HystrixPlugins.getInstance()
                        .getEventNotifier();
                HystrixMetricsPublisher metricsPublisher = HystrixPlugins.getInstance()
                        .getMetricsPublisher();
                HystrixPropertiesStrategy propertiesStrategy = HystrixPlugins.getInstance()
                        .getPropertiesStrategy();

                // 打印日志
                if (log.isDebugEnabled()) {
                    log.debug("Current Hystrix plugins configuration is [concurrencyStrategy [{}], eventNotifier [{}], metricPublisher [{}], propertiesStrategy [{}]]",
                            hystrixConcurrencyStrategy, eventNotifier, metricsPublisher, propertiesStrategy);
                    log.debug("Registering Muses Hystrix Concurrency Strategy.");
                }

                // 重置再重新填充
                // 直接设置会触发异常 Caused by: java.lang.IllegalStateException: Another strategy was already registered.
                HystrixPlugins.reset();
                HystrixPlugins.getInstance().registerConcurrencyStrategy(hystrixConcurrencyStrategy);
                HystrixPlugins.getInstance()
                        .registerCommandExecutionHook(commandExecutionHook);
                HystrixPlugins.getInstance().registerEventNotifier(eventNotifier);
                HystrixPlugins.getInstance().registerMetricsPublisher(metricsPublisher);
                HystrixPlugins.getInstance().registerPropertiesStrategy(propertiesStrategy);
            } catch (Exception e) {
                log.error("Failed to register Muses Hystrix Concurrency Strategy", e);
            }
        }
    }
}

