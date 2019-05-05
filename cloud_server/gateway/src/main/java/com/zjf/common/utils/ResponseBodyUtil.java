package com.zjf.common.utils;

import com.alibaba.fastjson.JSON;
import com.netflix.zuul.context.RequestContext;
import com.zjf.common.user.ResultVO;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletResponse;

/**
 * @Description filter响应json
 * @Author Harry
 **/
public class ResponseBodyUtil {

    public static void responseBody(RequestContext requestContext, int code , String msg){
        HttpServletResponse response = requestContext.getResponse();
        response.setHeader("Content-Type", "application/json;charset=UTF-8");
        //终止转发，返回响应报文
        requestContext.setSendZuulResponse(false);
        requestContext.setResponseStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        requestContext.setResponseBody(JSON.toJSONString(ResultVO.error(code, msg)));
    }

}
