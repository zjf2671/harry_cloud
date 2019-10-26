package com.zjf.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 *
 * @author Harry
 * @date 2019/4/1
 */

@Component
@ConfigurationProperties(prefix = "foo")
@Data
public class Foo {

    private String name;

    private Integer age;

    private Integer sex;

}
