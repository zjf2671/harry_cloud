package com.zjf.common.hystrix.fegin;

import com.zjf.common.hystrix.util.DCThreadLocalUtil;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * 测试类
 * 1、继承RequestInterceptor，把header设置到请求中,注意header的key若是大写时，请求中会被转为小写
 * @author Harry
 */
@Slf4j
@Configuration
public class FeignConfigTest implements RequestInterceptor {

    @Override
    public void apply(RequestTemplate requestTemplate) {
//        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        //当主线程的请求执行完毕后，Servlet会被销毁，因此在这里需要做判空
//        if (attributes != null) {
//            HttpServletRequest request = attributes.getRequest();
//
//            Enumeration<String> headerNames = request.getHeaderNames();
//
//            while (headerNames.hasMoreElements()) {
//                String name = headerNames.nextElement();
//                //不能把所有消息头都传递下去，否则会引起其他异常；header的name都是小写
//                if (name.equals("feignheader")) {
//                    requestTemplate.header(name,request.getHeader(name));
//                }
//            }
//        }

        Map<String, Object> dataContext = DCThreadLocalUtil.get();
        for (String key : dataContext.keySet()) {
            log.info("--从ThreadLocal获取消息头传递到下一个服务：key-[{}],value-[{}]",key,dataContext.get(key));
            requestTemplate.header(key, dataContext.get(key).toString());
        }

    }
}

