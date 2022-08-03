package com.deep.service;

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

    private List<String> strings = new ArrayList();

    private Map<? extends Integer, ? super String> integers = new HashMap<>();
}