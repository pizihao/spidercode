package com.deep.service;

import com.deep.jsr269.Api;
import com.deep.jsr269.Top;

/**
 * <h2></h2>
 *
 * @author Create by liuwenhao on 2022/7/28 17:25
 */
@Top(Api.class)
public class TestModel {

    private String name;
    private String age;

    public static void main(String[] args) {
        TestModel testModel = new TestModel();
    }

}