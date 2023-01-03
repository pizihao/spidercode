package com.binder.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class StringUtilTest {

    @Test
    public void testIsBlank() {
        assertFalse(StringUtil.isBlank("str"));
    }

    @Test
    public void testIsEmpty() {
        assertFalse(StringUtil.isEmpty("str"));
    }

    @Test
    public void testToSymbolCase() {
        assertEquals("result", StringUtil.toSymbolCase("str", 'a'));
    }

    @Test
    public void testToCamelCase() {
        assertEquals("result", StringUtil.toCamelCase("str", 'a'));
    }

    @Test
    public void testReplace() {
        assertEquals("result", StringUtil.replace("str", "searchStr", "replaceStr"));
    }

    @Test
    public void testSplit() {
        assertArrayEquals(new String[]{"result"}, StringUtil.split("str", "separatorChars"));
        assertArrayEquals(new String[]{}, StringUtil.split("str", "separatorChars"));
    }

    @Test
    public void testStartWith() {
        assertFalse(StringUtil.startWith("str", "prefix"));
    }
}
