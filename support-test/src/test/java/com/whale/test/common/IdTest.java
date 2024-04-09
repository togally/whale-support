package com.whale.test.common;

import com.whale.cloud.SupportTestApplication;
import com.whale.cloud.constant.Constants;
import com.whale.cloud.ids.IIdGenerator;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Map;

@Slf4j
@SpringBootTest(classes = SupportTestApplication.class)
public class IdTest {
    @Resource
    private Map<Constants.Ids, IIdGenerator> idGeneratorMap;

    @Test
    public void testIDGen() {
        for (Constants.Ids idStrategy : Constants.Ids.values()) {
            IIdGenerator generator = idGeneratorMap.get(idStrategy);
            log.info("依据id生成策略:{},生成了id:{}", idStrategy, generator.nextId());
        }
    }

}
