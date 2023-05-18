package com.deep.pool.config;

import cn.hippo4j.core.executor.DynamicThreadPool;
import cn.hippo4j.core.executor.support.ThreadPoolBuilder;
import org.apache.commons.lang3.time.StopWatch;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.*;
import java.util.concurrent.locks.LockSupport;

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
     * @author pizihao
     * @date 2022/1/14 13:56
     */
    private static ThreadPoolExecutor getExecutorService(ThreadFactory factory) {
        // 核心线程数
        int coreSize = 160;
        // 线程池最大值
        int mixSize = 160;
        // 线程保存时间 (秒：s)
        long keepAliveTime = 20;
        // 等待队列大小
        int capacitySize = 2;

        // 配置线程池
        return new ThreadPoolExecutor(
                coreSize,
                mixSize,
                keepAliveTime,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(capacitySize),
                factory,
                new ThreadPoolExecutor.AbortPolicy()
        );
    }

    public static void main(String[] args) {
        StopWatch stopWatch = StopWatch.createStarted();


        ThreadPoolExecutor service = getExecutorService(Executors.defaultThreadFactory());
        for (int i = 0; i < 200; i++) {
            LockSupport.parkNanos(1);
            service.execute(() -> {
                LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(2L));
            });
        }stopWatch.split();
        System.out.println();
        System.out.println(stopWatch.getNanoTime());
    }

}