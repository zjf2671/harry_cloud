package com.zjf.common.ribbon;

import com.zjf.common.ribbon.config.RestTemplateProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.ribbon.DefaultPropertiesFactory;
import org.springframework.context.annotation.Bean;

/**
 * Ribbon扩展配置类
 *
 * @author Harry
 */
@EnableConfigurationProperties(RestTemplateProperties.class)
public class RibbonAutoConfigure {
    @Bean
    public DefaultPropertiesFactory defaultPropertiesFactory() {
        return new DefaultPropertiesFactory();
    }
}
