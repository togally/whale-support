package com.whale.cloud.result;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.whale.cloud.exception.ResultCode;
import lombok.Data;

@Data
public class PageResult<T> extends CollectionResult<T> {
    private long total;

    private long size;

    private long current;

    public PageResult(ResultCode code, IPage<T> page) {
        super(code, page.getRecords());
        this.total = page.getTotal();
        this.size = page.getSize();
        this.current = page.getCurrent();
    }
}
