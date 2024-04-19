package com.whale.cloud.handler;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.extension.plugins.handler.TableNameHandler;
import com.whale.cloud.router.DbContextHolder;

/**
 * @author jiazhiwei
 */
public class RouterTableNameHandler implements TableNameHandler {
    @Override
    public String dynamicTableName(String sql, String tableName) {
        if (StrUtil.isNotBlank(DbContextHolder.getTBKey())){
            tableName = tableName + DbContextHolder.getTBKey();
        }
        return tableName;
    }
}
