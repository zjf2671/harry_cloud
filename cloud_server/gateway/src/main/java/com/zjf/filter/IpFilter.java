package com.zjf.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.zjf.common.utils.ResponseBodyUtil;
import com.zjf.common.utils.IPUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_DECORATION_FILTER_ORDER;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

/**
 * ip白名单
 * @author Harry
 */
@Component
@Slf4j
public class IpFilter extends ZuulFilter {
 
    @Override
    public String filterType() {
        return PRE_TYPE;
    }
 
    @Override
    public int filterOrder() {
        return PRE_DECORATION_FILTER_ORDER - 5;
    }
 
    @Override
    public boolean shouldFilter() {
        RequestContext ctx= RequestContext.getCurrentContext();
        if(!ctx.sendZuulResponse()){
            return false;
        }
        return true;
    }
 
    @Override
    public Object run(){
        RequestContext ctx= RequestContext.getCurrentContext();
        HttpServletRequest req=ctx.getRequest();
        String ipAddr = IPUtils.getIpAddr(req);
        log.info("请求IP地址为：[{}]",ipAddr);
       //配置本地IP白名单，生产环境可放入数据库或者redis中
        List<String> ips = new ArrayList<>();
        ips.add("0:0:0:0:0:0:0:1");

        if(!ips.contains(ipAddr)){
            log.info("IP地址校验不通过！！！");
            ResponseBodyUtil.responseBody(ctx, HttpStatus.UNAUTHORIZED.value(),"IpAddr is forbidden!");
        }else{
            log.info("IP校验通过。");
        }
        return null;
    }

}