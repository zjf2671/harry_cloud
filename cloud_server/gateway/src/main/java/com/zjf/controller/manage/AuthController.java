package com.zjf.controller.manage;

import com.alibaba.fastjson.JSON;
import com.zjf.client.user.UserClient;
import com.zjf.common.constants.ErrorCodeEnum;
import com.zjf.common.exception.BusinessException;
import com.zjf.common.user.ResultDTO;
import com.zjf.common.user.output.SysUserOutputDTO;
import com.zjf.common.utils.JwtUtils;
import com.zjf.common.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Description 授权中心
 * @Author Harry
 * @Date 2019/4/25 18:01
 **/
@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthController {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private UserClient userClient;

    @PostMapping("/login")
    public ResultDTO login(String username, String password){
        ResultDTO resultVO = userClient.login(username, password);
        ResultDTO resultVOr = new ResultDTO();
        if(resultVO.getCode().equals(ResultDTO.OK_CODE)){
            Object data = resultVO.getData();
            SysUserOutputDTO sysUserOutputDTO = JSON.parseObject(JSON.toJSONString(data),SysUserOutputDTO.class);
            //生成token
            String token = jwtUtils.generateToken(sysUserOutputDTO.getId().toString());
            //存入redis中
            redisUtils.set(token, sysUserOutputDTO, jwtUtils.getExpire());
            Map<String,Object> result = new HashMap<>(2);
            result.put("token", token);
            result.put("expire", jwtUtils.getExpire());
            resultVOr.setData(result);
        }else{
            resultVOr.setCode(resultVO.getCode());
            resultVOr.setMsg(resultVO.getMsg());
        }

        return resultVOr;
    }

    /**
     * 登出
     */
    @PostMapping(value = "/logout")
    public ResultDTO logout(@RequestHeader String token) {
        if(StringUtils.isBlank(token)){
            throw new BusinessException(ErrorCodeEnum.NO_TOKEN.getValue(),ErrorCodeEnum.NO_TOKEN.getCode());
        }
        ResultDTO<String> logout = userClient.logout(token);
        if(logout.getCode().equals(ResultDTO.OK_CODE)){
            redisUtils.delete(token);
        }
        return logout;
    }
}
