package TextTest;

import GeneticAlgorithm.DNA;

/**
 * Created by Mohammed on 23-Jan-17.
 */
public class TextDNA extends DNA {

    private String targetString;
    private char[] currentString;


    public TextDNA(String targetString){
        super();
        this.targetString = targetString;
        currentString = new char[targetString.length()];
        for (int i = 0; i < targetString.length(); i++) {
            currentString[i] = (char) (randomGenerator.nextInt(95)+32);
        }

    }

    public TextDNA(String startingString, String targetString){
        super();
        currentString=startingString.toCharArray();
        this.targetString=targetString;
    }

    @Override
    public float calculateFitness() {
        float sum = 0f;
        for (int i = 0; i < targetString.length(); i++) {
            if(currentString[i] == targetString.charAt(i)){
                sum++;
            }
        }
        this.fitness=sum/targetString.length();
        return fitness;
    }

    @Override
    public DNA mate(DNA partner) {
        //Picks a random point to split merge the strings of the two DNA objects
        int splitPoint = randomGenerator.nextInt(targetString.length());
        String partnerString = ((TextDNA)partner).getCurrentString();
        return new TextDNA(this.getCurrentString().substring(0,splitPoint)+partnerString.substring(splitPoint),targetString);
    }

    public String getCurrentString(){
        return new String(currentString);
    }

    @Override
    public void mutate(float mutationRate) {
        for (int i = 0; i < targetString.length(); i++) {
            if(randomGenerator.nextFloat()<mutationRate){
                currentString[i] = (char) (randomGenerator.nextInt(95)+32);
            }
        }
    }

    @Override
    public String toString() {
        return "TextDNA{" +
                "currentString= '" + getCurrentString() +
                "', fitness: "+String.valueOf(fitness)+
                '}';
    }
}
