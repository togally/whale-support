package com.whale.cloud.facotry;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.whale.cloud.exception.ResultCode;
import com.whale.cloud.result.BaseResult;
import com.whale.cloud.result.CollectionResult;
import com.whale.cloud.result.PageResult;

import java.util.List;

public class ResultFactory {

    /**
     * 成功
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> BaseResult<T> success(T data) {
        return new BaseResult(ResultCode.SUCCESS, data);
    }

    /**
     * 成功
     *
     * @param data
     * @param <T>
     * @return
     */
    public static <T> CollectionResult<T> success(List<T> data) {
        return new CollectionResult(ResultCode.SUCCESS, data);
    }

    /**
     * 成功
     *
     * @param page
     * @param <T>
     * @return
     */
    public static <T> PageResult<T> success(IPage<T> page) {
        return new PageResult(ResultCode.SUCCESS, page);
    }

    /**
     * 成功
     *
     * @return
     */
    public static BaseResult success() {
        return new BaseResult(ResultCode.SUCCESS);
    }

    /**
     * 异常返回
     *
     * @param resultCode
     * @return
     */
    public static BaseResult error(ResultCode resultCode) {
        return new BaseResult(resultCode);
    }

    /**
     * 异常返回
     *
     * @param resultCode
     * @return
     */
    public static BaseResult error(ResultCode resultCode, String message) {
        return new BaseResult(resultCode, message);
    }

    /**
     * 异常返回
     *
     * @param message
     * @return
     */
    public static BaseResult fail(String message) {
        return new BaseResult(ResultCode.FAIL,message);
    }
}
