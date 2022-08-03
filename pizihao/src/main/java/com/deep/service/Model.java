package com.deep.service;


import com.deep.service.operation.Close;
import com.deep.service.operation.Open;

import java.lang.annotation.*;

@Target(ElementType.ANNOTATION_TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Project(name = @Open("打开"))
@Close(test = 1, cls = TestDefult.class)
public @interface Model {

    String value() default "测试";

}
