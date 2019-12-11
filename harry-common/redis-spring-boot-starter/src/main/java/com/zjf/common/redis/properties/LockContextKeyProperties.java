package com.zjf.common.redis.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;

import java.util.List;

/**
 * @author Harry
 * 配置锁上下文key
 */
@Setter
@Getter
@ConfigurationProperties(prefix = "zjf.redis")
@RefreshScope
public class LockContextKeyProperties {

    private String lockContextKey = "commonStarterKey";

}
