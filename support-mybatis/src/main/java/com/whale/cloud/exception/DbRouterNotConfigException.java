package com.whale.cloud.exception;

/**
 * 路由数据库未配置异常
 * @author jiazhiwei
 */
public class DbRouterNotConfigException extends RuntimeException{
    public DbRouterNotConfigException() {
        super("please config router info in config files , such as yaml file");
    }
}
