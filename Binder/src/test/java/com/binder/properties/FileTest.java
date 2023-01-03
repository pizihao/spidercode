package com.binder.properties;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class FileTest {

    public static void main(String[] args) throws IOException {

        String path = "C:\\Users\\liuwenhao\\TEST";
        File file1 = new File(" \n\rC:\\Users\\liuwenhao\\OK");
        File file = new File(path, file1.getName());
        boolean directory = file1.isDirectory();
        System.out.println(file1.getName());
        System.out.println(Arrays.toString(file.list()));
    }

}
