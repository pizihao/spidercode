package com.deep.service;

import com.deep.jsr269.Type;
import lombok.Getter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <h2></h2>
 *
 * @author Create by liuwenhao on 2022/7/28 17:25
 */
public class TestModel {

    @Type
    private List<String> strings = new ArrayList();
    @Type
    private Map<? extends Integer, ? super String> integers = new HashMap<>();

    public static void main(String[] args) {

        @Type
        List<String> strings = new ArrayList<>();
        TestModel testModel = new TestModel();
    }

    public List<String> _stringsType() {
        return this.strings;
    }

}