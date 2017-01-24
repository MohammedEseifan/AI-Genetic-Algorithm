package Main;

import GeneticAlgorithm.Evolver;
import TextTest.*;
/**
 * Created by Mohammed on 23-Jan-17.
 */
public class Main {

    public static void main(String[] args){
        TextPopulation population = new TextPopulation(500,0.01f,"This is a test!");
        Evolver e = new Evolver(population,1f,-1);
        e.begin();
    }

}
