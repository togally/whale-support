package com.whale.cloud.router.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Data
@Component
@ConfigurationProperties("mini-db-router.jdbc")
public class RouterDataSourceConfig {
    /**
     * 数据库信息
     */
    private Map<String, Map<String, Object>> datasource = new HashMap<>();

    /**
     * 分库数量
     */
    private int dbCount;

    /**
     * 分表数量
     */
    private int tbCount;

    /**
     * yaml配置key
     */
    public interface ConfigKey {
        String DRIVER_CLASS = "driver-class-name";

        String URL = "url";

        String PSW = "password";

        String USERNAME = "username";
    }
}
