package com.zjf.modules.gen1.vo;

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
 * test1测试用表 VO对象
 *
 * @author harry
 * @date 2019-09-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel(value="GenTest1VO对象", description="test1测试用表")
public class GenTest1VO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主健id
     */
    @ApiModelProperty(value = "主健id")
    private Integer id;

    /**
     * 测试字段值
     */
    @ApiModelProperty(value = "测试字段值")
    private String testName;


}
