package com.example.aclient.util;

import com.google.common.collect.Lists;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class SplitUtil {


    public static void main(String[] args) {
        int parallel = 2;
        List<Info> infos = infos();
        // 一个主任务的共享库都在同一个rds之下，只需要关注数据库名即可
        Map<String, List<Info>> map = infos.stream()
                .collect(Collectors.groupingBy(Info::getName));
        // 加入分组索引
        AtomicInteger count = new AtomicInteger(parallel - 1);
        // 按照parallel分组
        map.forEach((s, groupInfos) -> {
            groupInfos.forEach(u -> u.setIndex(count.get()));
            count.set(count.get() - 1);
            if (count.get() < 0) {
                count.set(parallel - 1);
            }
        });

        map.forEach((s, i) -> i.forEach(info -> System.out.println(s + " ====》 " + info.index)));
    }

    public static List<Info> infos() {
        List<Info> list = Lists.newArrayList();

        for (int i = 0; i < 50; i++) {
            list.add(new Info("00000"));
        }

        for (int i = 0; i < 50; i++) {
            list.add(new Info("00001"));
        }

        for (int i = 0; i < 50; i++) {
            list.add(new Info("00002"));
        }

        return list;
    }

}
