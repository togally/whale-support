package com.whale.test.dbrouter;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.whale.cloud.SupportTestApplication;
import com.whale.cloud.entity.UserInfo;
import com.whale.cloud.mapper.UserMapper;
import com.whale.cloud.router.DbContextHolder;
import com.whale.cloud.service.DbRouterService;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@SpringBootTest(classes = SupportTestApplication.class)
public class TestDbRouter {
    @Resource
    private UserMapper userMapper;

    @Resource
    private DbRouterService dbRouterService;

    @Test
    public void testDataSourceChange() {
        DbContextHolder.setDBKey("db01");
        List<UserInfo> userInfoList = userMapper.selectList(Wrappers.emptyWrapper());
        Assert.isTrue(userInfoList.size() == 1, "db01查询user数据不为1");

        DbContextHolder.setDBKey("db02");
        userInfoList = userMapper.selectList(Wrappers.emptyWrapper());
        Assert.isTrue(userInfoList.size() == 0, "db02查询user数据不为0");
    }

    @Test
    public void testDbChange() {
        System.out.println(dbRouterService.listUsers());
        ;
    }

    @Test
    public void testTbChange() {
        System.out.println(dbRouterService.listByUserName("XaY0"));
        ;
    }

    @Test
    public void testRouteInsert() {
        long st = System.currentTimeMillis();
        for (int i = 0; i < 20000; i++) {
            dbRouterService.insertOne(new UserInfo(RandomString.make(4)));
        }
        log.info("执行完成，执行时间:{}", (System.currentTimeMillis() - st));
    }
}
