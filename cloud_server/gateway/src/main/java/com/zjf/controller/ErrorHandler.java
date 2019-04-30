package com.zjf.controller;

import com.zjf.common.user.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * filter异常接收器
 * @author Harry
 */
@RestController
@Slf4j
public class ErrorHandler {

    @GetMapping(value = "/error")
    public ResultVO error(HttpServletRequest request) {
        ResultVO r = new ResultVO();
        String message = request.getAttribute("javax.servlet.error.message").toString();
        String code = request.getAttribute("javax.servlet.error.status_code").toString();
        Exception exception = (Exception)request.getAttribute("javax.servlet.error.exception");
        r.setCode(code != null ? Integer.parseInt(code):null);
        r.setMsg(message);
        log.error("filter全局异常捕获", exception);
        return r;
    }

}