package com.zjf.common.aotoconfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.cloud.autoconfigure.RefreshAutoConfiguration;
import org.springframework.cloud.endpoint.RefreshEndpoint;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.IntervalTask;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

/**
 * @author Harry
 */
@Configuration
@ConditionalOnClass(RefreshEndpoint.class)
@ConditionalOnProperty("spring.cloud.config.refreshInterval")
@AutoConfigureAfter(RefreshAutoConfiguration.class)
@EnableScheduling
public class AutoconfigRefreshConfigure implements SchedulingConfigurer {

    private static final Logger LOGGER = LoggerFactory.getLogger(AutoconfigRefreshConfigure.class);

    @Autowired
    public AutoconfigRefreshConfigure(RefreshEndpoint refreshEndpoint) {
        this.refreshEndpoint = refreshEndpoint;
    }

    @Value("${spring.cloud.config.refreshInterval}")
    private long refreshInterval;

    private final RefreshEndpoint refreshEndpoint;


    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        final long interval = getRefreshIntervalilliseconds();
        LOGGER.info("***************************** 定时刷新延迟 {} 秒启动，每 {} 毫秒刷新一次配置 *****************************", refreshInterval, interval);
        scheduledTaskRegistrar.addFixedDelayTask(new IntervalTask(refreshEndpoint::refresh, interval, interval));
    }

    /**
     * 返回毫秒级时间间隔
     */
    private long getRefreshIntervalilliseconds() {
        return refreshInterval * 1000;
    }

//    @ConditionalOnMissingBean(ScheduledAnnotationBeanPostProcessor.class)
//    @EnableScheduling
//    @Configuration
//    protected static class EnableSchedulingConfigProperties{
//
//    }

}
