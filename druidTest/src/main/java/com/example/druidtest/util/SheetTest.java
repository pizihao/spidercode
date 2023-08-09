package com.example.druidtest.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SheetTest {

    static String a = "{\"enName\":[\"ticketCode\",\"batchNumber\",\"ticketNo\",\"ticketName\",\"statusExplain\",\"exchangeLinkAddress\"],\"showName\":[\"卡券编码\",\"批次\",\"卡券编号\",\"卡券名称\",\"状态\",\"链接地址\"]}";

    public static void main(String[] args) {
        // 单sheet查询表头信息
        JSONObject jsonObject = JSON.parseObject(a);

        Object enName = jsonObject.get("enName");
        Object showName = jsonObject.get("showName");

        List<String> enNameList = JSON.parseArray(enName.toString(), String.class);
        List<String> showNameList = JSON.parseArray(showName.toString(), String.class);

        // 单sheet
        Map<String, Integer> headers = new HashMap<>();

        showNameList.forEach(g -> {
            headers.put(g, 0);
        });
        System.out.println(JSON.toJSONString(headers));
    }

}
