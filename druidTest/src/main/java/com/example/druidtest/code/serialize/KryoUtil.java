package com.example.druidtest.code.serialize;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import com.esotericsoftware.kryo.util.DefaultInstantiatorStrategy;
import com.example.druidtest.model.DeriveDO;
import com.example.druidtest.model.Model;
import com.example.druidtest.model.entity.SimpleRequest;
import org.objenesis.strategy.StdInstantiatorStrategy;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.*;

public class KryoUtil {

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

        Kryo kryo = getInstance();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        Output output = new Output(stream);
        kryo.writeClassAndObject(output, request);
        output.flush();
        byte[] bytes = stream.toByteArray();
        System.out.println(Arrays.toString(bytes));

        Input input = new Input(new ByteArrayInputStream(bytes));
        Object simpleRequest = kryo.readClassAndObject(input);
        System.out.println(simpleRequest);
    }

    private static final String DEFAULT_ENCODING = "UTF-8";

    //每个线程的 Kryo 实例
    private static final ThreadLocal<Kryo> KRYO_LOCAL = ThreadLocal.withInitial(() -> {
        Kryo kryo = new Kryo();
        //支持对象循环引用（否则会栈溢出）
        kryo.setReferences(true); //默认值就是 true，添加此行的目的是为了提醒维护者，不要改变这个配置
        //不强制要求注册类（注册行为无法保证多个 JVM 内同一个类的注册编号相同；而且业务系统中大量的 Class 也难以一一注册）
        kryo.setRegistrationRequired(false); //默认值就是 false，添加此行的目的是为了提醒维护者，不要改变这个配置
        ((DefaultInstantiatorStrategy) kryo.getInstantiatorStrategy())
                .setFallbackInstantiatorStrategy(new StdInstantiatorStrategy());
        return kryo;
    });

    /**
     * 获得当前线程的 Kryo 实例
     *
     * @return 当前线程的 Kryo 实例
     */
    public static Kryo getInstance() {
        return KRYO_LOCAL.get();
    }

}
