package edu.cscc.topics.quality.unit;

public class MeaningOfLifeFinder {
    private MeaningOfLifeMachine myMachine;

    public MeaningOfLifeFinder(MeaningOfLifeMachine machine) {
        this.myMachine = machine;
    }

    public String tellMeTheMeaningOfLife() {
        return "The true meaning of life is: " + myMachine.getMeaning();
    }
}
