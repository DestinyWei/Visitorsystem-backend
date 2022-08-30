package com.project.aop.annotation;

import com.project.model.enums.BusinessType;

import java.lang.annotation.*;

/**
 * @description:
 * @author: weihaoming
 * @create: 2022-08-30-16:19
 * @version:
 */
@Target({ ElementType.PARAMETER, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {
    /**
     * 模块
     */
    String title() default "";

    /**
     * 功能
     */
    BusinessType businessType() default BusinessType.OTHER;
}
