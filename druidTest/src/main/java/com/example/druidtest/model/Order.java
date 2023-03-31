package com.example.druidtest.model;

/**
 * @author Create by liuwenhao on 2022/8/16 17:40
 */
public class Order {

    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Order{" +
            "name='" + name + '\'' +
            '}';
    }
}