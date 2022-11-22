package edu.cscc.topics.quality.unit;


import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class SampleJUnitTest {
    @Test
    public void simpleTest() {
        // JUnit has some convenience methods (like assertEquals) that compares to objects and gives a reasonable
        // error message if they are not (and fails the test)
        assertEquals("a.b", new SimpleJoiner().join(".", "a", "b"));
    }

    @Test
    public void shouldSkipNulls() {
        // Let's fix the code for this test
         assertEquals("a.b", new SimpleJoiner().join(".", "a", null, "b"));
    }
}
