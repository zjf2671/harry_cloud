package com.zjf.modules.gen1.controller.manager;


import com.zjf.modules.gen1.entity.GenTest1;
import com.zjf.modules.gen1.service.GenTest1Service;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;
import com.zjf.common.core.utils.BeanCommonUtils;
import com.zjf.common.core.utils.vo.PageUtilsVO;
import com.zjf.common.core.utils.vo.ResultVO;
import java.util.Map;
import com.zjf.modules.gen1.vo.GenTest1VO;
/**
 * test1测试用表 前端控制器
 *
 * @author harry
 * @date 2019-09-30
 */
@Api(tags = "test1测试用表")
@RestController
@RequestMapping("genTest1")
public class GenTest1Controller{

    @Autowired
    private GenTest1Service genTest1Service;

    @ApiImplicitParams({
        @ApiImplicitParam(name = "currPage", dataType = "int", value = "当前页"),
        @ApiImplicitParam(name = "pageSize", dataType = "int", value = "每页显示数量")
    })
    @ApiOperation("分页查询")
    @GetMapping("/page")
    public ResultVO<PageUtilsVO<GenTest1VO>> getPage(@ApiIgnore @RequestParam Map<String, Object> params){
        PageUtilsVO<GenTest1VO> pageUtils = genTest1Service.queryPage(params);
        return ResultVO.ok(pageUtils);
    }
    @ApiOperation("新增")
    @PostMapping("/create")
    public ResultVO<String> save(@ApiParam("数据对象")GenTest1VO data){
        GenTest1 genTest1 = new GenTest1();
        BeanCommonUtils.copyProperties(data, genTest1);
        genTest1Service.save(genTest1);
        return ResultVO.ok();
    }
    @ApiOperation("删除")
    @DeleteMapping("/delete/{id}")
    public ResultVO<String> delete(@ApiParam("数据对象id") @PathVariable("id")String id){
        genTest1Service.removeById(id);
        return ResultVO.ok();
    }
    @ApiOperation("更新")
    @PutMapping("/update")
    public ResultVO<String> update(@ApiParam("数据对象")GenTest1VO data){
        GenTest1 genTest1 = new GenTest1();
        BeanCommonUtils.copyProperties(data, genTest1);
        genTest1Service.updateById(genTest1);
        return ResultVO.ok();
    }
    @ApiOperation("通过ID获取一条数据")
    @GetMapping("/read/{id}")
    public ResultVO<GenTest1VO> getById(@ApiParam("数据对象id") @PathVariable("id")String id){
        GenTest1 genTest1 = genTest1Service.getById(id);
        GenTest1VO genTest1VO = new GenTest1VO();
        BeanCommonUtils.copyProperties(genTest1, genTest1VO);
        return ResultVO.ok(genTest1VO);
    }

}
