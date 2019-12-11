package ${package.Service};

import ${package.Entity}.${entity};
import ${superServiceClassPackage};
import com.zjf.common.core.utils.vo.PageUtilsVO;
import java.util.*;
import com.zjf.${package.ModuleName}.vo.${entity}VO;
/**
 * ${table.comment!} 服务类
 *
 * @author ${author}
 * @date ${date}
 */
<#if kotlin>
interface ${table.serviceName} : ${superServiceClass}<${entity}>
<#else>
public interface ${table.serviceName} extends ${superServiceClass}<${entity}> {

     /**
      * 分页查询
      *
      * @param params
      * @return
      */
    PageUtilsVO<${entity}VO> queryPage(Map<String, Object> params);

}
</#if>
