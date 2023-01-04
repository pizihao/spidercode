package com.binder.element;

import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Type;
import java.util.List;

import static org.junit.Assert.*;

public class CollectionElementTest {

    private CollectionElement collectionElementUnderTest;

    @Before
    public void setUp() {
        collectionElementUnderTest = new CollectionElement();
    }

    @Test
    public void testIsSupport() {
        // Setup
        final Type type = List.class;

        // Run the test
        final boolean result = collectionElementUnderTest.isSupport(type);

        // Verify the results
        assertTrue(result);
    }

}
