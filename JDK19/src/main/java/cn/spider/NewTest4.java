package cn.spider;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class NewTest4 {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();

        ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor();
        AtomicInteger count = new AtomicInteger(0);
        for (int i = 0; i < 100_0000; i++) {
            executorService.execute(() -> {
                try {
                    count.incrementAndGet();
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }
        while (count.get() == 100_0000){
            System.out.println("耗时:" + (System.currentTimeMillis() - start) + "ms");
        }

//        try (var executor = Executors.newFixedThreadPool(100)) {
//            IntStream.range(0, 100).forEach(i -> {
//                executor.submit(() -> {
//                    Thread.sleep(1000);
//                    return i;
//                });
//            });
//        } // executor.close() 会被自动调用
            // 提交了 10 万个虚拟线程，每个线程休眠 1 秒钟，1秒左右完成
    }

}
