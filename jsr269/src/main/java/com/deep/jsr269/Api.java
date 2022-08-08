package com.deep.jsr269;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Top
@Model
public @interface Api  {
}
