package com.whale.cloud.exception;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

@Getter
public enum ResultCode {
    SUCCESS("0", "成功!"),
    ERROR("-1", "系统错误!"),
    NO_AUTH("-2", "无权限!"),
    FAIL("-3","请求失败!");
    @EnumValue
    private String value;
    private String desc;

    ResultCode(String value, String desc) {
        this.value = value;
        this.desc = desc;
    }
}
