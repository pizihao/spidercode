package com.example.aliyuntest.service;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.ecs.model.v20140526.*;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;

import java.util.Arrays;

public class Test {

    public static void main(String[] args) throws ClientException {
//        IClientProfile profile = DefaultProfile.getProfile("cn-shanghai", "LTAI5tQJfnEWZwsmgNZeNC4s", "vqMn6TKrD22rYROXQREMev0981rN0E");
//        IAcsClient client = new DefaultAcsClient(profile);
//        DescribeImagesRequest request = new DescribeImagesRequest();
//        request.setImageOwnerAlias("system");
//        DescribeImagesResponse response = client.getAcsResponse(request);
//        System.out.println(JSON.toJSONString(response));
//
//        DescribeRegionsRequest regionsRequest = new DescribeRegionsRequest();
//        regionsRequest.setAcceptLanguage("zh-CN");
//        DescribeRegionsResponse acsResponse = client.getAcsResponse(regionsRequest);
//        System.out.println("====================");
//        System.out.println(JSON.toJSONString(acsResponse));

        String[] str = new String[]{"a", "b", "c", "d", "e"};
        int i = 1;
        int length = 2 * str.length - 1;
        String[] result = new String[length];
        System.arraycopy(str, 0, result, 0, str.length);
        while (i < length) {
            System.arraycopy(result, i, result, i + 1, length - i - 1);
            result[i] = "-";
            i = i + 2;
        }
        System.out.println(Arrays.toString(result));


        String a = "acbabd";
        int[] arr = new int[26];
        for (int i1 = 0; i1 < a.length(); i1++) {
            char c = a.charAt(i1);
            int i2 = c - 97;
            arr[i2]++;
        }
        System.out.println(Arrays.toString(arr));
    }
}
