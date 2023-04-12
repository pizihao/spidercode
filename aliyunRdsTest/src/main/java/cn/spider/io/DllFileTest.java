package cn.spider.io;

import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class DllFileTest {

    private static final int[] INTS = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23};
    public static void main(String[] args) {
        List<TimeInterval> timeIntervals = Lists.newArrayList();

        timeIntervals.add(new TimeInterval("2021-01-01 01:30:00", "2021-01-01 04:30:00"));
        timeIntervals.add(new TimeInterval("2021-01-01 03:30:00", "2021-01-01 06:30:00"));
        timeIntervals.add(new TimeInterval("2021-01-01 07:30:00", "2021-01-01 10:30:00"));

        List<TimeInterval> intervals = timeHandler(timeIntervals);
        System.out.println(intervals);


        StringTokenizer tokenizer = new StringTokenizer("435135,465133,68451", ",");
        int counted = tokenizer.countTokens();
        System.out.println(counted);
        System.out.println(tokenizer.nextToken());

        LocalDateTime dateTime1 = LocalDateTime.of(2021, 1, 1, 1, 30, 0);
        LocalDateTime dateTime2 = LocalDateTime.of(2021, 1, 1, 1, 30, 0);
        System.out.println(dateTime1.isAfter(dateTime2));
        System.out.println(dateTime1.isBefore(dateTime2));
        System.out.println(dateTime1.isEqual(dateTime2));


        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        // 开始的 日 单位，在一个完整的 日 的周期内 如果找不到合适的时间点，则表示时间段设置不合法
        int day = calendar.get(Calendar.DAY_OF_YEAR);
        System.out.println(day);

        System.out.println(Integer.toBinaryString((1 << 24) - 1).length());


        BitSet bitSet = new BitSet(24);
        bitSet.set(8, 14);
        bitSet.set(3, 5);
        System.out.println(Arrays.toString(bitSet.toLongArray()));
        System.out.println(bitSet);
        StringBuilder builder = Arrays.stream(INTS)
                .collect(StringBuilder::new, (sb, value) -> {
                    if (bitSet.get(value)) {
                        sb.append(1);
                    } else {
                        sb.append(0);
                    }
                }, StringBuilder::append);
        System.out.println(builder);

        System.out.println("===================");
        StringTokenizer tokenizer1 = new StringTokenizer("43513546513368451", "");
        int counted1 = tokenizer1.countTokens();
        System.out.println(counted1);
        System.out.println(tokenizer1.nextToken());
    }

    public static List<TimeInterval> timeHandler(List<TimeInterval> intervals) {
        List<TimeInterval> list = new ArrayList<>();
        if (intervals.isEmpty()) {
            return Collections.emptyList();
        }
        List<TimeInterval> timeIntervals = intervals.stream()
                .sorted(Comparable::compareTo)
                .collect(Collectors.toList());

        TimeInterval interval = timeIntervals.get(0);
        LocalDateTime start = interval.getStartTime();
        LocalDateTime end = interval.getEndTime();
        for (int i = 1; i < timeIntervals.size(); i++) {
            // 合并
            TimeInterval timeInterval = timeIntervals.get(i);
            LocalDateTime startTime = timeInterval.getStartTime();
            LocalDateTime endTime = timeInterval.getEndTime();
            if (startTime.isBefore(end) || startTime.isEqual(end)) {
                end = endTime.isAfter(end) ? endTime : end;
            } else {
                list.add(new TimeInterval(start, end));
                start = startTime;
                end = endTime;
            }
        }
        list.add(new TimeInterval(start, end));
        return list;
    }


    @Setter
    @Getter
    private static final class TimeInterval implements Comparable<TimeInterval> {

        private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime startTime;
        LocalDateTime endTime;

        public TimeInterval(LocalDateTime startTime, LocalDateTime endTime) {
            this.startTime = startTime;
            this.endTime = endTime;
        }

        public TimeInterval(String timeStr1, String timeStr2) {
            LocalDateTime time1 = LocalDateTime.parse(timeStr1, formatter);
            LocalDateTime time2 = LocalDateTime.parse(timeStr2, formatter);
            // 较小的时间作为开始时间
            if (time1.isBefore(time2)) {
                this.startTime = time1;
                this.endTime = time2;
            } else {
                this.startTime = time2;
                this.endTime = time1;
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            TimeInterval that = (TimeInterval) o;
            return Objects.equals(startTime, that.startTime) && Objects.equals(endTime, that.endTime);
        }

        @Override
        public int hashCode() {
            return Objects.hash(startTime, endTime);
        }

        @Override
        public String toString() {
            return startTime.format(formatter) + "," + endTime.format(formatter);
        }

        @Override
        public int compareTo(TimeInterval o) {
            return this.startTime.compareTo(o.getStartTime());
        }
    }
}
