package com.whale.cloud.router.strategy;

import com.whale.cloud.exception.InterfaceScope;

import java.util.List;

/**
 * 接口范围策略
 */
public interface InterfaceScopeStrategy {
    /**
     * 获取启用接口范围
     * @return
     */
    List<InterfaceScope> enableScope();
}
