package com.whale.cloud.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.whale.cloud.entity.UserInfo;
import com.whale.cloud.mapper.UserMapper;
import com.whale.cloud.router.annotation.TB;
import com.whale.cloud.service.DbRouterService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author jiazhiwei
 */
@Service
public class DbRouterServiceImpl implements DbRouterService {
    @Resource
    private UserMapper userMapper;

    @Override
    public List<UserInfo> listUsers() {
        return userMapper.selectList(Wrappers.emptyWrapper());
    }

    @TB(key = "userName")
    @Override
    public List<UserInfo> listByUserName(String userName) {
        return userMapper.selectList(Wrappers.lambdaQuery(UserInfo.class).eq(UserInfo::getUserName,userName));
    }
    @TB(key = "userInfo.userName")
    @Override
    public void insertOne(UserInfo userInfo) {
        userMapper.insert(userInfo);
    }
}
