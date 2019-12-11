package com.zjf.modules.gen1.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * test1测试用表实体对象
 *
 * @author harry
 * @date 2019-09-30
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("gen_test1")
public class GenTest1 implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主健id
     */
    private Integer id;

    /**
     * 测试字段值
     */
    private String testName;


}
