package com.zjf.modules.gen2.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 测试用表2
 *
 * @author harry
 * @date 2019-09-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("gen_test2")
public class GenTest2 implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主健id
     */
    private Integer id;

    /**
     * 测试字段名
     */
    private String testName;


}
