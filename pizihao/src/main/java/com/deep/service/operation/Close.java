package com.deep.service.operation;

import com.deep.service.TestDefult;

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

    TestEnum[] arraylist()  default {TestEnum.DOWN, TestEnum.LEFT, TestEnum.RIGHT, TestEnum.UP};


}