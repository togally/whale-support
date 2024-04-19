package com.whale.cloud.router.dynamic;

import cn.hutool.core.util.StrUtil;
import com.whale.cloud.router.DbContextHolder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;


/**
 * spring-jdbc的路由数据源
 *
 * @author jiazhiwei
 */
public class DynamicRouterDatasource extends AbstractRoutingDataSource {
    @Value("${whale.mini-db-router.jdbc.default:db01}")
    private String defaultDb;

    @Override
    protected Object determineCurrentLookupKey() {
        return StrUtil.isBlank(DbContextHolder.getDBKey()) ? defaultDb : DbContextHolder.getDBKey();
    }
}
