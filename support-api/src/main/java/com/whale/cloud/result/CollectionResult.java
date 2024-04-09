package com.whale.cloud.result;

import com.whale.cloud.exception.ResultCode;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

/**
 * 集合返回值
 *
 * @param <T>
 */
@Data
@NoArgsConstructor
public class CollectionResult<T> extends BaseResult<Collection<T>> {
    public CollectionResult(ResultCode code) {
        super(code);
    }

    public CollectionResult(ResultCode code, Collection<T> data) {
        super(code, data);
    }

    public CollectionResult(ResultCode code, Collection<T> data, String message) {
        super(code, data, message);
    }
}
