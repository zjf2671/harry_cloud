package com.zjf.testRedisLock;

import com.alibaba.fastjson.JSON;
import com.zjf.modules.gen1.service.GenTest1Service;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


/**
 * Created by Harry on 2019/11/6.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class Gen1ServiceImplTest {

    @Autowired
    private GenTest1Service genTest1Service;


    @Test
    public void saveGenTest1(){
        try {
            String name = "test";
            List<Callable<Boolean>> callList = Lists.newArrayList();
            ExecutorService executor = Executors.newCachedThreadPool();
            Callable<Boolean> call = ()->{
                return genTest1Service.saveGenTest1(name);
            };
            //50个并发线程
            for (int i = 0; i < 50; i++) {
                callList.add(call);
            }
            List<Future<Boolean>> futures = executor.invokeAll(callList);
            List<Boolean> idList = Lists.newArrayList();
            for(Future<Boolean> result:futures){
                if(result.get()!=null){
                    idList.add(result.get());
                }
            }
            Set<Boolean> idsSet = new HashSet<>(idList);
            log.info("idList===>{}", JSON.toJSONString(idList));
            log.info("idsSet===>{}", JSON.toJSONString(idsSet));
            Assert.assertTrue(idsSet.size() == 1);

        }catch (Exception e) {
            e.printStackTrace();
        }

    }


}