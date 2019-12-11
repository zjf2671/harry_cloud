package com.zjf.common.config;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.zjf.common.mybatisplus.Injector.MSSqlSqlInjector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * mybatis-plus配置
 * @author harry
 */
@Configuration
public class MybatisPlusConfig {

    /**
     * mybatis-plus分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        // 开启 PageHelper 的支持
//        paginationInterceptor.setLocalPage(true);
        return paginationInterceptor;
    }

    @Bean
    public ISqlInjector logicSqlInjector() {
        return new MSSqlSqlInjector();
    }



}