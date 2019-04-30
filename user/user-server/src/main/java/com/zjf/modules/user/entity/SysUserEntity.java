package com.zjf.modules.user.entity;


import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统用户
 * 
 * @author harry.zhang
 * 
 */
@TableName("sys_user")
@Data
public class SysUserEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
	 * 用户ID
	 */
	@TableId
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
	 * 密码
	 */
	private String password;

	/**
	 * 盐
	 */
	private String salt;

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
	 * 创建者ID
	 */
	@TableField(fill = FieldFill.INSERT)
	private Long createId;

	/**
	 * 创建时间
	 */
	@TableField(fill = FieldFill.INSERT)
	private Date createTime;

	/**
	 * 更新者ID
	 */
	@TableField(fill = FieldFill.UPDATE)
	private Long updateId;

	/**
	 * 更新者时间
	 */
	@TableField(fill = FieldFill.UPDATE)
	private Date updateTime;

}
