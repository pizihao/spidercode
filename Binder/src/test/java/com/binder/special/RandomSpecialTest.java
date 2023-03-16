package com.binder.special;

import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;

public class RandomSpecialTest {

    private RandomSpecial randomSpecialUnderTest;

    @Before
    public void setUp() {
        randomSpecialUnderTest = new RandomSpecial();
    }

    @Test
    public void testGetPrefix() {
        assertEquals("random.", randomSpecialUnderTest.getPrefix());
    }

    @Test
    public void testGetProperty() {
        // Setup

        // Run the test
        final Object result = randomSpecialUnderTest.getProperty("name");

        // Verify the results
    }

    @Test
    public void testApply() {
        // Setup

        // Run the test
        final Object result = randomSpecialUnderTest.apply("s");

        // Verify the results
    }
}
