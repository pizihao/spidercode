package com.example.aclient.util;


import lombok.Data;

@Data
public class Info {

    String name;

    Integer index;

    public Info(String name) {
        this.name = name;
    }
}
