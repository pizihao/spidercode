package com.example.aliyuntest.make;

import cn.hutool.core.collection.ListUtil;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test<T extends MakeTest & CallTest> {


    public static boolean isContinuous(Collection<Integer> collection) {
        List<Integer> collect = collection.stream().distinct().sorted().collect(Collectors.toList());
        int max = -1;
        int min = -1;
        for (Integer i : collect) {
            if (i != 0) {
                if (i > max || max == -1) {
                    max = i;
                }
                if (i < min || min == -1) {
                    min = i;
                }
            }
        }
        return max - min <= collect.size() - 1;
    }

    public static void main(String[] args) {

        //just方法直接去声明相关的元素
        Flux<Integer> flux1 = Flux.just(1, 2, 3, 4);
        Flux<Integer> flux2 = Flux.just(5, 6, 7, 8);
        Flux<Integer> flux3 = Flux.just(9, 10, 11, 12);

        List<Integer> block = Flux.zip(flux1, flux2, flux3)
                .flatMap(Flux::fromIterable)
                .map(Integer.class::cast)
                .collectList().block();
        System.out.println(block);

        Mono.just(1);

        //其他的方法 数组形式数据流
        Integer[] array = {1, 2, 3, 4};
        Flux.fromArray(array);

        //list集合形式数据流
        List<Integer> list = Arrays.asList(array);
        Flux.fromIterable(list);

        //stream形式数据流
        Stream<Integer> stream = list.stream();
        Flux.fromStream(stream);
    }

}
