package TextTest;

import GeneticAlgorithm.DNA;
import GeneticAlgorithm.Population;

/**
 * Created by Mohammed on 23-Jan-17.
 */
public class TextPopulation extends Population {


    public TextPopulation(int populationSize, float DNAMutationRate, String targetString) {
        super(populationSize, DNAMutationRate);

    }

    @Override
    public DNA newDNA() {
        return null;
    }

}
