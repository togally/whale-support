package com.whale.cloud.router.annotation;

import java.lang.annotation.*;

/**
 * @author jiazhiwei
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface TB {
    String key() default "";
}
