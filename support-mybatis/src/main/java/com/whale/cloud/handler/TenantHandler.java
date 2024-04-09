package com.whale.cloud.handler;

import com.baomidou.mybatisplus.core.plugins.IgnoreStrategy;
import com.baomidou.mybatisplus.core.plugins.InterceptorIgnoreHelper;
import com.baomidou.mybatisplus.extension.plugins.handler.TenantLineHandler;
import com.whale.cloud.context.ExecutionContext;
import net.sf.jsqlparser.expression.Expression;
import net.sf.jsqlparser.expression.LongValue;
import net.sf.jsqlparser.schema.Column;

import java.util.List;
import java.util.Optional;

public class TenantHandler implements TenantLineHandler {
    private boolean enable;

    public TenantHandler(boolean tenantEnable) {
        this.enable = tenantEnable;
    }

    @Override
    public Expression getTenantId() {
        return Optional.ofNullable(ExecutionContext.getTenant()).map(LongValue::new).orElse(new LongValue(0));
    }

    @Override
    public String getTenantIdColumn() {
        return "tenant_id";
    }

    @Override
    public boolean ignoreTable(String tableName) {
        return TenantLineHandler.super.ignoreTable(tableName);
    }

    @Override
    public boolean ignoreInsert(List<Column> columns, String tenantIdColumn) {
        return TenantLineHandler.super.ignoreInsert(columns, tenantIdColumn);
    }

    public boolean enableTenant() {
        return enable;
    }

    /**
     * 禁用租户处理器
     */
    public static void disable(){
        InterceptorIgnoreHelper.handle(IgnoreStrategy.builder().tenantLine(true).build());
    }


    /**
     * 启用租户处理器租户处理器
     */
    public static void enable(){
        InterceptorIgnoreHelper.clearIgnoreStrategy();
    }
}
