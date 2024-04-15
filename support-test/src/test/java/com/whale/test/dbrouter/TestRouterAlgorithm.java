package com.whale.test.dbrouter;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.whale.cloud.SupportTestApplication;
import com.whale.cloud.executor.AlgorithmExecutor;
import com.whale.cloud.router.algorithm.DbRouterAlgorithm;
import com.whale.cloud.router.algorithm.TbRouterAlgorithm;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.*;

@Slf4j
@SpringBootTest(classes = SupportTestApplication.class)
public class TestRouterAlgorithm {
    @Resource
    private TbRouterAlgorithm tbRouterAlgorithm;

    @Resource
    private DbRouterAlgorithm dbRouterAlgorithm;

    @Test
    public void testTbExecutor(){
        Map<Integer, Set<Integer>> routerMap = new HashMap<>();
        for (int i = 1; i < 1000; i++) {
            String routerKey = RandomString.make(i);
            int dbIndex = dbRouterAlgorithm.execute(routerKey);
            int tbIndex = tbRouterAlgorithm.execute(routerKey);
            if (null == routerMap.get(dbIndex)){
                routerMap.put(dbIndex,new HashSet<>());
            }
            routerMap.get(dbIndex).add(tbIndex);
        }
        log.info("路由结果", JSON.toJSONString(routerMap));
    }

    @Test
    public void testDbExecutor(){
        int index1 = 0;
        int index2 = 0;
        for (int i = 1; i < 1000; i++) {
            int dbIndex = dbRouterAlgorithm.execute(RandomString.make(i));
            Assert.isFalse(dbIndex > 2,"数据库索引超出范围!");
            index1 += dbIndex == 1 ? 1 : 0;
            index2 += dbIndex == 2 ? 1 : 0;
        }
        log.info("db0 路由次数:{}",index1);
        log.info("db1 路由次数:{}",index2);
    }
}
