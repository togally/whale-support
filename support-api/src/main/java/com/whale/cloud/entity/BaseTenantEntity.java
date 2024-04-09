package com.whale.cloud.entity;

import lombok.Data;

@Data
public class BaseTenantEntity extends BaseLogicDeleteEntity {
    private static final long serialVersionUID = -7203059506882412698L;

    /**
     * 租户id
     */
    private Long tenantId;
}
