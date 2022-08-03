package com.deep.service;

import com.deep.service.operation.Open;

import java.lang.annotation.*;

@Target(ElementType.ANNOTATION_TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Project {

    Open name();

}
