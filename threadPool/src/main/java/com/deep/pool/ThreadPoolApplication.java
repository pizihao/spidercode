package com.deep.pool;

import cn.hippo4j.core.enable.EnableDynamicThreadPool;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableDynamicThreadPool
@EnableAsync
public class ThreadPoolApplication {

    public static void main(String[] args) {
        SpringApplication.run(ThreadPoolApplication.class, args);
    }

}
