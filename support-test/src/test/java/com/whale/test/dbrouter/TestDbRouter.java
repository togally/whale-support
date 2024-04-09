package com.whale.test.dbrouter;

import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.whale.cloud.SupportTestApplication;
import com.whale.cloud.entity.UserInfo;
import com.whale.cloud.mapper.UserMapper;
import com.whale.cloud.router.DbContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@Slf4j
@SpringBootTest(classes = SupportTestApplication.class)
public class TestDbRouter {
    @Resource
    private UserMapper userMapper;

    @Test
    public void testDataSourceChange(){
        DbContextHolder.setDBKey("db01");
        List<UserInfo> userInfoList = userMapper.selectList(Wrappers.emptyWrapper());
        Assert.isTrue(userInfoList.size() == 1,"db01查询user数据不为1");

        DbContextHolder.setDBKey("db02");
        userInfoList = userMapper.selectList(Wrappers.emptyWrapper());
        Assert.isTrue(userInfoList.size() == 0,"db02查询user数据不为0");
    }
}
