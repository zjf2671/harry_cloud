package com.zjf.testbean;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class Bean1 implements BeanNameAware, InitializingBean, DisposableBean {
      
    @Autowired
    public Bean2 bean2;

    private String name;

    public String state;  
      
    public Bean1 (){  
        state = "construct!";  
        System.out.println("bean1 -> " + state + "..." + "bean2: " + bean2 + "-----------------------"+name);
    }  
      
    public void init_method (){  
        state = "init-method!";  
        System.out.println("bean1 -> " + state + "..." + "bean2: " + bean2 + "-----------------------"+name);
    }  
      
    public void destory_method (){  
        state = "destory-method!";  
        System.out.println("bean1 -> " + state + "..." + "bean2: " + bean2);  
    }  
      
    @PostConstruct
    public void postConstruct (){  
        state = "postConstruct!";  
        System.out.println("bean1 -> " + state + "..." + "bean2: " + bean2);  
    }  
      
    @PreDestroy
    public void preDestory (){  
        state = "preDestory!";  
        System.out.println("bean1 -> " + state + "..." + "bean2: " + bean2);  
    }  
  
    @Override  
    public void destroy() throws Exception {  
        state = "destory!";  
        System.out.println("bean1 -> " + state + "..." + "bean2: " + bean2);  
    }  
  
    @Override  
    public void afterPropertiesSet() throws Exception {  
        state = "afterPropertiesSet!";  
        System.out.println("bean1 -> " + state + "..." + "bean2: " + bean2);  
    }


    @Override
    public void setBeanName(String name) {
        this.name = name;
    }
}
