package com.deep.service.jsr269;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
//@ShareAnnotation(ignore = Project.class, importance = {Close.class, Open.class})
@Model
public @interface Api {
}
