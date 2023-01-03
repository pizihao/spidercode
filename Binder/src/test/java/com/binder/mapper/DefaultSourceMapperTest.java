package com.binder.mapper;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DefaultSourceMapperTest {

    private DefaultSourceMapper defaultSourceMapperUnderTest;

    @Before
    public void setUp() {
        defaultSourceMapperUnderTest = new DefaultSourceMapper();
    }

    @Test
    public void testConvert() {
        assertEquals("resultCode", defaultSourceMapperUnderTest.convert("result-code"));
    }

    @Test
    public void testReverseConvert() {
        assertEquals("result-code", defaultSourceMapperUnderTest.reverseConvert("resultCode"));
    }
}
