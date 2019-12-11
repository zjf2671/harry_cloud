package com.zjf.common.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.zjf.common.core.constants.Constant;
import com.zjf.common.core.utils.SpringContextUtils;
import com.zjf.common.redis.util.RedisUtils;
import com.zjf.common.user.output.SysUserOutputDTO;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @Description: 注入公共字段自动填充
 * @author harry.zhang
 * @version 1.0
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

	@Override
	public void insertFill(MetaObject metaObject) {
		RedisUtils redisUtils = SpringContextUtils.getBean(Constant.REDIS_UTILS_INSTANCE, RedisUtils.class);
		HttpServletRequest request = SpringContextUtils.getHttpServletRequest();
		SysUserOutputDTO sysUserOutputDTO = null;
		if(request != null){
			String token = request.getHeader(Constant.TOKEN);
			if(StringUtils.isNotBlank(token)){
				sysUserOutputDTO = redisUtils.get(token, SysUserOutputDTO.class);
			}
		}

		if(sysUserOutputDTO != null){
			//只有本系统有才能存
			Object createId = getFieldValByName(Constant.CREATE_ID, metaObject);
			if (null == createId) {
				setFieldValByName(Constant.CREATE_ID, sysUserOutputDTO.getId(), metaObject);
			}
		}
		Object createTime = getFieldValByName(Constant.CREATE_TIME, metaObject);
		
		if (null == createTime) {
			setFieldValByName(Constant.CREATE_TIME, new Date(), metaObject);
		}
	}

	@Override
	public void updateFill(MetaObject metaObject) {
		RedisUtils redisUtils = SpringContextUtils.getBean(Constant.REDIS_UTILS_INSTANCE, RedisUtils.class);
		HttpServletRequest request = SpringContextUtils.getHttpServletRequest();
		SysUserOutputDTO sysUserOutputDTO = null;
		if(request != null){
			String token = request.getHeader(Constant.TOKEN);
			if(StringUtils.isNotBlank(token)){
				// 从redis中获取当前登录用户
				sysUserOutputDTO = redisUtils.get(token, SysUserOutputDTO.class);
			}
		}
		if(sysUserOutputDTO != null){
			//只有本系统有才能存用户信息,
			setFieldValByName(Constant.UPDATE_ID, sysUserOutputDTO.getId(), metaObject);
		}
		setFieldValByName(Constant.UPDATE_TIME, new Date(), metaObject);
		
	}

}
