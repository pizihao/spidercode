package com.binder.element;

import com.binder.ElementUnit;
import com.binder.mapper.DefaultSourceMapper;
import com.binder.source.SourceName;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.*;

public class ArrayElementTest {

    private ArrayElement arrayElementUnderTest;

    @Before
    public void setUp() {
        arrayElementUnderTest = new ArrayElement();
    }

    @Test
    public void testIsSupport() {
        final Type type = String.class;
        final boolean result = arrayElementUnderTest.isSupport(type);
        assertFalse(result);

        final Type type1 = String[].class;
        final boolean result1 = arrayElementUnderTest.isSupport(type1);
        assertTrue(result1);
    }

}
