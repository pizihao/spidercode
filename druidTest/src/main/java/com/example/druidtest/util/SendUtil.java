package com.example.druidtest.util;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class SendUtil {

    static String a = "[\n" +
            "  {\n" +
            "    \"jobName\": \"数据库升级服务的自定义会员定时任务\",\n" +
            "    \"jobDescription\": \"数据库升级服务的自定义会员定时任务\",\n" +
            "    \"appId\": 79,\n" +
            "    \"memberGroup\": \"\",\n" +
            "    \"serverType\": \"database-update\",\n" +
            "    \"jobParams\": \"123\",\n" +
            "    \"timeExpressionType\": \"CRON\",\n" +
            "    \"timeExpression\": \"0/2 * * * * ?\",\n" +
            "    \"processorInfo\": \"com.differ.jackyun.database.update.component.job.DataBaseCustomMemberProcessor\",\n" +
            "    \"maxInstanceNum\": \"1\",\n" +
            "    \"concurrency\": 5,\n" +
            "    \"instanceTimeLimit\": 0,\n" +
            "    \"instanceRetryNum\": 0,\n" +
            "    \"taskRetryNum\": 1,\n" +
            "    \"enable\": true,\n" +
            "    \"jobType\": \"1\"\n" +
            "  }\n" +
            "]\n";

    static String b = "sss";

    static Map<String, IBuffer<String>> map = new ConcurrentHashMap<>(32);

    public static void main(String[] args) {


        ForkJoinPool joinPool = ForkJoinPool.commonPool();

        for (long i = 0; i < 50000000000l; i++) {
            joinPool.execute(() -> {
                IBuffer<String> buffer;
                boolean ackFlag = false;
                int ackCount = 5;
                while (ackCount-- > 0 && !ackFlag) {
                    buffer = buildRunnable(b);
                    ackFlag = buffer.write(a);
                }
            });
        }

        while (true){
            LockSupport.parkNanos(TimeUnit.MINUTES.toNanos(10L));
        }

    }

    private static synchronized IBuffer<String> buildRunnable(String mark) {
        if (map.containsKey(mark)) {
            IBuffer<String> runnable = map.get(mark);
            if (runnable.state() != IBuffer.ACTIVE) {
                map.remove(mark);
            } else {
                return runnable;
            }
        }
        IBuffer<String> buffer = new Buffer(64, 0.9f, 10);
        map.put(mark, buffer);
        return buffer;
    }

}
