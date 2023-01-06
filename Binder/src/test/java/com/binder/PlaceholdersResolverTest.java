package com.binder;

import com.binder.source.SourceName;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class PlaceholdersResolverTest {

    private PlaceholdersResolver placeholdersResolverUnderTest;

    @Before
    public void setUp() {
        placeholdersResolverUnderTest = new PlaceholdersResolver(
                Arrays.asList(new SourceName("prefix", "elementName", 0, "obj", null)), Arrays.asList());
    }

    @Test
    public void testGetSourceName() {
        // Setup
        final List<SourceName> expectedResult = Arrays.asList(new SourceName("prefix", "elementName", 0, "obj", null));

        // Run the test
        final List<SourceName> result = placeholdersResolverUnderTest.getSourceName();

        // Verify the results
        assertEquals(expectedResult, result);
    }
}
