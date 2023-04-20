package com.example.druidtest.config;

public class LoopTask {
    public static void main(String[] args) throws InterruptedException {
        // 1s = 1000000000ns，LockSupport仅支持暂停纳秒级，便于换算
        long convert = 1000000000L;
        // 等待时间，首次等待为2s
        long waitTime = convert * 2;
        /*
         * 在前10s内，每2s调用一次，95％的sql会在这个时间段内执行完成，
         * 在前60s内，每5s调用一次
         * 在前300s内，每30s调用一次
         * 剩下的每60s调用一次
         * 如果任务真正超时则等待时间为10分钟，总的调用次数为28次，消耗较小
         */
        // 使用<，最后一次放在循环外
        while (waitTime < 10 * 60 * convert) {
            // 先暂停线程
            System.out.println("等待时间 : " + waitTime / convert + "秒");
            // 等待时间按阶段递增
            if (waitTime < 10 * convert) {
                // 第一阶段，前10s
                waitTime += convert * 2;
            } else if (waitTime < 60 * convert) {
                // 第一阶段，前10s
                waitTime += convert * 5;
            } else if (waitTime < 5 * 60 * convert) {
                // 第一阶段，前5分钟
                waitTime += convert * 30;
            } else {
                waitTime += convert * 60;
            }
        }
        System.out.println(waitTime/convert);
    }
}
