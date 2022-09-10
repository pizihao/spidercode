package com.deep.mvc.model;

/**
 * <h2></h2>
 *
 * @author Create by liuwenhao on 2022/9/10 14:15
 */
public class Contract {
 
    String name;

    public Contract(Contract contract) {
        this.name = contract.getName();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}