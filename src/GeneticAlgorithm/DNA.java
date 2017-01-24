package GeneticAlgorithm;

import java.util.Random;

/**
 * Created by Mohammed on 23-Jan-17.
 */
public abstract class DNA {
    protected float fitness;
    protected Random randomGenerator;

    public DNA(){
        this.fitness=0f;
        randomGenerator = new Random();
    }

    public abstract float calculateFitness();
    public abstract DNA mate(DNA partner);
    public abstract void mutate(float mutationRate);

    public float getFitness() {
        return fitness;
    }
}
