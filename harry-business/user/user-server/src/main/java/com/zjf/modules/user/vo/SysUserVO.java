package com.zjf.modules.user.vo;


import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统用户
 * 
 * @author harry.zhang
 * 
 */
@Data
@ApiModel(value="SysUserVO对象", description="系统用户对象")
public class SysUserVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 用户ID
	 */
	@ApiModelProperty("主键id")
	private Long id;

	/**
	 * 用户名
	 */
	@ApiModelProperty("用户名")
	private String username;

	/**
	 * 邮箱
	 */
	@ApiModelProperty("邮箱")
	private String email;

	/**
	 * 手机号
	 */
	@ApiModelProperty("手机号")
	private String mobile;

	/**
	 * 状态  0：禁用   1：正常
	 */
	@ApiModelProperty("状态  0：禁用   1：正常")
	private Integer status;
	

	/**
	 * 创建者ID
	 */
	@ApiModelProperty("创建者ID")
	private Long createId;

	/**
	 * 创建时间
	 */
	@ApiModelProperty("创建时间")
	private Date createTime;

	/**
	 * 更新者ID
	 */
	@ApiModelProperty("更新者ID")
	private Long updateId;

	/**
	 * 更新者时间
	 */
	@ApiModelProperty("更新者时间")
	private Date updateTime;

}
