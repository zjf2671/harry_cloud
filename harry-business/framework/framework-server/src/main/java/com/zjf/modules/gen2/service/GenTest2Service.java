package com.zjf.modules.gen2.service;

import com.zjf.common.core.utils.vo.PageUtilsVO;
import com.zjf.common.mybatisplus.service.ZjfBaseService;
import com.zjf.modules.gen2.entity.GenTest2;

import java.util.*;
import com.zjf.modules.gen2.vo.GenTest2VO;
/**
 * 测试用表2 服务类
 *
 * @author harry
 * @date 2019-09-24
 */
public interface GenTest2Service extends ZjfBaseService<GenTest2> {
     /**
      * 分页查询
      *
      * @param params
      * @return
      */
    PageUtilsVO<GenTest2VO> queryPage(Map<String, Object> params);
}
