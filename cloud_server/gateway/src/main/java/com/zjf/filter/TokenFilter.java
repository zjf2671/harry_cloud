package com.zjf.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import com.zjf.common.exception.BusinessException;
import com.zjf.common.exception.GatewayException;
import com.zjf.common.utils.JwtUtils;
import com.zjf.common.utils.ResponseBodyUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_DECORATION_FILTER_ORDER;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

/**
 * token过滤
 * @author Harry
 */
@Component
@Slf4j
public class TokenFilter extends ZuulFilter {

    @Autowired
    private JwtUtils jwtUtils;

    /**
     * 指定过滤器类型
     * @return
     */
    @Override
    public String filterType() {
        return PRE_TYPE;
    }

    /**
     * 指定其在过滤器链上所处的顺序,数字越小，优先级越高
     * @return
     */
    @Override
    public int filterOrder() {
        return PRE_DECORATION_FILTER_ORDER - 1;
    }
    /**
     * 是否启用这个filter
     * @return
     */
    @Override
    public boolean shouldFilter() {
        RequestContext requestContext = RequestContext.getCurrentContext();
        RequestContext ctx = RequestContext.getCurrentContext();
        //前置的filter终止，本filter不执行
        if(!ctx.sendZuulResponse()){
            return false;
        }

//        HttpServletRequest request = requestContext.getRequest();
//        String requestURI = request.getRequestURI();
//        log.info("requestURI====>{}", requestURI);
//        if(requestURI.contains("/auth/login")){
//            return false;
//        }

        return true;
    }

    @Override
    public Object run() throws ZuulException {
        log.info("---------------------正常执行TokenFilter---------------------");
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        //这里从header里获取, 也可以从url参数里获取
        String token = request.getHeader("token");
        if (StringUtils.isBlank(token)) {
            token = request.getParameter("token");
        }
        if (StringUtils.isBlank(token)) {
            ResponseBodyUtil.responseBody(requestContext, HttpStatus.UNAUTHORIZED.value(),"无  token");
        }else{
            try {
                jwtUtils.getClaimByToken(token);
            } catch (BusinessException e) {
                throw new GatewayException(e.getMsg());
            }
        }

        return null;

    }


}