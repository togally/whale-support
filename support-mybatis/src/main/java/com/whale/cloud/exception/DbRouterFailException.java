package com.whale.cloud.exception;

import cn.hutool.core.util.StrUtil;
import lombok.Data;

/**
 * @author jiazhiwei
 */
@Data
public class DbRouterFailException extends RuntimeException{
    public DbRouterFailException(String key) {
        super(StrUtil.format("can not found db by key : {}",key));
    }
}
