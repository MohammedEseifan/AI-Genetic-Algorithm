package GeneticImplemenations.Ten.AI;

import GeneticAlgorithm.DNA;
import GeneticAlgorithm.Population;

import java.util.List;

/**
 * Created by Mohammed on 24-Jan-17.
 */
public class TenPopulation extends Population {
    public TenPopulation(int populationSize, float DNAMutationRate) {
        super(populationSize, DNAMutationRate);
    }

    @Override
    public void reproduce(){

        List<DNA> matingPool = generateMatingPool();

        for (int i = 0; i < populationSize; i++)
        {
            //Select two DNAs to mate and mate them
            DNA parentA = matingPool.get(randomGenerator.nextInt(matingPool.size()));
            DNA parentB = matingPool.get(randomGenerator.nextInt(matingPool.size()));
            population[i] = parentA.mate(parentB);
            ((TenDNA)population[i]).setEnemyDNA((TenDNA) fittestDNA);

            //Mutate the newly created child
            population[i].mutate(DNAMutationRate);
        }

    }
}
