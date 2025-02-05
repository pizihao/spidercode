package com.example.cache;

public class Goods {

    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }

    public Goods id(Integer id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Goods name(String name) {
        this.name = name;
        return this;
    }
}
