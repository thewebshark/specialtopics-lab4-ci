package edu.cscc.topics.quality.unit;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class SampleMockTest {

    @Test
    public void dontTestUsingARealDependency() {
        // Using a "real" database in a unit test is a setup for disaster (what if the database goes down?)
        MeaningOfLifeMachine realMachine = new DatabaseBackedMeaningOfLife();
        MeaningOfLifeFinder finder = new MeaningOfLifeFinder(realMachine);

        // Run this test to see the type of things that happen when the external system is misbehaving
        //Assertions.assertThat(finder.tellMeTheMeaningOfLife()).contains("42");
    }

    @Test
    public void testUsingAMockObject() {
        //Instead, you can "mock" the object that your class under test depends on
        MeaningOfLifeMachine mockMachine = Mockito.mock(MeaningOfLifeMachine.class);
        when(mockMachine.getMeaning()).thenReturn("42");

        MeaningOfLifeFinder finder = new MeaningOfLifeFinder(mockMachine);

        Assertions.assertThat(finder.tellMeTheMeaningOfLife().contains("42"));
        verify(mockMachine).getMeaning();

    }
}
