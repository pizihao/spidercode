package com.deep.mvc.model;

/**
 * <h2></h2>
 *
 * @author Create by liuwenhao on 2022/8/17 18:44
 */
public class Model {

    Integer id;
    String name;

    public Model() {
    }

    public Model(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Model{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}