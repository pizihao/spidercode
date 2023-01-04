package com.binder.element;

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Type;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class SimpleElementTest {

    private SimpleElement simpleElementUnderTest;

    @Before
    public void setUp() {
        simpleElementUnderTest = new SimpleElement();
    }

    @Test
    public void testIsSupport() {
        // Setup
        final Type type = String.class;

        // Run the test
        final boolean result = simpleElementUnderTest.isSupport(type);

        // Verify the results
        assertTrue(result);

        final Type type1 = Object.class;

        final boolean result1 = simpleElementUnderTest.isSupport(type1);

        assertFalse(result1);
    }
}
