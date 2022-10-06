package com.deep.pool.conrtroller;

/**
 * <h2></h2>
 *
 * @author Create by liuwenhao on 2022/8/17 18:44
 */
public class Model {

    Integer id;
    String name;

    String contractId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    @Override
    public String toString() {
        return "Model{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", contractId='" + contractId + '\'' +
                '}';
    }
}