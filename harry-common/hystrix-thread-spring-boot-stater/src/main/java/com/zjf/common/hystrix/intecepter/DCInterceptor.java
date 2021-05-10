package com.zjf.common.hystrix.intecepter;

import com.zjf.common.hystrix.util.DCThreadLocalUtil;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *  测试类
 * 测试拦截器，自定义数据添加到ThreadLocalUtil中用于下面的父子线程数据传递
 * @author Harry
 */
public class DCInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        DCThreadLocalUtil.set("TestDC","Test Data Context");

        return true;
    }

}

