package cn.spider.aliyun;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 为RDS同步数据专门提供的线程池<br>
 * 考虑到在RDS部署的服务会不断增加，所以必须严格的控制每次发送数据的数量，使其能够更快的完成响应并返回结果<br>
 * 改配置存在两个定制化的线程池：<br>
 * <ol>
 *     <li>任务分配线程池，避免用户频繁点击造成内存溢出的情况，同时进行数据同步的任务数不宜过多，并交由一个单个线程完成分配工作</li>
 *     <li>任务执行线程池，按照当前及其CPU配置合理的定制线程池核心数</li>
 * </ol>
 *
 * @author lwh
 */
public class RdsInfoThreadPoolConfig {

    public static ThreadPoolExecutor rdsInfoBossExecutor = rdsInfoBossExecutor();
    public static ThreadPoolExecutor rdsInfoWorkerExecutor = rdsInfoWorkerExecutor();


    /**
     * 任务分配线程池的执行非常快，队列不应设置过多
     *
     * @return 任务分配线程池
     */
    public static ThreadPoolExecutor rdsInfoBossExecutor() {
        int processors = Runtime.getRuntime().availableProcessors();
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(
                processors * 2,
                processors * 4,
                60,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(600),
                new RdsInfoBossThreadFactory(),
                new RdsInfoRejectedExecutionHandler1());
        threadPoolExecutor.prestartAllCoreThreads();
        return threadPoolExecutor;
    }

    public static ThreadPoolExecutor rdsInfoWorkerExecutor() {
        int processors = Runtime.getRuntime().availableProcessors();
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(
                processors * 2,
                processors * 4,
                60,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(200),
                new RdsInfoWorkerThreadFactory(),
                new RdsInfoRejectedExecutionHandler());
        poolExecutor.prestartAllCoreThreads();
        return poolExecutor;
    }

    private static class RdsInfoBossThreadFactory implements ThreadFactory {

        private final AtomicInteger count = new AtomicInteger(0);

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r);
            String threadName = "rdsInfoBoss-" + ThreadPool.class.getSimpleName() + count.addAndGet(1);
            t.setName(threadName);
            return t;
        }
    }

    private static class RdsInfoWorkerThreadFactory implements ThreadFactory {

        private final AtomicInteger count = new AtomicInteger(0);

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r);
            String threadName = "rdsInfoWorker-" + ThreadPool.class.getSimpleName() + count.addAndGet(1);
            t.setName(threadName);
            return t;
        }
    }

    private static class RdsInfoRejectedExecutionHandler implements RejectedExecutionHandler {

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            throw new RuntimeException("任务已被拒接，请稍后重试" + Thread.currentThread().getName());
        }
    }

    private static class RdsInfoRejectedExecutionHandler1 implements RejectedExecutionHandler {

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            throw new RuntimeException("请稍后重试" + Thread.currentThread().getName());
        }
    }

}
