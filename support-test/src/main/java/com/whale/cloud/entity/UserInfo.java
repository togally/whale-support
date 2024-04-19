package com.whale.cloud.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author jiazhiwei
 */
@Data
@NoArgsConstructor
@TableName("test_user")
public class UserInfo {
    private String userName;

    public UserInfo(String userName) {
        this.userName = userName;
    }
}
