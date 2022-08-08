package com.deep.jsr269;

import com.deep.jsr269.operation.Open;

import java.lang.annotation.*;

@Target(ElementType.ANNOTATION_TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Project {

    Open name();

}
