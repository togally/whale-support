package com.whale.cloud.service;

import com.whale.cloud.entity.UserInfo;

import java.util.List;

/**
 * @author jiazhiwei
 */
public interface DbRouterService {

    /**
     * 查询用户
     * @return
     */
    List<UserInfo> listUsers();

    List<UserInfo> listByUserName(String userName);

    void insertOne(UserInfo userInfo);
}
