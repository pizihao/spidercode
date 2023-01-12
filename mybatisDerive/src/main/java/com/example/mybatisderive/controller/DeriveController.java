package com.example.mybatisderive.controller;

import com.alibaba.fastjson.JSONObject;
import com.example.mybatisderive.model.DO.DeriveDO;
import com.example.mybatisderive.model.DTO.CloudOrder;
import com.example.mybatisderive.model.DTO.QueryDTO;
import com.example.mybatisderive.service.DeriveService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

/**
 * @author Create by liuwenhao on 2022/9/25 13:33
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/derive")
public class DeriveController {

    final DeriveService deriveService;
    @Value("${local.name}")
    String username;

    @PostMapping("/save")
    public Integer save(@RequestBody DeriveDO deriveDO) {
        return deriveService.save(deriveDO);
    }

    @GetMapping("/select")
    public List<DeriveDO> select(QueryDTO dto) {
        return deriveService.select(dto);
    }

    @GetMapping("/selectList")
    public void select() {
        deriveService.insert();
    }

    @GetMapping("insertDerive")
    public void insertDerive() {
        deriveService.insertDerive();
    }

    @GetMapping("jacksonTest")
    public CloudOrder jacksonTest() {
        String a = "{\n" +
                "  \"id\": \"8a9990897d5bdc5e017d6a86212d0022\",\n" +
                "  \"brandId\": \"ff80808176ade78c0176ae1304180002\",\n" +
                "  \"platformId\": \"null\",\n" +
                "  \"poolId\": \"ff8080817742c9fc0177434310360007\",\n" +
                "  \"projectId\": \"null\",\n" +
                "  \"areaId\": \"7b9555b4-f8f6-47f3-966a-b6b92b0fb02d\",\n" +
                "  \"availableAreaId\": \"e15e294f-958f-4667-a2c6-ce6ab5897190\",\n" +
                "  \"orderNumber\": \"Nbcb0f6d8-5720-48c3-9906-7ba69859c6c7\",\n" +
                "  \"tenantId\": \"ff808081747d905201748ba96c1e0000\",\n" +
                "  \"tenantAcount\": \"null\",\n" +
                "  \"userId\": \"777e310d-8365-4a69-b281-81d0f5be9e1c\",\n" +
                "  \"jobId\": \"null\",\n" +
                "  \"institutionsId\": \"ff8080817688a779017688e9f1120001\",\n" +
                "  \"directoryPrice\": 0.00,\n" +
                "  \"discountPrice\": 0.00,\n" +
                "  \"couponsId\": \"null\",\n" +
                "  \"activityId\": \"null\",\n" +
                "  \"mealPrice\": \"null\",\n" +
                "  \"orderType\": \"new_order\",\n" +
                "  \"productId\": \"null\",\n" +
                "  \"status\": \"pass\",\n" +
                "  \"resourceType\": \"vm\",\n" +
                "  \"custom\": {\n" +
                "    \"paymentModeId\": \"length_billing\",\n" +
                "    \"unit\": \"month\",\n" +
                "    \"timeLength\": 1,\n" +
                "    \"totalAttributePrice\": 0,\n" +
                "    \"attributePrice\": [\n" +
                "      {\n" +
                "        \"price\": 0,\n" +
                "        \"templateCode\": \"vcpu\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"price\": 0,\n" +
                "        \"templateCode\": \"memory\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"price\": 0,\n" +
                "        \"templateCode\": \"systemDisk\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"price\": 0,\n" +
                "        \"templateCode\": \"mirror\"\n" +
                "      }\n" +
                "    ]\n" +
                "  },\n" +
                "  \"createtime\": \"2021-11-29 15:08:34.0\",\n" +
                "  \"describe\": \"null\",\n" +
                "  \"orderValue\": [\n" +
                "    {\n" +
                "      \"attributeId\": \"ff80808176fa21980176fc19b7f9000c\",\n" +
                "      \"subassemblyCode\": \"enumType\",\n" +
                "      \"specificationsName\": \"2*4\",\n" +
                "      \"name\": \"计算规格\",\n" +
                "      \"typeName\": \"通用型\",\n" +
                "      \"tableId\": \"73842d91-7918-4a84-8105-34ecb264df4d\",\n" +
                "      \"templateCode\": \"specifications\",\n" +
                "      \"value\": \"2*4\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"attributeId\": \"ff80808176fa21980176fc1b6593000d\",\n" +
                "      \"subassemblyCode\": \"enumType\",\n" +
                "      \"name\": \"镜像\",\n" +
                "      \"typeName\": \"常用镜像\",\n" +
                "      \"tableId\": \"8a99908d7d6503ef017d6a3298fd0587\",\n" +
                "      \"templateCode\": \"mirror\",\n" +
                "      \"value\": \"vm-26\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"attributeId\": \"ff8080817723cc050177251b5b8e004a\",\n" +
                "      \"subassemblyCode\": \"enumType\",\n" +
                "      \"valueName\": \"是\",\n" +
                "      \"name\": \"CPU热部署\",\n" +
                "      \"templateCode\": \"cpuHotDeployment\",\n" +
                "      \"value\": \"true\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"attributeId\": \"ff8080817723cc050177251cc81d004b\",\n" +
                "      \"subassemblyCode\": \"enumType\",\n" +
                "      \"valueName\": \"是\",\n" +
                "      \"name\": \"内存热部署\",\n" +
                "      \"templateCode\": \"memoryHotDeployment\",\n" +
                "      \"value\": \"true\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"attributeId\": \"ff80808176fa21980176fc2b07080012\",\n" +
                "      \"subassemblyCode\": \"textBox\",\n" +
                "      \"name\": \"实例名称\",\n" +
                "      \"templateCode\": \"instanceName\",\n" +
                "      \"value\": \"云计算\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"attributeId\": \"ff80808176fa21980176fc31d3ce0013\",\n" +
                "      \"relevantAttribute\": [],\n" +
                "      \"subassemblyCode\": \"enumType\",\n" +
                "      \"valueName\": \"创建后设置\",\n" +
                "      \"name\": \"登录凭证\",\n" +
                "      \"templateCode\": \"logonCredentials\",\n" +
                "      \"value\": \"creationSetting\"\n" +
                "    },\n" +
                "    {\n" +
                "      \"attributeId\": \"ff80808176fa21980176fc26f14a000f\",\n" +
                "      \"relevantAttribute\": [\n" +
                "        {\n" +
                "          \"subnetId\": \"be343e52-35c9-4373-a518-7794b46f4874\",\n" +
                "          \"netcardType\": \"E1000\",\n" +
                "          \"ipDataType\": \"Manual\",\n" +
                "          \"ipAddress\": \"10.25.56.2\",\n" +
                "          \"subnetResourceUuid\": \"85aaa396-94e4-4e17-82e4-c1104a32ec9f\",\n" +
                "          \"subnetName\": \"子网\"\n" +
                "        }\n" +
                "      ],\n" +
                "      \"subassemblyCode\": \"enumType\",\n" +
                "      \"name\": \"网络\",\n" +
                "      \"tableId\": \"00fb936c-ae6c-4aec-8b39-f88348c397b7\",\n" +
                "      \"templateCode\": \"network\",\n" +
                "      \"value\": \"dvportgroup-439\"\n" +
                "    }\n" +
                "  ],\n" +
                "  \"number\": 1,\n" +
                "  \"orderUuid\": \"null\",\n" +
                "  \"updatetime\": \"2021-11-29 15:09:05.0\",\n" +
                "  \"contractUsed\": \"null\",\n" +
                "  \"totalPrice\": \"null\"\n" +
                "}";
        return JSONObject.parseObject(a, CloudOrder.class);
    }

    @GetMapping("getLocalName")
    public void getLocalName() throws UnknownHostException {
        System.out.println(Inet4Address.getLocalHost().getHostAddress());
        System.out.println(username);
    }

}
