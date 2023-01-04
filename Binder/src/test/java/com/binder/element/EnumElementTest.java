package com.binder.element;

import com.binder.ElementUnit;
import com.binder.source.SourceName;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Type;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class EnumElementTest {

    private EnumElement enumElementUnderTest;

    @Before
    public void setUp() {
        enumElementUnderTest = new EnumElement();
    }

    @Test
    public void testIsSupport() {
        // Setup
        final Type type = String.class;

        // Run the test
        final boolean result = enumElementUnderTest.isSupport(type);

        // Verify the results
        assertFalse(result);
    }

    @Test
    public void testParser() {
        // Setup
        final ElementUnit elementUnit = new ElementUnit("prefix", "name",
                Arrays.asList(new SourceName("prefix", "elementName", 0, "obj", null)), null);
        final Elements elements = new Elements(Arrays.asList());

        // Run the test
        final String result = enumElementUnderTest.parser(elementUnit, elements);

        // Verify the results
        assertEquals("result", result);
    }
}
