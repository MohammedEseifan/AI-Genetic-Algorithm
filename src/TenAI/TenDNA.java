package TenAI;

import GeneticAlgorithm.DNA;

/**
 * Created by Mohammed on 24-Jan-17.
 */
public class TenDNA extends DNA{
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
