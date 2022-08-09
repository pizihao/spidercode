package com.deep.service.jsr269;

import com.deep.jsr269.annotation.Top;
import com.deep.service.jsr269.operation.Close;
import com.deep.service.jsr269.operation.Open;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Top(importance = {Open.class, Close.class}, ignore = Close.class)
@Model
public @interface Api {
}
