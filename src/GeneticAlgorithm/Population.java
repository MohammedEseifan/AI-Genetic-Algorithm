package GeneticAlgorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Mohammed on 23-Jan-17.
 */
public abstract class Population{

    protected DNA population[];
    protected int populationSize;
    protected float DNAMutationRate;
    protected DNA fittestDNA;
    protected Random randomGenerator;


    public Population(int populationSize, float DNAMutationRate) {
        this.population = new DNA[populationSize];
        this.populationSize=populationSize;
        this.randomGenerator = new Random(System.currentTimeMillis());
        this.DNAMutationRate = DNAMutationRate;

    }

    public abstract DNA newDNA();

    public void calculatePopulationFitness(){
        fittestDNA = population[0];
        for(DNA dna: population){
            if (dna.calculateFitness() > fittestDNA.getFitness()){
                fittestDNA=dna;
            }
        }
    }

    public List<DNA> generateMatingPool(){

        List<DNA> matingPool = new ArrayList<>();
        for(DNA dna: population){
            //Determines how many times this DNA object should be added to the mating pool (aka probability of being mated)
            int num = (int) (50*dna.getFitness()/fittestDNA.getFitness());
            for (int x = 0; x <= num; x++){
                matingPool.add(dna);
            }
        }

        return matingPool;
    }

    public void reproduce(){

        List<DNA> matingPool = generateMatingPool();

        for (int i = 0; i < populationSize; i++)
        {
            //Select two DNAs to mate and mate them
            DNA parentA = matingPool.get(randomGenerator.nextInt(matingPool.size()));
            DNA parentB = matingPool.get(randomGenerator.nextInt(matingPool.size()));
            population[i] = parentA.mate(parentB);

            //Mutate the newly created child
            population[i].mutate(DNAMutationRate);
        }

        calculatePopulationFitness();
    }
}
