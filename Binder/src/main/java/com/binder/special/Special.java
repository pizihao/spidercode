package com.binder.special;

import java.util.function.Function;

public interface Special<T> extends Function<String, T> {

    String getPrefix();


}
