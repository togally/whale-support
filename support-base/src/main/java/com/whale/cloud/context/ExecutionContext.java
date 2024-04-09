package com.whale.cloud.context;

public class ExecutionContext {
    /**
     * 获取当前操作者
     *
     * @return
     */
    public static Long getOperator() {
        return 0L;
    }

    /**
     * 获取当前租户
     *
     * @return
     */
    public static Long getTenant() {
        return 1L;
    }
}
