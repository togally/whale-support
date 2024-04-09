package com.whale.cloud.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class BaseLogicDeleteEntity extends BaseEntity {
    private static final long serialVersionUID = -2378595779229396812L;
    /**
     * 是否删除
     */
    @TableLogic(value = "0", delval = "unix_timestamp()")
    @TableField(select = false, fill = FieldFill.INSERT)
    private Integer isDel;
}
