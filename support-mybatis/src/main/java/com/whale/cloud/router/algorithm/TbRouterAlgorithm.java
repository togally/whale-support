package com.whale.cloud.router.algorithm;

import com.whale.cloud.executor.AlgorithmExecutor;
import com.whale.cloud.router.config.RouterDataSourceConfig;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 动态库表路由
 *
 * @author jiazhiwei
 */
@Component
public class TbRouterAlgorithm implements AlgorithmExecutor<String, Integer> {
    @Resource
    private RouterDataSourceConfig dataSourceConfig;

    /**
     * 路由算法执行
     *
     * @param routerKey 路由key
     * @return 路由后的库/表
     */
    @Override
    public Integer execute(String routerKey) {
        int tbCount = dataSourceConfig.getTbCount();
        int dbCount = dataSourceConfig.getDbCount();
        int size = dbCount * tbCount;
        /**
         * 扰动函数
         * routerKey.hashCode() >>> 16 右移动16位后取的是高位的16位
         * 异或运算目的是让高位的16位与低位的16位都参与运算
         */
        int idx = (size - 1) & (routerKey.hashCode() ^ (routerKey.hashCode() >>> 16));
        return idx % tbCount + 1;
    }
}
