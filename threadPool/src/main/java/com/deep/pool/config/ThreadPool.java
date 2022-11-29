package com.deep.pool.config;

import cn.hippo4j.core.executor.DynamicThreadPool;
import cn.hippo4j.core.executor.support.ThreadPoolBuilder;
import cn.hutool.core.thread.NamedThreadFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.CustomizableThreadFactory;

import java.util.concurrent.*;

/**
 * <h2></h2>
 *
 * @author Create by liuwenhao on 2022/8/30 13:35
 */
@Configuration
public class ThreadPool {

    @Bean
    @DynamicThreadPool
    public ThreadPoolExecutor messageConsumeDynamicExecutor() {
        String threadPoolId = "message-consume";
        return ThreadPoolBuilder.builder()
            .threadFactory(threadPoolId)
            .threadPoolId(threadPoolId)
            .dynamicPool()
            .build();
    }

    @Bean
    @DynamicThreadPool
    public ThreadPoolExecutor messageProduceDynamicExecutor() {
        String threadPoolId = "message-produce";

        return ThreadPoolBuilder.builder()
            .threadFactory(threadPoolId)
            .threadPoolId(threadPoolId)
            .dynamicPool()
            .build();
    }

    /**
     * <h2>通过配置生成一个线程池</h2>
     *
     * @param factory 线程池工厂
     * @return java.util.concurrent.ExecutorService
     * @author liuwenhao
     * @date 2022/1/14 13:56
     */
    private ThreadPoolExecutor getExecutorService(ThreadFactory factory) {
        // 核心线程数
        int coreSize = 16;
        // 线程池最大值
        int mixSize = 32;
        // 线程保存时间 (秒：s)
        long keepAliveTime = 20;
        // 等待队列大小
        int capacitySize = 16;

        // 配置线程池
        return new ThreadPoolExecutor(
            coreSize,
            mixSize,
            keepAliveTime,
            TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(capacitySize),
            factory,
            new ThreadPoolExecutor.CallerRunsPolicy()
        );
    }

}