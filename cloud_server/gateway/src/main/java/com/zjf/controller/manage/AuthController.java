package com.zjf.controller.manage;

import com.alibaba.fastjson.JSON;
import com.zjf.client.user.UserClient;
import com.zjf.common.constants.ErrorCodeEnum;
import com.zjf.common.exception.BusinessException;
import com.zjf.common.user.ResultDTO;
import com.zjf.common.user.output.SysUserOutputDTO;
import com.zjf.common.utils.JwtUtils;
import com.zjf.common.utils.RedisUtils;
import com.zjf.common.utils.ResultVO;
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
    public ResultVO login(String username, String password){
        ResultDTO resultDTO = userClient.login(username, password);
        ResultVO resultVO = new ResultVO();
        if(resultDTO.getCode().equals(ResultDTO.OK_CODE)){
            Object data = resultDTO.getData();
            SysUserOutputDTO sysUserOutputDTO = JSON.parseObject(JSON.toJSONString(data),SysUserOutputDTO.class);
            //生成token
            String token = jwtUtils.generateToken(sysUserOutputDTO.getId().toString());
            //存入redis中
            redisUtils.set(token, sysUserOutputDTO, jwtUtils.getExpire());
            Map<String,Object> result = new HashMap<>(2);
            result.put("token", token);
            result.put("expire", jwtUtils.getExpire());
            resultVO.setData(result);
        }else{
            resultVO.setCode(resultDTO.getCode());
            resultVO.setMsg(resultDTO.getMsg());
        }

        return resultVO;
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
