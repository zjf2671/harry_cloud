package com.zjf;

import com.alibaba.fastjson.JSON;
import com.zjf.client.GenTestApiClient;
import com.zjf.common.core.utils.dto.ResultDTO;
import com.zjf.framework.common.gen.output.GenTest1OutputDTO;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description test api
 * @Author Harry
 * @Date 2019/9/30 9:40
 **/
public class TestApi extends SpringbootTestContext{

    @Autowired
    private GenTestApiClient genTestApiClient;

    @Test
    public void testApi(){
        ResultDTO<GenTest1OutputDTO> byId = genTestApiClient.getById(1L);
        System.out.println(JSON.toJSONString(byId));
    }

}
