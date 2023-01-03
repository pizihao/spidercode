package com.binder.properties;

import com.binder.source.MapPropertiesSource;
import com.binder.source.Source;

import java.util.HashMap;
import java.util.Map;

public class Tests {

    public static void main(String[] args) {
        Map<String, Object> a = new HashMap<>();
        a.put("spring.application.name", "threadPool");
        a.put("spring.dynamic.thread-pool.active-alarm", 80);
        a.put("spring.dynamic.thread-pool.alarm", false);
        a.put("spring.dynamic.thread-pool.alarm-interval", 8);
        a.put("spring.dynamic.thread-pool.banner", true);
        a.put("spring.dynamic.thread-pool.capacity-alarm", 80);
        a.put("spring.dynamic.thread-pool.check-state-interval", 3000);
        a.put("spring.dynamic.thread-pool.collect", true);
        a.put("spring.dynamic.thread-pool.config-file-type", "yaml");
        a.put("spring.dynamic.thread-pool.enable", false);
        a.put("spring.dynamic.thread-pool.executors[0].active-alarm", 80);
        a.put("spring.dynamic.thread-pool.executors[0].alarm", true);
        a.put("spring.dynamic.thread-pool.executors[0].allow-core-thread-time-out", true);
        a.put("spring.dynamic.thread-pool.executors[0].blocking-queue", "LinkedBlockingQueue");
        a.put("spring.dynamic.thread-pool.executors[0].capacity-alarm", 80);
        a.put("spring.dynamic.thread-pool.executors[0].core-pool-size", 1);
        a.put("spring.dynamic.thread-pool.executors[0].execute-time-out", 1000);
        a.put("spring.dynamic.thread-pool.executors[0].keep-alive-time", 1024);
        a.put("spring.dynamic.thread-pool.executors[0].maximum-pool-size", 1);
        a.put("spring.dynamic.thread-pool.executors[0].queue-capacity", 1);
        a.put("spring.dynamic.thread-pool.executors[0].rejected-handler", "AbortPolicy");
        a.put("spring.dynamic.thread-pool.executors[0].thread-name-prefix", "message-consume");
        a.put("spring.dynamic.thread-pool.executors[0].thread-pool-id", "message-consume");
        a.put("spring.dynamic.thread-pool.executors[1].active-alarm", 80);
        a.put("spring.dynamic.thread-pool.executors[1].alarm", true);
        a.put("spring.dynamic.thread-pool.executors[1].allow-core-thread-time-out", true);
        a.put("spring.dynamic.thread-pool.executors[1].blocking-queue", "LinkedBlockingQueue");
        a.put("spring.dynamic.thread-pool.executors[1].capacity-alarm", 80);
        a.put("spring.dynamic.thread-pool.executors[1].core-pool-size", 3);
        a.put("spring.dynamic.thread-pool.executors[1].execute-time-out", 1000);
        a.put("spring.dynamic.thread-pool.executors[1].keep-alive-time", 1024);
        a.put("spring.dynamic.thread-pool.executors[1].maximum-pool-size", 10);
        a.put("spring.dynamic.thread-pool.executors[1].nodes", "192.168.137.1:58866");
        a.put("spring.dynamic.thread-pool.executors[1].queue-capacity", 1);
        a.put("spring.dynamic.thread-pool.executors[1].rejected-handler", "AbortPolicy");
        a.put("spring.dynamic.thread-pool.executors[1].thread-name-prefix", "message-produce");
        a.put("spring.dynamic.thread-pool.executors[1].thread-pool-id", "message-produce");
        a.put("spring.dynamic.thread-pool.nacos.data-id", "AbortPolicy");
        a.put("spring.dynamic.thread-pool.nacos.group", "DEFAULT_GROUP");
        a.put("spring.dynamic.thread-pool.notify-platforms[0].platform", "WECHAT");
        a.put("spring.dynamic.thread-pool.notify-platforms[0].token", "xxx");
        a.put("spring.dynamic.thread-pool.notify-platforms[1].platform", "DING");
        a.put("spring.dynamic.thread-pool.notify-platforms[1].secret", "xxx");
        a.put("spring.dynamic.thread-pool.notify-platforms[1].token", "xxx");
        a.put("spring.dynamic.thread-pool.notify-platforms[2].platform", "LARK");
        a.put("spring.dynamic.thread-pool.notify-platforms[2].token", "xxx");
        a.put("spring.dynamic.thread-pool.tomcat.core-pool-size", .120);
        a.put("spring.dynamic.thread-pool.tomcat.enable", true);
        a.put("spring.dynamic.thread-pool.tomcat.keep-alive-time", 1007);
        a.put("spring.dynamic.thread-pool.tomcat.maximum-pool-size", 200);
        a.put("spring.dynamic.thread-pool.tomcat.nodes", "*:*");

        Source source = new MapPropertiesSource(a, "spring.dynamic.thread-pool");
        source.getSourceValue().forEach(s -> {
            if (s.getIndex() != -1) {
                System.out.println(s.getQualifiedName());
            }
        });

        BootstrapConfigProperties result = source.getResult(BootstrapConfigProperties.class);
        System.out.println(result);

    }

}
