package edu.cscc.topics.quality.unit;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.Assert.assertEquals;

public class SampleAssertJTest {

    private List<String> strings;

    @Before
    public void setup() {
        // Frequently you need to do the same thing before each test is run.  @Before does just that
        strings = new ArrayList<>();
    }

    @Test
    public void nonEmptyListHasNonZeroLength() {
        strings.add("a");
        strings.add("b");

        // Junit way - IDE can't help with the assertion
        assertEquals(2, strings.size());

        // AssertJ way - Note that this is based on the fact that ArrayList is a List and has a size() method.
        // Testing collections is so common that assertJ handles it natively
        assertThat(strings).hasSize(2);
    }

    @Test
    public void beingEfficientWithAssertJ() {
        strings.add("a");
        strings.add("b");
        strings.add("c");

        // Another AssertJ nicety - methods from the class under test are directly available
        // This is true regardless of whether the class is part of the standard library or not
        // In this example you can say assertThat(strings).size() and it works
        assertThat(strings).size().isGreaterThanOrEqualTo(0);

        /* See how your IDE can help: uncomment the line below
           and hit <CTRL>-<ENTER> and see how it helps with
           command completion */
        //assertThat(strings).con

        /* Compare error output of the two tests below, by uncommenting and running them one at a time */

        // JUnit isn't too bad.  Clearly shows Expected vs. Actual (it gets more opaque with more complex examples)
        assertEquals(3, strings.size());

        // AssertJ output is pretty specific, including the value of strings itself so you can see what was gong on
        // Uncomment and run this test to see what I mean
         assertThat(strings).hasSize(3);


    }
}
