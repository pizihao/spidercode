package com.deep.pool;

import cn.hippo4j.core.enable.EnableDynamicThreadPool;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * -XX:+PrintGC -Xmn880M -Xms2048M -XX:SurvivorRatio=8 -XX:MetaspaceSize=256M -XX:+UseConcMarkSweepGC -XX:+UseCMSCompactAtFullCollection
 * -Drebel.notification.url=http://localhost:17434 -javaagent:C:\SoftWare\jetbrains\apps\IDEA-U\ch-0\223.7571.182.plugins\jr-ide-idea\lib\xrebel\xrebel.jar
 */
@SpringBootApplication
@EnableDynamicThreadPool
public class ThreadPoolApplication {

    public static void main(String[] args) {
        SpringApplication.run(ThreadPoolApplication.class, args);
    }

}
