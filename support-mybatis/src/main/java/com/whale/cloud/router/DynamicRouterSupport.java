package com.whale.cloud.router;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.map.MapUtil;
import com.whale.cloud.exception.DbRouterFailException;
import com.whale.cloud.exception.DbRouterNotConfigException;
import com.whale.cloud.router.algorithm.DbRouterAlgorithm;
import com.whale.cloud.router.algorithm.TbRouterAlgorithm;
import com.whale.cloud.router.config.RouterDataSourceConfig;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author jiazhiwei
 */
@Data
@Slf4j
public class DynamicRouterSupport {
    @Resource
    private TbRouterAlgorithm tbRouterAlgorithm;

    @Resource
    private DbRouterAlgorithm dbRouterAlgorithm;

    @Resource
    private RouterDataSourceConfig dataSourceConfig;

    protected String getDbKey(String assignKey) {
        Map<String, Map<String, Object>> dataSources = dataSourceConfig.getDatasource();
        if (MapUtil.isEmpty(dataSources)){
            throw new DbRouterNotConfigException();
        }
        Map<String, Object> map = dataSourceConfig.getDatasource().get(assignKey);
        if (null == map){
            throw new DbRouterFailException(assignKey);
        }
        return assignKey;
    }
}
