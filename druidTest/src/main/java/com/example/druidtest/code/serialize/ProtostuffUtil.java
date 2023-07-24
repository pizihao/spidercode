package com.example.druidtest.code.serialize;

import com.example.druidtest.model.DeriveDO;
import com.example.druidtest.model.Model;
import com.example.druidtest.model.entity.SimpleRequest;
import io.protostuff.LinkedBuffer;
import io.protostuff.ProtostuffIOUtil;
import io.protostuff.runtime.RuntimeSchema;

import java.util.Arrays;
import java.util.UUID;


public class ProtostuffUtil {

    public static void main(String[] args) {
        String key = UUID.randomUUID().toString();

        DeriveDO deriveDO = new DeriveDO();
        deriveDO.setName("中文");
        deriveDO.setId(1);
        deriveDO.setAddress("测试中文序列化");
        Model model = new Model();
        model.setId(5);
        model.setName("test");

        SimpleRequest<DeriveDO> request = new SimpleRequest<>(key, deriveDO);
        request.appendHeader("P-TIME-CODE", "timeStamp");
        request.appendHeader("P-TYPE", 50);
        request.appendHeader("M-MODEL", model);


        SimpleRequest<Model> request1 = new SimpleRequest<>(key, model);
        request1.appendHeader("P-TIME-CODE", "timeStamp");
        request1.appendHeader("P-TYPE", 50);
        request1.appendHeader("M-MODEL", deriveDO);


        RuntimeSchema<SimpleRequest> schema = RuntimeSchema.createFrom(SimpleRequest.class);

        byte[] bytes = ProtostuffIOUtil.toByteArray(request, schema, LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
        byte[] bytes1 = ProtostuffIOUtil.toByteArray(request1, schema, LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE));
        System.out.println(Arrays.toString(bytes));
        System.out.println(Arrays.toString(bytes1));

        SimpleRequest message = schema.newMessage();
        ProtostuffIOUtil.mergeFrom(bytes, message, schema);
        SimpleRequest message1 = schema.newMessage();
        ProtostuffIOUtil.mergeFrom(bytes1, message1, schema);

        System.out.println(message);
        System.out.println(message1);
    }
}
