package com.zjf.modules.gen2.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 测试用表2
 *
 * @author harry
 * @date 2019-09-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="GenTest2VO对象", description="测试用表2")
public class GenTest2VO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主健id
     */
    @ApiModelProperty(value = "主健id")
    private Integer id;

    /**
     * 测试字段名
     */
    @ApiModelProperty(value = "测试字段名")
    private String testName;


}
