package com.zjf.controller;

import com.netflix.zuul.exception.ZuulException;
import com.zjf.common.utils.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.error.AbstractErrorController;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * filter异常接收器
 * @author Harry
 */
@RestController
@Slf4j
@RequestMapping("/error")
public class ErrorHandler extends AbstractErrorController{

    @Autowired
    public ErrorHandler(ErrorAttributes errorAttributes) {
        super(errorAttributes);
    }
    /**
     * @param request
     * @param response
     * @return
     */
    @RequestMapping()
    public ResultVO error(HttpServletRequest request, HttpServletResponse response) {
        ResultVO r = new ResultVO();
        String message = request.getAttribute("javax.servlet.error.message")!=null ? request.getAttribute("javax.servlet.error.message").toString():null;
        String code = request.getAttribute("javax.servlet.error.status_code")!=null ? request.getAttribute("javax.servlet.error.status_code").toString():null;
        Throwable throwable = (Throwable)request.getAttribute("javax.servlet.error.exception");
        if(throwable instanceof ZuulException){
            ZuulException zuulException = (ZuulException)throwable;
            r.setCode(code != null ? Integer.parseInt(code): zuulException.nStatusCode);
            r.setMsg(message != null ? message: zuulException.getMessage());
        }else {
            r.setCode(code != null ? Integer.parseInt(code): -1);
            r.setMsg(message != null ? message: throwable.getMessage());
        }
        log.error("filter全局异常捕获 status:{}", r.getCode(), throwable);
        //用于解决自定义异常code码浏览器不识别问题
        response.setStatus(HttpStatus.OK.value());
        return r;
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}