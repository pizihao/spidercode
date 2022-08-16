package com.deep.service.jsr269;

import com.deep.jsr269.annotation.ShareAnnotation;
import com.deep.service.jsr269.operation.Close;
import com.deep.service.jsr269.operation.Open;

import java.lang.annotation.*;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ShareAnnotation(ignore = Project.class, importance = {Close.class, Open.class})
@Model
public @interface Api {
}
