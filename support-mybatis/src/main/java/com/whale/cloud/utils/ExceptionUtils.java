package com.whale.cloud.utils;

import com.whale.cloud.exception.InternalException;

public class ExceptionUtils {
    public static void throwIfCondition(boolean condition, String msg){
        if (condition) throw new InternalException(msg);
    }
}
