package ${package.ServiceImpl};

import ${package.Entity}.${entity};
import ${package.Mapper}.${table.mapperName};
import ${package.Service}.${table.serviceName};
import ${superServiceImplClassPackage};
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.stereotype.Service;
import com.zjf.common.core.utils.BeanCommonUtils;
import com.zjf.common.core.utils.Query;
import com.zjf.common.core.utils.vo.PageUtilsVO;
import com.google.common.collect.Lists;
import com.zjf.${package.ModuleName}.vo.${entity}VO;
import java.util.*;
/**
 * ${table.comment!} 服务实现类
 *
 * @author ${author}
 * @date ${date}
 */
@Service
<#if kotlin>
open class ${table.serviceImplName} : ${superServiceImplClass}<${table.mapperName}, ${entity}>(), ${table.serviceName} {

}
<#else>
public class ${table.serviceImplName} extends ${superServiceImplClass}<${table.mapperName}, ${entity}> implements ${table.serviceName} {

     @Override
     public PageUtilsVO<${entity}VO> queryPage(Map<String, Object> params){
          QueryWrapper<${entity}> queryWrapper = new QueryWrapper<>();
          IPage<${entity}> pageList = this.page(new Query<${entity}>().getPage(params), queryWrapper);
          List<${entity}> records = pageList.getRecords();
          List<${entity}VO> genTest1VOList = Lists.newArrayList();
          BeanCommonUtils.copyListProperties(records, genTest1VOList, ${entity}VO.class);
          return new PageUtilsVO<${entity}VO>(genTest1VOList, pageList.getTotal(), pageList.getSize(), pageList.getCurrent());
     }

}
</#if>
