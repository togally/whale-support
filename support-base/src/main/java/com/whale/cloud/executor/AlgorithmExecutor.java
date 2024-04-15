package com.whale.cloud.executor;

/**
 * 算法执行期
 *
 * @author jiazhiwei
 */
public interface AlgorithmExecutor<T, R> {

    /**
     * 执行算法
     *
     * @param param
     * @return
     */
    R execute(T param);
}
