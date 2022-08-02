package com.deep.service;


import java.lang.annotation.*;

@Target(ElementType.ANNOTATION_TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Project(name = TestDefult.class)
public @interface Model {

    String  value() default "测试";

}
