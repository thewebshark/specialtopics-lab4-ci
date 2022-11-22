package edu.cscc.topics.quality.unit;


public class DatabaseBackedMeaningOfLife implements MeaningOfLifeMachine {

    @Override
    public String getMeaning() {
        throw new RuntimeException("Silly rabbit.  You don't hook up to a real database in a unit test setting.  That would be a bad idea!");
    }
}
