package com.zjf.common.core.interceptor;

import com.zjf.common.core.constants.CommonConstant;
import com.zjf.common.core.constants.Constant;
import com.zjf.common.core.utils.SpringContextUtils;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.Map.Entry;

/**
 * 用于把请求过的header参数带到Feign调用header里面
 * @author Harry
 */
@Configuration
@Slf4j
public class FeignHeadersInterceptor implements RequestInterceptor {

    protected List<String> requestHeaders = new ArrayList<>();

    @PostConstruct
    public void initialize() {
        requestHeaders.add(Constant.TOKEN);
        requestHeaders.add(CommonConstant.ZJF_VERSION);
    }

    @Override
    public void apply(RequestTemplate template) {
        HttpServletRequest request = SpringContextUtils.getHttpServletRequest();
        if (Objects.isNull(request)) {
            return;
        }
        Map<String, String> headers = getHeaders(request);
        if (headers.size() > 0) {
            Iterator<Entry<String, String>> iterator = headers.entrySet().iterator();
            while (iterator.hasNext()) {
                Entry<String, String> entry = iterator.next();
                // 把请求过来的header请求头 中的token和PCid原样设置到feign请求头中
                for (String requestHeader : requestHeaders) {
                    if(requestHeader.equals(entry.getKey())){
                        template.header(entry.getKey(), entry.getValue());
                    }
                }


            }
        }
    }

    private Map<String, String> getHeaders(HttpServletRequest request) {
        Map<String, String> map = new LinkedHashMap<>();
        Enumeration<String> enums = request.getHeaderNames();
        while (enums.hasMoreElements()) {
            String key = enums.nextElement();
            String value = request.getHeader(key);
            map.put(key, value);
        }

        return map;
    }
}