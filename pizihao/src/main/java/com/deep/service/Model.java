package com.deep.service;


import com.deep.service.operation.Close;
import com.deep.service.operation.Open;
import com.deep.service.operation.TestEnum;

import java.lang.annotation.*;

@Target(ElementType.ANNOTATION_TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Project(name = @Open("打开"))
@Close(
    test = 1,
    cls = TestDefult.class,
    open = @Open("注解"),
    enums = TestEnum.DOWN,
    arrays = {TestEnum.DOWN, TestEnum.LEFT, TestEnum.RIGHT, TestEnum.UP}
)
public @interface Model {

    String value() default "测试";

}
