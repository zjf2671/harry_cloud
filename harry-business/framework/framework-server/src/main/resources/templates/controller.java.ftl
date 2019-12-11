package ${package.Controller};


import ${package.Entity}.${entity};
import ${package.Service}.${table.serviceName};
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
import com.zjf.common.core.utils.BeanCommonUtils;
import com.zjf.common.core.utils.vo.PageUtilsVO;
import com.zjf.common.core.utils.vo.ResultVO;
import java.util.Map;
import com.zjf.${package.ModuleName}.vo.${entity}VO;
/**
 * ${table.comment!} 前端控制器
 *
 * @author ${author}
 * @date ${date}
 */
@Api(tags = "${table.comment!}接口")
@RestController
@RequestMapping("/${table.entityPath}")
public class ${table.controllerName}{

    @Autowired
    private ${entity}Service ${table.entityPath}Service;

    @ApiImplicitParams({
        @ApiImplicitParam(name = "currPage", dataType = "int", value = "当前页"),
        @ApiImplicitParam(name = "pageSize", dataType = "int", value = "每页显示数量")
    })
    @ApiOperation("分页查询")
    @GetMapping("/page")
    public ResultVO<PageUtilsVO<${entity}VO>> getPage(@ApiIgnore @RequestParam Map<String, Object> params){
        PageUtilsVO<${entity}VO> pageUtils = ${table.entityPath}Service.queryPage(params);
        return ResultVO.ok(pageUtils);
    }
    @ApiOperation("新增")
    @PostMapping("/create")
    public ResultVO<String> save(@ApiParam("数据对象")${entity}VO data){
        ${entity} ${table.entityPath} = new ${entity}();
        BeanCommonUtils.copyProperties(data, ${table.entityPath});
        ${table.entityPath}Service.save(${table.entityPath});
        return ResultVO.ok();
    }
    @ApiOperation("删除")
    @DeleteMapping("/delete/{id}")
    public ResultVO<String> delete(@ApiParam("数据对象id") @PathVariable("id")String id){
        ${table.entityPath}Service.removeById(id);
        return ResultVO.ok();
    }
    @ApiOperation("更新")
    @PutMapping("/update")
    public ResultVO<String> update(@ApiParam("数据对象")${entity}VO data){
        ${entity} ${table.entityPath} = new ${entity}();
        BeanCommonUtils.copyProperties(data, ${table.entityPath});
        ${table.entityPath}Service.updateById(${table.entityPath});
        return ResultVO.ok();
    }
    @ApiOperation("通过ID获取一条数据")
    @GetMapping("/read/{id}")
    public ResultVO<${entity}VO> getById(@ApiParam("数据对象id") @PathVariable("id")String id){
        ${entity} ${table.entityPath} = ${table.entityPath}Service.getById(id);
        ${entity}VO ${table.entityPath}VO = null;
        if(${table.entityPath} != null){
            ${table.entityPath}VO = new ${entity}VO()
            BeanCommonUtils.copyProperties(${table.entityPath}, ${table.entityPath}VO);
        }
        return ResultVO.ok(${table.entityPath}VO);
    }

}
