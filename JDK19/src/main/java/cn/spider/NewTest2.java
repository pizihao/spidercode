package cn.spider;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class NewTest2 {
    public static void main(String[] args) {
        List<Integer> list1 = List.of(1, 2, 3, 4, 5);
        List<Integer> listResult = list1.stream().dropWhile(x -> x < 3).collect(Collectors.toList());
        System.out.println(listResult);

        List<Integer> list2 = List.of(1, 2, 3, 4, 5);
        List<Integer> listResult2 = list2.stream().takeWhile(x -> x < 3).collect(Collectors.toList());
        System.out.println(listResult2);
        Stream<Integer> stream = Stream.of(1, 2, null);
        stream.forEach(System.out::print);
        stream = Stream.ofNullable(null);
        stream.forEach(System.out::print);
        IntStream.iterate(0, x -> x < 10, x -> x + 1).forEach(System.out::print);
    }
}
