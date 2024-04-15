package com.whale.cloud.router.annotation;

import java.lang.annotation.*;

/**
 * 数据源
 *
 * @author jiazhiwei
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.METHOD})
public @interface DS {
    String key() default "";
}
