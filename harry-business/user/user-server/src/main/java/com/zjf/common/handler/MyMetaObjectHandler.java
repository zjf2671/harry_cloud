package com.zjf.common.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.zjf.common.user.output.SysUserOutputDTO;
import com.zjf.common.utils.RedisUtils;
import com.zjf.common.utils.SpringContextUtils;
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
		RedisUtils redisUtils = SpringContextUtils.getBean("redisUtils", RedisUtils.class);
		HttpServletRequest request = SpringContextUtils.getHttpServletRequest();
		SysUserOutputDTO sysUserOutputDTO = null;
		if(request != null){
			String token = request.getHeader("token");
			if(StringUtils.isNotBlank(token)){
				sysUserOutputDTO = redisUtils.get(token, SysUserOutputDTO.class);
			}
		}

		if(sysUserOutputDTO != null){
			//只有本系统有才能存
			Object createId = getFieldValByName("createId", metaObject);
			if (null == createId) {
				setFieldValByName("createId", sysUserOutputDTO.getId().longValue(), metaObject);
			}
		}
		Object createTime = getFieldValByName("createTime", metaObject);
		
		if (null == createTime) {
			setFieldValByName("createTime", new Date(), metaObject);
		}
	}

	@Override
	public void updateFill(MetaObject metaObject) {
		RedisUtils redisUtils = SpringContextUtils.getBean("redisUtils", RedisUtils.class);
		HttpServletRequest request = SpringContextUtils.getHttpServletRequest();
		SysUserOutputDTO sysUserOutputDTO = null;
		if(request != null){
			String token = request.getHeader("token");
			if(StringUtils.isNotBlank(token)){
				// 从redis中获取当前登录用户
				sysUserOutputDTO = redisUtils.get(token, SysUserOutputDTO.class);
			}
		}
		if(sysUserOutputDTO != null){
			//只有本系统有才能存用户信息,
			setFieldValByName("updateId", sysUserOutputDTO.getId().longValue(), metaObject);
		}
		setFieldValByName("updateTime", new Date(), metaObject);
		
	}

}
