package com.example.aliyuntest.bit;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.roaringbitmap.RoaringBatchIterator;
import org.roaringbitmap.RoaringBitmap;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TimeBitTest {

    public static void main(String[] args) {
        // 时间范围 天，每天的1点到4点
        int[] ints = new int[]{
                1, 2, 3, 4
        };
        RoaringBitmap bitmapDay = RoaringBitmap.bitmapOf(ints);
        // 查看是否包含 1
//        System.out.println(bitmapDay.contains(1));
//        System.out.println(bitmapDay.contains(0));


        // 获取当前日期
        Date date = new Date();
        // 创建 Calendar 对象
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        // 获取当月的天数
        int daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        // 计算当月的小时数
        int hoursInMonth = daysInMonth * 24;

        int[] ints1 = new int[hoursInMonth];
        // 时间范围 月，每月的6号到10号
        for (int i = 5 * 24; i < 10 * 24 - 1; i++) {
            ints1[i] = i;
        }
        // 15号到20号
        for (int i = 14 * 24; i < 20 * 24 - 1; i++) {
            ints1[i] = i;
        }
        RoaringBitmap bitmapDay1 = RoaringBitmap.bitmapOf(ints1);
        int[] array = bitmapDay1.stream().toArray();
//        System.out.println(Arrays.toString(array));
//        System.out.println(bitmapDay1.toString());
//        System.out.println(LocalDateTime.now().getDayOfYear());

        RoaringBitmap bitmap = new TimeBitTest().getArray("2023-04-02 00:00:00", "2023-04-07 00:00:00", ChronoUnit.MONTHS);
//        System.out.println(bitmap.toString());
//        System.out.println(bitmap.toArray().length);
    }

    public RoaringBitmap getArray(String startTime, String endTime, ChronoUnit unit) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime startParse = LocalDateTime.parse(startTime, format);
        LocalDateTime endParse = LocalDateTime.parse(endTime, format);
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime firstDay = now;
        if (unit.equals(ChronoUnit.MONTHS)) {
            // 本月的第一天
            firstDay = now.withDayOfMonth(1);
        } else if (unit.equals(ChronoUnit.YEARS)) {
            // 今年的第一天
            firstDay = now.withDayOfYear(1);
        }
        firstDay = firstDay.withHour(0).withMinute(0).withSecond(0).withNano(0);
        // 从start - first 到 end - first 的值塞入到RoaringBitmap
        long startOffset = firstDay.until(startParse, ChronoUnit.MINUTES);
        long endOffset = firstDay.until(endParse, ChronoUnit.MINUTES);
        Node node = new Node(startOffset, endOffset);
        return compress(node);
    }

    public RoaringBitmap compress(Node... nodes) {
        RoaringBitmap bitmap = new RoaringBitmap();
        for (Node node : nodes) {
            long start = node.getStart();
            long end = node.getEnd();
            for (long i = start; i < end; i++) {
                bitmap.add((int) i);
            }
        }

        return bitmap;
    }


    @NoArgsConstructor
    @Setter
    @Getter
    @AllArgsConstructor
    static class Node {
        long start;
        long end;
    }
}
