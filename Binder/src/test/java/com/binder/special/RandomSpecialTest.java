package com.binder.special;

import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class RandomSpecialTest {

    private RandomSpecial randomSpecialUnderTest;

    @Before
    public void setUp() {
        randomSpecialUnderTest = new RandomSpecial();
        randomSpecialUnderTest.random = mock(Random.class);
    }

    @Test
    public void testGetPrefix() {
        assertEquals("random.", randomSpecialUnderTest.getPrefix());
    }

    @Test
    public void testGetProperty() {
        // Setup
        when(randomSpecialUnderTest.random.nextInt()).thenReturn(0);
        when(randomSpecialUnderTest.random.nextLong()).thenReturn(0L);
        when(randomSpecialUnderTest.random.nextInt(0)).thenReturn(0);

        // Run the test
        final Object result = randomSpecialUnderTest.getProperty("name");

        // Verify the results
        verify(randomSpecialUnderTest.random).nextBytes(any(byte[].class));
    }

    @Test
    public void testApply() {
        // Setup
        when(randomSpecialUnderTest.random.nextInt()).thenReturn(0);
        when(randomSpecialUnderTest.random.nextLong()).thenReturn(0L);
        when(randomSpecialUnderTest.random.nextInt(0)).thenReturn(0);

        // Run the test
        final Object result = randomSpecialUnderTest.apply("s");

        // Verify the results
        verify(randomSpecialUnderTest.random).nextBytes(any(byte[].class));
    }
}
