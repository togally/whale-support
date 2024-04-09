package com.whale.cloud;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.TenantLineInnerInterceptor;
import com.whale.cloud.handler.OperatorFillHandler;
import com.whale.cloud.handler.TenantHandler;
import com.whale.cloud.router.config.RouterDataSourceConfig;
import com.whale.cloud.router.dynamic.DynamicRouterDatasource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;


/**
 * @author jiazhiwei
 */
@Configuration
@MapperScan(value = {"com.whale.**.mapper"})
@ConditionalOnProperty(prefix = "whale.mybatis", name = "enable", havingValue = "true", matchIfMissing = true)
public class MybatisAutoConfiguration {
    @Resource
    private RouterDataSourceConfig dataSourceConfig;

    @Value("${whale.mybatis.tenant.enable:true}")
    private boolean tenantEnable;

    @Bean
    @ConditionalOnMissingBean
    public TenantHandler tenantHandler() {
        return new TenantHandler(tenantEnable);
    }

    /**
     * 租户插件
     */
    @Bean
    @ConditionalOnMissingBean
    public MybatisPlusInterceptor mybatisPlusInterceptor(TenantHandler tenantHandler) {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        if (tenantHandler.enableTenant()){
            interceptor.addInnerInterceptor(new TenantLineInnerInterceptor(tenantHandler));
        }
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }

    @Bean
    @ConditionalOnMissingBean(MetaObjectHandler.class)
    @ConditionalOnProperty(prefix = "whale.mybatis.fill", name = "enable", havingValue = "true", matchIfMissing = true)
    public OperatorFillHandler updateFillHandler() {
        return new OperatorFillHandler();
    }

    @Bean
    public DataSource dataSource() {
        DynamicRouterDatasource datasource = new DynamicRouterDatasource();

        if (dataSourceConfig.getDbCount() != 0) {
            Map<Object, Object> targetDataSources = new HashMap<>(dataSourceConfig.getDatasource().size());
            for (Map.Entry<String, Map<String, Object>> dataSourceEntry : dataSourceConfig.getDatasource().entrySet()) {
                String dbKey = dataSourceEntry.getKey();
                Map<String, Object> infos = dataSourceEntry.getValue();
                String url = infos.get(RouterDataSourceConfig.ConfigKey.URL).toString();
                String name = infos.get(RouterDataSourceConfig.ConfigKey.USERNAME).toString();
                String pwd = infos.get(RouterDataSourceConfig.ConfigKey.PSW).toString();
                String driver = infos.get(RouterDataSourceConfig.ConfigKey.DRIVER_CLASS).toString();

                DriverManagerDataSource dataSource = new DriverManagerDataSource(url, name, pwd);
                dataSource.setDriverClassName(driver);
                targetDataSources.put(dbKey, dataSource);
            }
            datasource.setTargetDataSources(targetDataSources);
        }
        return datasource;
    }
}
