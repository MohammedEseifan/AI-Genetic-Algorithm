package TextTest;

import GeneticAlgorithm.DNA;

/**
 * Created by Mohammed on 23-Jan-17.
 */
public class TextDNA extends DNA {

    private String targetString;
    private char[] currentString;


    public TextDNA(String targetString){
        this.targetString = targetString;
        currentString = new char[targetString.length()];

    }

    @Override
    public float calculateFitness() {
        return 0;
    }

    @Override
    public DNA mate(DNA partner) {
        return null;
    }

    @Override
    public void mutate(float mutationRate) {

    }
}
