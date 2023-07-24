package com.example.server.model;


import java.util.ArrayList;
import java.util.List;

public class ModelTest {

    String fixParam;
    String bizType;
    String goodsIdList;
    String skuIdList;
    long time;

    public ModelTest() {
    }

    public ModelTest(String fixParam, String bizType, String goodsIdList, String skuIdList) {
        this.fixParam = fixParam;
        this.bizType = bizType;
        this.goodsIdList = goodsIdList;
        this.skuIdList = skuIdList;
    }


    public ModelTest(long time) {
        this.time = time;
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
        return "ModelTest{" +
                "fixParam='" + fixParam + '\'' +
                ", bizType='" + bizType + '\'' +
                ", goodsIdList='" + goodsIdList + '\'' +
                ", skuIdList='" + skuIdList + '\'' +
                ", time=" + time +
                '}';
    }

    public static void main(String[] args) {
        List<ModelTest> modelTests = new ArrayList<>();

        modelTests.add(new ModelTest(10));
        modelTests.add(new ModelTest(20));
        modelTests.add(new ModelTest(1));
        modelTests.add(new ModelTest(5));
        modelTests.add(new ModelTest(4));

        ModelTest test = modelTests.stream()
                .min((f, l) -> Math.toIntExact(f.time - l.time))
                .get();

        System.out.println(test);


    }

}
