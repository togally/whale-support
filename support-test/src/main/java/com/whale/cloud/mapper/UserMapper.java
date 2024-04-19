package com.whale.cloud.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.whale.cloud.entity.UserInfo;
import com.whale.cloud.router.annotation.DS;

/**
 * @author jiazhiwei
 */
@DS(key = "db1")
public interface UserMapper extends BaseMapper<UserInfo> {
}
