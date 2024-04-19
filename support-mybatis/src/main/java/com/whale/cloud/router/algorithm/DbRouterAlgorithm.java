package com.whale.cloud.router.algorithm;

import com.whale.cloud.executor.AlgorithmExecutor;
import com.whale.cloud.router.config.RouterDataSourceConfig;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 数据库路由算法
 *
 * @author jiazhiwei
 */
@Component
public class DbRouterAlgorithm implements AlgorithmExecutor<String, Integer> {
    @Resource
    private RouterDataSourceConfig dataSourceConfig;

    @Override
    public Integer execute(String routerKey) {
        int dbCount = dataSourceConfig.getDbCount();
        int tbCount = dataSourceConfig.getTbCount();
        int size = dbCount * tbCount;
        /**
         * 扰动函数
         * routerKey.hashCode() >>> 16 右移动16位后取的是高位的16位
         * 异或运算目的是让高位的16位与低位的16位都参与运算
         */
        int idx = (size - 1) & (routerKey.hashCode() ^ (routerKey.hashCode() >>> 16));
        // 库表索引
        return idx % dbCount + 1;
    }
}
