package com.whale.cloud.exception;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

import java.util.List;

@Getter
public enum InterfaceScope {
    CREATE("CREATE","新增",List.of("add")),
    DELETE("DELETE","删除",List.of("delete")),
    UPDATE("UPDATE","更新",List.of("updateById")),
    QUERY("QUERY","查询",List.of("findById","page"))
    ;
    @EnumValue
    private String value;
    private String desc;

    private List<String> path;

    InterfaceScope(String value, String desc, List<String> path) {
        this.value = value;
        this.desc = desc;
        this.path = path;
    }
}
