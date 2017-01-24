package GeneticAlgorithm;

/**
 * Created by Mohammed on 23-Jan-17.
 */
public class Evolver{

    Population pop;
    float targetFitness;
    int targetGenerations;
    public Evolver(Population pop, float targetFitness, int targetGenerations) {
        this.targetFitness = targetFitness;
        this.pop = pop;
        this.targetGenerations=targetGenerations>0?targetGenerations:Integer.MAX_VALUE;
    }

    public void begin() {
        System.out.println("Starting evolution");
        int i = 1;
        while(pop.getMaxFitness()<targetFitness && i<targetGenerations){
            System.out.println("Running generation: "+i);
            pop.reproduce();
            i++;
        }
    }
}
