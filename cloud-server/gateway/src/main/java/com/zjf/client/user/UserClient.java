package com.zjf.client.user;

import com.zjf.api.user.UserApi;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * @Description 用户接口
 * @Author Harry
 * @Date 2019/4/23 18:06
 **/
@FeignClient("user")
public interface UserClient extends UserApi {
}
