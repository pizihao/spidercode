package com.deep.service.jsr269;


import com.deep.service.jsr269.operation.Blue;
import com.deep.service.jsr269.operation.Close;
import com.deep.service.jsr269.operation.Open;
import com.deep.service.jsr269.operation.TestEnum;

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
    arrays = {TestEnum.DOWN, TestEnum.LEFT, TestEnum.RIGHT, TestEnum.UP},
    opens = {@Open("liu"), @Open("wen"), @Open("hao")},
    blues = {
        @Blue(
            opens = {@Open("liu"), @Open("wen"), @Open("hao")},
            enums = {TestEnum.DOWN, TestEnum.LEFT, TestEnum.RIGHT, TestEnum.UP}
        ),
        @Blue(
            opens = {@Open("xiang"), @Open("chun"), @Open("qu")},
            enums = {TestEnum.DOWN, TestEnum.LEFT, TestEnum.RIGHT}
        )
    }
)
@Open("kaiakaiaia")
public @interface Model {


}
