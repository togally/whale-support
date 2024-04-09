package com.whale.cloud.entity;

import lombok.Data;

@Data
public class BaseUserTenantEntity extends BaseTenantEntity {

    private static final long serialVersionUID = 378491315060008798L;

    /**
     * userId
     */
    private Long userId;
}
