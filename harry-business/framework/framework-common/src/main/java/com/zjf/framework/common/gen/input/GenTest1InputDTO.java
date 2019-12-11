package com.zjf.framework.common.gen.input;

import lombok.Data;

import java.io.Serializable;

/**
 * @Description inputDTO
 * @Author Harry
 * @Date 2019/9/23 14:06
 **/
@Data
public class GenTest1InputDTO implements Serializable {


    /**
     * 主健id
     */
    private Integer id;

    /**
     * 测试字段值
     */
    private String testName;

}
