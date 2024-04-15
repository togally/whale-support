package com.whale.cloud.router.dynamic;

import com.whale.cloud.router.DbContextHolder;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * spring-jdbc的路由数据源
 *
 * @author jiazhiwei
 */
public class DynamicRouterDatasource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        return DbContextHolder.getDBKey();
    }
}
