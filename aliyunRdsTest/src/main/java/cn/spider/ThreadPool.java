package cn.spider;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 线程池
 *
 * @author 郑勇飞
 * @date 2018/1/30 14:19
 */
public class ThreadPool {


    public static ThreadPoolExecutor EXECUTOR;

    /**
     * 初始线程数
     */
    public static final int CORE_POOL_SIZE = Runtime.getRuntime().availableProcessors() * 2;

    /**
     * 最大线程数
     */
    public static final int MAX_POOL_SIZE = CORE_POOL_SIZE * 2;

    /**
     * 阻塞队列长度
     */
    public static final int WORK_QUEUE_CAPACITY = 50;

    static {
        new ThreadPool().init(CORE_POOL_SIZE, MAX_POOL_SIZE, WORK_QUEUE_CAPACITY);
    }

    /**
     * 线程池初始化方法
     * corePoolSize 核心线程池大小----10
     * maximumPoolSize 最大线程池大小----30
     * keepAliveTime 线程池中超过corePoolSize数目的空闲线程最大存活时间----30+单位TimeUnit
     * workQueue 阻塞队列----new ArrayBlockingQueue<Runnable>(10)====10容量的阻塞队列
     * threadFactory 新建线程工厂----new CustomThreadFactory()====定制的线程工厂
     * rejectedExecutionHandler 当提交任务数超过maxmumPoolSize+workQueue之和时,
     * 任务会交给RejectedExecutionHandler来处理
     */
    private void init(int corePoolSize, int maximumPoolSize, int capacity) {
        EXECUTOR = new ThreadPoolExecutor(
                corePoolSize,
                maximumPoolSize,
                60,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<Runnable>(capacity),
                new CustomThreadFactory(),
                new CustomRejectedExecutionHandler());

    }


    public void destory() {
        if (EXECUTOR != null) {
            EXECUTOR.shutdownNow();
        }
    }

    private class CustomThreadFactory implements ThreadFactory {

        private AtomicInteger count = new AtomicInteger(0);

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r);
            String threadName = "msg-" + ThreadPool.class.getSimpleName() + count.addAndGet(1);
            t.setName(threadName);
            return t;
        }
    }


    private class CustomRejectedExecutionHandler implements RejectedExecutionHandler {

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        }
    }


}
