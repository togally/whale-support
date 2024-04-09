package com.whale.cloud.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.whale.cloud.context.ExecutionContext;
import org.apache.ibatis.reflection.MetaObject;

import java.time.LocalDateTime;

public class OperatorFillHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        this.fill(metaObject, true);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.fill(metaObject, false);
    }

    private void fill(MetaObject metaObject, boolean isInsert) {
        if (isInsert) {
            this.strictInsertFill(metaObject, "createTime", () -> LocalDateTime.now(), LocalDateTime.class);
            this.strictInsertFill(metaObject, "createBy", () -> ExecutionContext.getOperator(), Long.class);
            this.strictInsertFill(metaObject, "isDel", () -> 0, Integer.class);
        }
        this.strictInsertFill(metaObject, "lastUpdateTime", () -> LocalDateTime.now(), LocalDateTime.class);
        this.strictInsertFill(metaObject, "lastUpdateBy", () -> ExecutionContext.getOperator(), Long.class);
    }

}
