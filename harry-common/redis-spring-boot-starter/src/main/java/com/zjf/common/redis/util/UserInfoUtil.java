package com.zjf.common.redis.util;

import com.zjf.common.core.constants.Constant;
import com.zjf.common.core.exception.BusinessException;
import com.zjf.common.core.utils.SpringContextUtils;
import com.zjf.common.user.output.SysUserOutputDTO;
import org.apache.commons.lang.StringUtils;

/**
 * @Description 获取当前用户信息
 * @Author Harry
 **/
public class UserInfoUtil {

    private UserInfoUtil(){}

    /**
     * 固定code值，不允许修改
     */
    private static final int TOKEN_EXPIRED_CODE = 100001;
    private static final int NO_TOKEN_CODE = 100002;

    public static SysUserOutputDTO getCurrUserInfo() {

        String token = SpringContextUtils.getHttpServletRequest().getHeader(Constant.TOKEN);
        if (StringUtils.isBlank(token)) {
            token = SpringContextUtils.getHttpServletRequest().getParameter(Constant.TOKEN);
        }
        if(StringUtils.isBlank(token)){
            throw new BusinessException("无 token, 无法获取当前登录用户信息", NO_TOKEN_CODE);
        }

        RedisUtils redisUtils = SpringContextUtils.getBean(Constant.REDIS_UTILS_INSTANCE, RedisUtils.class);
        SysUserOutputDTO sysUserOutputDTO = redisUtils.get(token, SysUserOutputDTO.class);
        if(sysUserOutputDTO == null){
            throw new BusinessException("token 无效, 无法获取当前登录用户信息", TOKEN_EXPIRED_CODE);
        }
        return sysUserOutputDTO;
    }


}
