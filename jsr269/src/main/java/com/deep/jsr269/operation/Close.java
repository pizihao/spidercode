package com.deep.jsr269.operation;

import com.deep.jsr269.TestDefult;

import java.lang.annotation.*;

/**
 * <h2></h2>
 *
 * @author Create by liuwenhao on 2022/8/3 14:13
 */
@Target(ElementType.ANNOTATION_TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Close {

    /**
     * 常量 ，基本数据类型和String类型
     */
    int test();

    /**
     * Class
     */
    Class<? extends TestDefult> cls();

    /**
     * 注解
     */
    Open open();

    /**
     * Enum
     */
    TestEnum enums();

    /**
     * 数组
     */

    TestEnum[] arrays();

    Open[] opens();

    Blue[] blues();

}