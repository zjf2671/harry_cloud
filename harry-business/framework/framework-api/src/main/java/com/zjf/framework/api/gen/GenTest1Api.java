package com.zjf.framework.api.gen;

import com.zjf.common.core.utils.dto.ResultDTO;
import com.zjf.framework.common.gen.output.GenTest1OutputDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 对外暴露api
 * @author Harry
 */
public interface GenTest1Api {

    /**
     * 通过id查询
     * @param id
     * @return ResultDTO<GenTest1OutputDTO>
     */
    @GetMapping("/api/read/{id}")
    ResultDTO<GenTest1OutputDTO> getById(@PathVariable("id") Long id);

}
