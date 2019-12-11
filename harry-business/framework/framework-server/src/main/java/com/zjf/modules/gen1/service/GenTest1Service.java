package com.zjf.modules.gen1.service;

import com.zjf.common.mybatisplus.service.ZjfBaseService;
import com.zjf.modules.gen1.entity.GenTest1;
import com.zjf.common.core.utils.vo.PageUtilsVO;
import java.util.*;
import com.zjf.modules.gen1.vo.GenTest1VO;
/**
 * test1测试用表 服务类
 *
 * @author harry
 * @date 2019-09-30
 */
public interface GenTest1Service extends ZjfBaseService<GenTest1> {

     /**
      * 分页查询
      *
      * @param params
      * @return
      */
    PageUtilsVO<GenTest1VO> queryPage(Map<String, Object> params);

    boolean saveGenTest1(String name);
}
