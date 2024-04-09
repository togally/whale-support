package com.whale.cloud.result;

import com.whale.cloud.exception.ResultCode;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class BaseResult<T> implements Serializable {
    private static final long serialVersionUID = -6772452195280793195L;
    private T data;

    private String message;

    private ResultCode code;

    public BaseResult(ResultCode code) {
        this(code, null, null);
    }

    public BaseResult(ResultCode code, String message) {
        this(code, null, message);
    }

    public BaseResult(ResultCode code, T data) {
        this(code, data, code.getDesc());
    }

    public BaseResult(ResultCode code, T data, String message) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public boolean isNotSuccess() {
        return code != ResultCode.SUCCESS;
    }

    public boolean isSuccess() {
        return code == ResultCode.SUCCESS;
    }
}
