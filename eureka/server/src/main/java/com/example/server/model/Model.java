package com.example.server.model;

public class Model {

    String fixParam;
    String bizType;
    String goodsIdList;
    String skuIdList;

    public Model() {
    }

    public Model(String fixParam, String bizType, String goodsIdList, String skuIdList) {
        this.fixParam = fixParam;
        this.bizType = bizType;
        this.goodsIdList = goodsIdList;
        this.skuIdList = skuIdList;
    }

    public String getFixParam() {
        return fixParam;
    }

    public void setFixParam(String fixParam) {
        this.fixParam = fixParam;
    }

    public String getBizType() {
        return bizType;
    }

    public void setBizType(String bizType) {
        this.bizType = bizType;
    }

    public String getGoodsIdList() {
        return goodsIdList;
    }

    public void setGoodsIdList(String goodsIdList) {
        this.goodsIdList = goodsIdList;
    }

    public String getSkuIdList() {
        return skuIdList;
    }

    public void setSkuIdList(String skuIdList) {
        this.skuIdList = skuIdList;
    }

    @Override
    public String toString() {
        return "Model{" +
                "fixParam='" + fixParam + '\'' +
                ", bizType='" + bizType + '\'' +
                ", goodsIdList='" + goodsIdList + '\'' +
                ", skuIdList='" + skuIdList + '\'' +
                '}';
    }
}
