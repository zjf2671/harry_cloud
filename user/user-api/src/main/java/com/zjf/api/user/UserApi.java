package com.zjf.api.user;

import com.zjf.common.user.ResultVO;
import com.zjf.common.user.output.SysUserOutputDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Description 用户服务
 * @Author Harry
 * @Date 2019/4/18 18:45
 **/
public interface UserApi {

    /**
     * 登录接口
     * @param username
     * @param password
     * @return
     */
    @PostMapping("/login")
    public ResultVO<SysUserOutputDTO> login(@RequestParam("username") String username, @RequestParam("password") String password);

    /**
     * 通过用户id去取用户的信息
     * @param id
     * @return
     */
    @GetMapping("/getUserInfoById")
    public ResultVO<SysUserOutputDTO> getUserInfo(@RequestParam("id") Long id);

    /**
     * 用户登出
     * @param token
     * @return
     */
    @PostMapping("/logout")
    public ResultVO<String> logout(@RequestParam("token")String token);

}
