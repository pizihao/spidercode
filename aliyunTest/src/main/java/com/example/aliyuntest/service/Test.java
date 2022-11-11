package com.example.aliyuntest.service;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.ecs.model.v20140526.*;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

public class Test {

    public static void main(String[] args) throws ClientException {
        IClientProfile profile = DefaultProfile.getProfile("cn-shanghai", "LTAI5tQJfnEWZwsmgNZeNC4s", "vqMn6TKrD22rYROXQREMev0981rN0E");
        IAcsClient client = new DefaultAcsClient(profile);
        DescribeImagesRequest request = new DescribeImagesRequest();
        request.setImageOwnerAlias("system");
        DescribeImagesResponse response = client.getAcsResponse(request);
        System.out.println(JSON.toJSONString(response));

        DescribeRegionsRequest regionsRequest = new DescribeRegionsRequest();
        regionsRequest.setAcceptLanguage("zh-CN");
        DescribeRegionsResponse acsResponse = client.getAcsResponse(regionsRequest);
        System.out.println("====================");
        System.out.println(JSON.toJSONString(acsResponse));

    }
}
