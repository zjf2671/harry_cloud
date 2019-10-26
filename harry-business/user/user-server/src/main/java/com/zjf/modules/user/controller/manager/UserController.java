package com.zjf.modules.user.controller.manager;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.zjf.common.user.output.SysUserOutputDTO;
import com.zjf.common.utils.BeanCommonUtils;
import com.zjf.common.utils.PageUtils;
import com.zjf.common.utils.RedisUtils;
import com.zjf.common.utils.ResultVO;
import com.zjf.modules.user.entity.SysUserEntity;
import com.zjf.modules.user.service.SysUserService;
import com.zjf.modules.user.vo.SysUserVO;
import io.swagger.annotations.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Map;

/**
 * 网关路由过来的请求
 *
 * @Author Harry
 * @Date 2019/4/25 14:13
 **/
@Api(tags = "用户系统中心-用户管理")
@RestController
public class UserController {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private RedisUtils redisUtils;

    /**
     * 分页查询
     * @param params
     * @return
     */
    @ApiOperation("用户列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "currPage", dataType = "int", value = "当前页"),
            @ApiImplicitParam(name = "pageSize", dataType = "int", value = "每页显示数量"),
    })
    @ApiResponses(value={
            @ApiResponse(code=200, message="OK"),
            @ApiResponse(code=500, message="后台异常")
    })
    @GetMapping("/queryPageList")
    public ResultVO<PageUtils<SysUserVO>> getPageList(@ApiIgnore @RequestParam Map<String, Object> params){
        PageUtils<SysUserVO> pageUtils = sysUserService.queryPage(params);
        return ResultVO.ok(pageUtils);
    }

    /**
     * 查询用户
     *
     * @return
     */
    @GetMapping("/userInfo")
    public ResultVO<SysUserVO> getUserInfo() {
        String token = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getHeader("token");
        if (StringUtils.isBlank(token)) {
            token = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getParameter("token");
        }
        SysUserOutputDTO sysUserOutputDTO = redisUtils.get(token, SysUserOutputDTO.class);
        SysUserVO sysUserVO = new SysUserVO();
        BeanCommonUtils.copyProperties(sysUserOutputDTO,sysUserVO);
        return ResultVO.ok(sysUserVO);
    }

    /**
     * 新增用户
     *
     * @return
     */
    @PostMapping("/userInfo")
    public ResultVO<String> saveUser() {
        SysUserEntity entity = new SysUserEntity();
        entity.setUsername("009");
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
        entity.setUsername("009");
        sysUserService.update(entity, new UpdateWrapper<SysUserEntity>().eq("username", entity.getUsername()));
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
        entity.setUsername("009");
        sysUserService.remove(new UpdateWrapper<SysUserEntity>().eq("username", entity.getUsername()));
        return ResultVO.ok();
    }

}
