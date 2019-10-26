package com.zjf.modules.user.controller.server;


import com.zjf.common.user.ResultDTO;
import com.zjf.common.user.output.SysUserOutputDTO;
import com.zjf.common.utils.BeanCommonUtils;
import com.zjf.common.utils.RedisUtils;
import com.zjf.modules.user.entity.SysUserEntity;
import com.zjf.modules.user.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
* 登录相关 api的具体实现，即为feign的服务接口
 * 
 * @author harry.zhang
 */
@RestController
public class SysLoginController {

	@Autowired
	private SysUserService sysUserService;

	@Autowired
	private RedisUtils redisUtils;

	/**
	 * 登录
	 */
	@PostMapping("/login")
	public ResultDTO login(String username, String password) {
		ResultDTO<SysUserOutputDTO> r = new ResultDTO();
		//用户信息
		SysUserOutputDTO user = sysUserService.queryByUserName(username, password);

		return ResultDTO.ok(user);
	}

	@GetMapping("/getUserInfoById")
	public ResultDTO<SysUserOutputDTO> getUserInfo(Long id) {
		SysUserEntity userEntity = sysUserService.getById(id);
		SysUserOutputDTO sysUserOutputDTO = new SysUserOutputDTO();
		BeanCommonUtils.copyProperties(userEntity,sysUserOutputDTO);
		return ResultDTO.ok(sysUserOutputDTO);
	}

	@PostMapping("/logout")
	public ResultDTO<String> logout(String token){
		ResultDTO<String> resultVO = new ResultDTO<>();
		SysUserOutputDTO sysUserOutputDTO = redisUtils.get(token, SysUserOutputDTO.class);
		//todo
		return resultVO;
	}
}
