package com.zjf.fallback;

import com.netflix.hystrix.exception.HystrixTimeoutException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.zuul.filters.route.FallbackProvider;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author harry
 * 模块异常回调
 */
@Slf4j
@Component
public class DefaultFallbackProvider implements FallbackProvider {

    private static final String SERVICE_DISABLE = "服务模块不可用";

    @Override
    public String getRoute() {
        return "*";
    }

    @Override
    public ClientHttpResponse fallbackResponse(String route, Throwable cause) {
        log.error("调用:{} 服务异常",route, cause);
        //标记不同的异常为不同的http状态值
        if (cause instanceof HystrixTimeoutException) {
            return response(route, HttpStatus.GATEWAY_TIMEOUT);
        } else {
            //可继续添加自定义异常类
            return response(route, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * 处理逻辑
     * @param route
     * @param status
     * @return
     */
    private ClientHttpResponse response(String route, final HttpStatus status) {
        return new ClientHttpResponse() {
            @Override
            public HttpStatus getStatusCode() throws IOException {
                return status;
            }

            @Override
            public int getRawStatusCode() throws IOException {
                return status.value();
            }

            @Override
            public String getStatusText() throws IOException {
                return status.getReasonPhrase();
            }

            @Override
            public void close() {
            }

            @Override
            public InputStream getBody() throws IOException {
                return new ByteArrayInputStream(("{\"code\":\""+status.value()+"\",\"msg\":\""+ route + SERVICE_DISABLE+"\"}").getBytes());
            }

            @Override
            public HttpHeaders getHeaders() {
                HttpHeaders headers = new HttpHeaders();
                headers.setContentType(MediaType.APPLICATION_JSON);
                return headers;
            }
        };
    }

}
