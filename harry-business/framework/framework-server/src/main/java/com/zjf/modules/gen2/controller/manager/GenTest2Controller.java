package com.zjf.modules.gen2.controller.manager;


import com.zjf.common.core.utils.BeanCommonUtils;
import com.zjf.common.core.utils.vo.PageUtilsVO;
import com.zjf.common.core.utils.vo.ResultVO;
import com.zjf.modules.gen2.entity.GenTest2;
import com.zjf.modules.gen2.service.GenTest2Service;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Map;
import com.zjf.modules.gen2.vo.GenTest2VO;
/**
 * 测试用表2 前端控制器
 *
 * @author harry
 * @date 2019-09-24
 */
@Api(tags = "测试用表2")
@RestController
@RequestMapping("genTest2")
public class GenTest2Controller{

    @Autowired
    private GenTest2Service genTest2Service;

    @ApiImplicitParams({
        @ApiImplicitParam(name = "currPage", dataType = "int", value = "当前页"),
        @ApiImplicitParam(name = "pageSize", dataType = "int", value = "每页显示数量")
    })
    @ApiOperation("分页查询")
    @GetMapping("/page")
    public ResultVO<PageUtilsVO<GenTest2VO>> getPage(@ApiIgnore @RequestParam Map<String, Object> params){
        PageUtilsVO<GenTest2VO> pageUtils = genTest2Service.queryPage(params);
        return ResultVO.ok(pageUtils);
    }
    @ApiOperation("新增")
    @PostMapping("/create")
    public ResultVO<String> save(@ApiParam("数据对象")GenTest2VO data){
        GenTest2 genTest2 = new GenTest2();
        BeanCommonUtils.copyProperties(data, genTest2);
        genTest2Service.save(genTest2);
        return ResultVO.ok();
    }
    @ApiOperation("删除")
    @DeleteMapping("/delete/{id}")
    public ResultVO<String> delete(@ApiParam("数据对象id") @PathVariable("id")String id){
        genTest2Service.removeById(id);
        return ResultVO.ok();
    }
    @ApiOperation("更新")
    @PutMapping("/update")
    public ResultVO<String> update(@ApiParam("数据对象")GenTest2VO data){
        GenTest2 genTest2 = new GenTest2();
        BeanCommonUtils.copyProperties(data, genTest2);
        genTest2Service.updateById(genTest2);
        return ResultVO.ok();
    }
    @ApiOperation("通过ID获取一条数据")
    @GetMapping("/read/{id}")
    public ResultVO<GenTest2VO> update(@ApiParam("数据对象id") @PathVariable("id")String id){
        GenTest2 genTest2 = genTest2Service.getById(id);
        GenTest2VO genTest2VO = new GenTest2VO();
        BeanCommonUtils.copyProperties(genTest2, genTest2VO);
        return ResultVO.ok(genTest2VO);
    }

}
