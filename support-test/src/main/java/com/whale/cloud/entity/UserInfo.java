package com.whale.cloud.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author jiazhiwei
 */
@Data
@TableName("test_user")
public class UserInfo {
    private String userName;
}
