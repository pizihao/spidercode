package com.example.aliyuntest.service;

import com.alibaba.fastjson.JSON;
import com.aliyun.swas_open20200601.Client;
import com.aliyun.swas_open20200601.models.ListInstancesRequest;
import com.aliyun.swas_open20200601.models.ListInstancesResponse;
import com.aliyun.teaopenapi.models.Config;
import reactor.core.publisher.Mono;

public class SwasTest {

    public static void main(String[] args) throws Exception {
        Config config = new Config()
                // 必填，您的 AccessKey ID
                .setAccessKeyId("LTAI5tQJfnEWZwsmgNZeNC4s")
                // 必填，您的 AccessKey Secret
                .setAccessKeySecret("vqMn6TKrD22rYROXQREMev0981rN0E");
        // 访问的域名
        config.endpoint = "swas.cn-shanghai.aliyuncs.com";
        Client client = new Client(config);
        ListInstancesRequest instancesRequest = new ListInstancesRequest();
        instancesRequest.setRegionId("cn-shanghai");
        ListInstancesResponse instancesResponse = client.listInstances(instancesRequest);
        System.out.println(JSON.toJSONString(instancesResponse));
    }
}
