package com.deep.flink;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;



/**
 * 监听端口的测试
 */
public class FlinkToPort {

    public static void main(String[] args) throws Exception {
        //获取Flink批处理执行环境
        StreamExecutionEnvironment environment = StreamExecutionEnvironment.getExecutionEnvironment();

        final String host = "localhost";
        final int port = 49664;
        //从socket中获取数据源
        DataStreamSource<String> source = environment.socketTextStream(host, port);
        //单词计数
        source
                //将一行句子按照空格拆分,输入一个字符串,输出一个2元组,key为一个单词,value为1
                .flatMap((FlatMapFunction<String, Object>) (s, collector) -> {
                    //对读取到的每一行数据按照空格分割
                    String[] split = s.split(" ");
                    //将每个单词放入collector中作为输出,格式类似于{word:1}
                    for (String word : split) {
                        collector.collect(new Tuple2<>(word, 1));
                    }
                })
                .returns(Object.class)
                .print();

        environment.execute();
    }

}
