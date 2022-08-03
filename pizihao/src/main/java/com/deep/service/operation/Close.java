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

    int test();

    Class<?> cls();

}