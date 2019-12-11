package com.zjf.framework.common.gen.output;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description outputDTO
 * @Author Harry
 * @Date 2019/9/23 14:08
 **/
@Data
public class GenTest1OutputDTO implements Serializable {

    /**
     * 主健id
     */
    private Integer id;

    /**
     * 测试字段值
     */
    private String testName;


}
