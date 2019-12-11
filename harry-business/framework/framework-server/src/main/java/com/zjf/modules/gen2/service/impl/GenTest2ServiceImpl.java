package com.zjf.modules.gen2.service.impl;

import com.zjf.common.core.utils.BeanCommonUtils;
import com.zjf.common.core.utils.Query;
import com.zjf.common.core.utils.vo.PageUtilsVO;
import com.zjf.common.mybatisplus.service.impl.ZjfBaseServiceImpl;
import com.zjf.modules.gen2.entity.GenTest2;
import com.zjf.modules.gen2.dao.GenTest2Dao;
import com.zjf.modules.gen2.service.GenTest2Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.stereotype.Service;
import com.google.common.collect.Lists;
import com.zjf.modules.gen2.vo.GenTest2VO;
import java.util.*;
/**
 * 测试用表2 服务实现类
 *
 * @author harry
 * @date 2019-09-24
 */
@Service
public class GenTest2ServiceImpl extends ZjfBaseServiceImpl<GenTest2Dao, GenTest2> implements GenTest2Service {
     @Override
     public PageUtilsVO<GenTest2VO> queryPage(Map<String, Object> params){
          QueryWrapper<GenTest2> queryWrapper = new QueryWrapper<>();
          IPage<GenTest2> pageList = this.page(new Query<GenTest2>().getPage(params), queryWrapper);
          List<GenTest2> records = pageList.getRecords();
          List<GenTest2VO> genTest1VOList = Lists.newArrayList();
          BeanCommonUtils.copyListProperties(records, genTest1VOList, GenTest2VO.class);
          return new PageUtilsVO<GenTest2VO>(genTest1VOList, pageList.getTotal(), pageList.getSize(), pageList.getCurrent());
     }
}
