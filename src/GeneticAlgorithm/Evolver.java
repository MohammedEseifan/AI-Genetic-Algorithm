package GeneticAlgorithm;

/**
 * Created by Mohammed on 23-Jan-17.
 */
public class Evolver{

    Population pop;
    float targetFitness;

    public Evolver(Population pop, float targetFitness) {
        this.targetFitness = targetFitness;
        this.pop = pop;
    }

    public void begin() {
        System.out.println("Starting evolution");
        int i = 1;
        while(pop.getMaxFitness()<targetFitness){
            System.out.println("Running generation: "+i);
            pop.reproduce();
            i++;
        }
    }
}
