package com.zjf.common.idempotent;

import com.zjf.common.idempotent.web.IdempotentParamFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Harry
 */
@Configuration
public class WebAutoConfiguration {

    @Bean
    public IdempotentParamFilter idempotentParamFilter(){
        return  new IdempotentParamFilter();
    }
}
