package com.zjf.modules.gen1.service.impl;

import com.zjf.common.mybatisplus.service.impl.ZjfBaseServiceImpl;
import com.zjf.common.redis.annotation.ServiceLock;
import com.zjf.modules.gen1.entity.GenTest1;
import com.zjf.modules.gen1.dao.GenTest1Dao;
import com.zjf.modules.gen1.service.GenTest1Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.stereotype.Service;
import com.zjf.common.core.utils.BeanCommonUtils;
import com.zjf.common.core.utils.Query;
import com.zjf.common.core.utils.vo.PageUtilsVO;
import com.google.common.collect.Lists;
import com.zjf.modules.gen1.vo.GenTest1VO;
import java.util.*;
/**
 * test1测试用表 服务实现类
 *
 * @author harry
 * @date 2019-09-30
 */
@Service
public class GenTest1ServiceImpl extends ZjfBaseServiceImpl<GenTest1Dao, GenTest1> implements GenTest1Service {

     @Override
     public PageUtilsVO<GenTest1VO> queryPage(Map<String, Object> params){
          QueryWrapper<GenTest1> queryWrapper = new QueryWrapper<>();
          IPage<GenTest1> pageList = this.page(new Query<GenTest1>().getPage(params), queryWrapper);
          List<GenTest1> records = pageList.getRecords();
          List<GenTest1VO> genTest1VOList = Lists.newArrayList();
          BeanCommonUtils.copyListProperties(records, genTest1VOList, GenTest1VO.class);
          return new PageUtilsVO<GenTest1VO>(genTest1VOList, pageList.getTotal(), pageList.getSize(), pageList.getCurrent());
     }

     @Override
     @ServiceLock
     public boolean saveGenTest1(String name) {

          GenTest1 benTest1 = this.getOne(new QueryWrapper<GenTest1>().eq("testName", name));
          if(benTest1 == null){
               benTest1 = new GenTest1();
               benTest1.setTestName(name);
               this.save(benTest1);
          }
          return true;
     }

}
