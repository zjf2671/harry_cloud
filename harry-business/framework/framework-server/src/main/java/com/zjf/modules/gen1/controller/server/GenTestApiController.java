package com.zjf.modules.gen1.controller.server;

import com.zjf.common.core.utils.BeanCommonUtils;
import com.zjf.common.core.utils.dto.ResultDTO;
import com.zjf.framework.common.gen.output.GenTest1OutputDTO;
import com.zjf.modules.gen1.entity.GenTest1;
import com.zjf.modules.gen1.service.GenTest1Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * feign的服务接口
 * @Author Harry
 **/
@ApiIgnore
@RestController
@RequestMapping("/api")
public class GenTestApiController {

    @Autowired
    private GenTest1Service genTest1Service;

    @GetMapping("/read/{id}")
    public ResultDTO<GenTest1OutputDTO> getById(@PathVariable("id")Long id){
        GenTest1 genTest1 = genTest1Service.getById(id);
        GenTest1OutputDTO genTest1OutputDTO = new GenTest1OutputDTO();
        BeanCommonUtils.copyProperties(genTest1, genTest1OutputDTO);
        return ResultDTO.ok(genTest1OutputDTO);
    }



}
