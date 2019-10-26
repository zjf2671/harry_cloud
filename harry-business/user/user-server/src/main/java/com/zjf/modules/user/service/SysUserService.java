package com.zjf.modules.user.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.zjf.common.user.output.SysUserOutputDTO;
import com.zjf.common.utils.PageUtils;
import com.zjf.modules.user.entity.SysUserEntity;

import java.util.Map;


/**
 * 系统用户
 * 
 * @author harry.zhang
 * 
 */
public interface SysUserService extends IService<SysUserEntity> {

	/**
	 * 根据用户名，查询系统用户
	 */
	SysUserOutputDTO queryByUserName(String username, String password);


	/**
	 * 分页查询
	 * @param params
	 * @return
	 */
	PageUtils queryPage(Map<String, Object> params);

}