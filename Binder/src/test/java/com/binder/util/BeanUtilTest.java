package com.binder.util;

import org.junit.Test;

import java.lang.reflect.Method;

import static org.junit.Assert.assertEquals;

public class BeanUtilTest {

    @Test
    public void testGetter() {
        // Setup
        final Method expectedResult = null;

        // Run the test
        final Method result = BeanUtil.getter(String.class, "propertiesName");

        // Verify the results
        assertEquals(expectedResult, result);
    }

    @Test
    public void testSetter() {
        // Setup
        final Method expectedResult = null;

        // Run the test
        final Method result = BeanUtil.setter(String.class, "propertiesName");

        // Verify the results
        assertEquals(expectedResult, result);
    }


    public static void main(String[] args) {


        System.out.println(Float.parseFloat(String.format("%.2f", 45.0)));

        Integer a = null;

        switch (a) {
            case 1:
                break;
            case 2:
                System.out.println(123);
            default:
                break;
        }

    }
}
