package GeneticImplemenations.Text;

import GeneticAlgorithm.Population;

/**
 * Created by Mohammed on 23-Jan-17.
 */
public class TextPopulation extends Population {

    private String targetString;

    public TextPopulation(int populationSize, float DNAMutationRate, String targetString) {
        super(populationSize, DNAMutationRate);
        this.targetString = targetString;
        for (int i = 0; i < populationSize; i++) {
            population[i] = new TextDNA(targetString);
        }
    }


}
