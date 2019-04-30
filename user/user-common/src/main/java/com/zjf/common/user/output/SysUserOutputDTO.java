package com.zjf.common.user.output;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Description 用户DTO对象
 * @Author Harry
 * @Date 2019/4/19 12:49
 **/
@Data
public class SysUserOutputDTO implements Serializable{

    /**
     * 用户ID
     */
    private Long id;

    /**
     * 操作员工号
     */
    private String userNo;
    /**
     * 操作员姓名
     */
    private String username;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 状态  0：禁用   1：正常
     */
    private Integer status;

    /**
     * 角色ID列表
     */
    private List<Long> roleIdList;

    /**
     * 创建者ID
     */
    private Long createId;

    /**
     * 创建时间
     */
    private Date createTime;

}
