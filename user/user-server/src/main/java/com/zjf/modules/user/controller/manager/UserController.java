package com.zjf.modules.user.controller.manager;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.zjf.common.user.ResultVO;
import com.zjf.common.user.output.SysUserOutputDTO;
import com.zjf.common.utils.RedisUtils;
import com.zjf.modules.user.entity.SysUserEntity;
import com.zjf.modules.user.service.SysUserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * 网关路由过来的请求
 *
 * @Author Harry
 * @Date 2019/4/25 14:13
 **/
@RestController
public class UserController {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private RedisUtils redisUtils;

    /**
     * 查询用户
     *
     * @return
     */
    @GetMapping("/userInfo")
    public ResultVO<SysUserOutputDTO> getUserInfo() {
        String token = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getHeader("token");
        if (StringUtils.isBlank(token)) {
            token = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getParameter("token");
        }
        SysUserOutputDTO sysUserOutputDTO = redisUtils.get(token, SysUserOutputDTO.class);

        return ResultVO.ok(sysUserOutputDTO);
    }

    /**
     * 新增用户
     *
     * @return
     */
    @PostMapping("/userInfo")
    public ResultVO<String> saveUser() {
        SysUserEntity entity = new SysUserEntity();
        entity.setUserNo("009");
        boolean save = sysUserService.save(entity);
        return ResultVO.ok();
    }

    /**
     * 修改用户
     *
     * @return
     */
    @PutMapping("/userInfo")
    public ResultVO<String> updateUser() {
        SysUserEntity entity = new SysUserEntity();
        entity.setUserNo("009");
        entity.setUsername("aaaa");
        sysUserService.update(entity, new UpdateWrapper<SysUserEntity>().eq("userNo", entity.getUserNo()));
        return ResultVO.ok();
    }

    /**
     * 删除用户
     *
     * @return
     */
    @DeleteMapping("/userInfo")
    public ResultVO<String> deleteUser() {
        SysUserEntity entity = new SysUserEntity();
        entity.setUserNo("009");
        sysUserService.remove(new UpdateWrapper<SysUserEntity>().eq("userNo", entity.getUserNo()));
        return ResultVO.ok();
    }

}
