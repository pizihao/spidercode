package com.example.druidtest.util;

import com.google.protobuf.ServiceException;

import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class MapUtil {

    static ForkJoinPool joinPool = ForkJoinPool.commonPool();

    static Map<String, Integer> map = new ConcurrentHashMap<>(32);

    public static void main(String[] args) {
        for (int i = 0; i < 100000; i++) {
            int finalI = i;
            joinPool.execute(() -> {
                String mark = String.valueOf(new Random().nextInt(10));
                Integer buffer;
                boolean ackFlag = false;
                int ackCount = 5;
                while (ackCount-- > 0 && !ackFlag) {
                    buffer = buildRunnable(mark, finalI);
                    ackFlag = write(buffer);
                }
                if (ackFlag) {
                    System.out.println("成功" + finalI);
                }else {
                    System.out.println("失败");
                }
            });
        }

        while (true) {

            LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(40L));
        }
    }

    public static boolean write(int i) {
        int nexted = new Random().nextInt(5);
        if (i % 3 == 0) {
            LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(4L));
        }

        return nexted > 2;
    }

    public static Integer buildRunnable(String mark, int i) {
        while (map.size() >= 10) {
            LockSupport.parkNanos(TimeUnit.MILLISECONDS.toNanos(200L));
            clearBySize();
        }
        if (map.containsKey(mark)) {
            return map.get(mark);
        }
        map.put(mark, i);
        return i;
    }

    private static void clearBySize() {
        Map.Entry<String, Integer> entry = map.entrySet().stream().min((f, l) -> Math.toIntExact(f.getValue() - l.getValue())).orElse(null);
        if (entry == null) {
            return;
        }
        map.remove(entry.getKey());
        LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(4L));
    }
}
