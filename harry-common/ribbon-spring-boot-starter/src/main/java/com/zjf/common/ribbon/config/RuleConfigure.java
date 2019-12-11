package com.zjf.common.ribbon.config;

import com.netflix.loadbalancer.IRule;
import com.zjf.common.ribbon.rule.CustomIsolationRule;
import org.springframework.context.annotation.Bean;

/**
 * @author Harry
 */
public class RuleConfigure {
    @Bean
    public IRule isolationRule() {
        return new CustomIsolationRule();
    }
}
