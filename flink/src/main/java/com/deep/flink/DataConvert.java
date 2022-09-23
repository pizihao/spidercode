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


    public void test(){
        ExecutionEnvironment environment = ExecutionEnvironment.getExecutionEnvironment();
        //注意 readTextFile路径填你自己的文件路径。
        DataSource<String> testDataSet = environment.readTextFile("1.txt");
        //可以理解testDataSet为一个RDD
        //groupby(0)意思是以元组第一个为key进行分组，sum(1)是对元组第二个位置数据进行累加。
        val result = testDataSet.flatMap(_.split(" ")).map((_, 1)).groupBy(0).sum(1)
        result.print()
    }
    }

}
