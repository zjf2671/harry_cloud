package com.zjf.modules.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.zjf.common.constants.ErrorCodeEnum;
import com.zjf.common.exception.BusinessException;
import com.zjf.common.utils.BeanCommonUtils;
import com.zjf.common.user.output.SysUserOutputDTO;
import com.zjf.common.utils.PageUtils;
import com.zjf.common.utils.Query;
import com.zjf.modules.user.dao.SysUserDao;
import com.zjf.modules.user.entity.SysUserEntity;
import com.zjf.modules.user.service.SysUserService;
import com.zjf.modules.user.vo.SysUserVO;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


/**
 * 系统用户
 * 
 * @author harry.zhang
 * 
 */
@Service("sysUserService")
public class SysUserServiceImpl extends ServiceImpl<SysUserDao, SysUserEntity> implements SysUserService {

	@Override
	public PageUtils<SysUserVO> queryPage(Map<String, Object> params) {
		String username = (String)params.get("username");
		IPage<SysUserEntity> pageList = this.page(new Query<SysUserEntity>().getPage(params), new QueryWrapper<SysUserEntity>().
				eq(StringUtils.isNotBlank(username), "username", username));
		List<SysUserEntity> entityList = pageList.getRecords();
		List<SysUserVO> voList = Lists.newArrayList();
		BeanCommonUtils.copyListProperties(entityList, voList, SysUserVO.class);
		return new PageUtils(voList, pageList.getTotal(), pageList.getSize(), pageList.getCurrent());
	}
	@Override
	public SysUserOutputDTO queryByUserName(String username,String password) {
		SysUserEntity sysUserEntity = baseMapper.queryByUserName(username);
		//账号不存在、密码错误
		if(sysUserEntity == null || !sysUserEntity.getPassword().equals(new Sha256Hash(password, sysUserEntity.getSalt()).toHex())) {
			throw new BusinessException(ErrorCodeEnum.USER_PASSWORD_ERROR.getValue(), ErrorCodeEnum.USER_PASSWORD_ERROR.getCode());
		}

		//账号锁定
		if(sysUserEntity.getStatus() == 0){
			throw new BusinessException(ErrorCodeEnum.USER_LOCK.getValue(), ErrorCodeEnum.USER_LOCK.getCode());
		}
		SysUserOutputDTO sysUserOutputDTO = new SysUserOutputDTO();
		if(sysUserEntity!=null){
			BeanCommonUtils.copyProperties(sysUserEntity,sysUserOutputDTO);
		}
		return sysUserOutputDTO;
	}


}
