package com.binder.source;

import com.binder.element.Elements;
import com.binder.mapper.SourceMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class MapPropertiesSourceTest {

    @Mock
    private SourceMapper mockSourceMapper;

    private MapPropertiesSource mapPropertiesSourceUnderTest;

    @Before
    public void setUp() {
        mapPropertiesSourceUnderTest = new MapPropertiesSource(new HashMap<>(), mockSourceMapper, "prefix",
                Arrays.asList());
    }

    @Test
    public void testGetResult() {
        // Setup
        final Elements elements = new Elements(Arrays.asList());

        // Run the test
        final String result = mapPropertiesSourceUnderTest.getResult(String.class, elements);

        // Verify the results
        assertEquals("result", result);
    }

    @Test
    public void testGetSourceValue() {
        // Setup
        final List<SourceName> expectedResult = Arrays.asList(new SourceName("prefix", "s", 0, "o", null));

        // Run the test
        final List<SourceName> result = mapPropertiesSourceUnderTest.getSourceValue();

        // Verify the results
        assertEquals(expectedResult, result);
    }
}
