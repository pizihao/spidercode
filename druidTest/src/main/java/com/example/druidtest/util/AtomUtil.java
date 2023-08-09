package com.example.druidtest.util;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;

public class AtomUtil {


    static AtomicInteger integer = new AtomicInteger(0);

    static volatile int a;


    public static void main(String[] args) {
        ForkJoinPool joinPool = ForkJoinPool.commonPool();

        for (long i = 0; i < 5000L; i++) {
            joinPool.execute(() -> {
                a++;
                LockSupport.parkNanos(10L);
                a--;
            });
        }
        LockSupport.parkNanos(TimeUnit.SECONDS.toNanos(20L));

        System.out.println(integer.get());

        while (true) {
            LockSupport.parkNanos(TimeUnit.MINUTES.toNanos(10L));
        }


    }
}
