package com.deep.service;

import com.deep.crow.type.ParameterizedTypeImpl;
import com.deep.jsr269.TypeName;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <h2></h2>
 *
 * @author Create by liuwenhao on 2022/7/28 17:25
 */
public class TestModel extends TestDefult {

    @TypeName("intType")
    private List<String> strings = new ArrayList();

    @TypeName("strType")
    private Map<? extends Integer, ? super String> integers = new HashMap<>();
}