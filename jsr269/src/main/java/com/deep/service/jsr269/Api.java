package com.deep.service.jsr269;

import com.deep.jsr269.Top;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Top
@Model
public @interface Api  {
}
