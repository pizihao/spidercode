package com.deep.pool.config;

import cn.hippo4j.core.executor.DynamicThreadPool;
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

    @Bean("masterExecutorService")
    @DynamicThreadPool
    public ExecutorService pimExecutorService() {
        // 线程工厂
        ThreadFactory factory = new CustomizableThreadFactory("Master-thread-");
        return getExecutorService(factory);
    }

    @Bean("ExecutorService")
    @DynamicThreadPool
    public ExecutorService commonExecutorService() {
        // 线程工厂
        ThreadFactory factory = new CustomizableThreadFactory("Common-thread-");
        return getExecutorService(factory);
    }

    /**
     * <h2>通过配置生成一个线程池</h2>
     *
     * @param factory 线程池工厂
     * @return java.util.concurrent.ExecutorService
     * @author liuwenhao
     * @date 2022/1/14 13:56
     */
    private ExecutorService getExecutorService(ThreadFactory factory) {
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