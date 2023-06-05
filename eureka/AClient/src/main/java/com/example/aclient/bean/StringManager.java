package com.example.aclient.bean;

import org.springframework.stereotype.Component;

@Component
public class StringManager implements Manager<String> {

    String name = "dhkj";

    @Override
    public String getT() {
        return name;
    }

    @Override
    public void setT(String s) {
        this.name = s;
    }
}
