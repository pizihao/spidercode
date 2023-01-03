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
}
