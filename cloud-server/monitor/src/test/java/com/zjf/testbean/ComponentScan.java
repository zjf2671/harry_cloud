package com.zjf.testbean;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;

@Configuration
@org.springframework.context.annotation.ComponentScan(basePackages = "com.zjf.testbean")
public class ComponentScan {  
      
//    @Bean(initMethod = "init_method", destroyMethod = "destory_method")
//    public Bean1 bean1 (){
//        return new Bean1();
//    }
//
    public static void main(String[] args) {  
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(ComponentScan.class);  
        ctx.refresh();  
        ctx.registerShutdownHook();  
    }  
      
}  