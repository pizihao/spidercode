package com.example.druidtest;

public class test {

    public static void main(String[] args) {
        Integer a = 1;

        System.out.println(KiMemoryUnit.of("Mi").toGigabytes(1048576));
        long l = KiMemoryUnit.MEGABYTES.toGigabytes(1048576);
        System.out.println(l);

    }
}
