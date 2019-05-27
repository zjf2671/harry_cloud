package com.zjf.controller;

import com.zjf.common.user.ResultDTO;
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
    public ResultDTO error(HttpServletRequest request, HttpServletResponse response) {
        ResultDTO r = new ResultDTO();
        String message = request.getAttribute("javax.servlet.error.message").toString();
        String code = request.getAttribute("javax.servlet.error.status_code").toString();
        Exception exception = (Exception)request.getAttribute("javax.servlet.error.exception");
        r.setCode(code != null ? Integer.parseInt(code):null);
        r.setMsg(message);
        log.error("filter全局异常捕获 status:{}", code, exception);
        //用于解决自定义异常code码浏览器不识别问题
        response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        return r;
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}