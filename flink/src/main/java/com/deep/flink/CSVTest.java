package com.deep.flink;

import com.alibaba.excel.EasyExcel;

import java.io.File;
import java.util.List;

public class CSVTest {

    static String fileName = "C:\\Users\\differ\\Desktop\\菜单.csv";

    public static void main(String[] args) {
        File file = new File(fileName);
        List<Object> list = EasyExcel.read(file)
                .sheet(0)
                .headRowNumber(1)
                .doReadSync();

        System.out.println(list);

    }

}
