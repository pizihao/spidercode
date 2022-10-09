package com.deep.flink;

import org.apache.flink.api.common.RuntimeExecutionMode;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.operators.DataSource;
import org.apache.flink.streaming.api.datastream.DataStreamSink;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.CloseableIterator;

import java.util.Arrays;
import java.util.List;

/**
 * @author Create by liuwenhao on 2022/9/21 16:35
 */
public class DataConvert {

    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        env.setRuntimeMode(RuntimeExecutionMode.AUTOMATIC);
        DataStreamSource<String> elementsSource = env.fromElements(
                "java,scala,php,c++",
                "java,scala,php",
                "java,scala",
                "java"
        );

        CloseableIterator<String[]> iterator = elementsSource.filter(value -> value.length() > 5)
                .map(value -> value.split(","))
                .executeAndCollect();


        while (iterator.hasNext()){
            String[] next = iterator.next();
            System.out.println(Arrays.toString(next));
        }
    }

}
