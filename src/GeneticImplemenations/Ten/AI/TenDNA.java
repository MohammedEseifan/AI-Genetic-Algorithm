package GeneticImplemenations.Ten.AI;

import GeneticAlgorithm.DNA;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Mohammed on 24-Jan-17.
 */
public class TenDNA extends DNA implements Serializable {

    List<AIAttribute> attributes;

    public TenDNA(){
        initAttributes();
    }

    public TenDNA(List<AIAttribute> attributes){
        this.attributes=attributes;
    }

    private void initAttributes(){
        attributes.clear();

    }

    public Integer getArribute(String name){
        for (AIAttribute attr: attributes) {
            if(attr.getName().equalsIgnoreCase(name)){
                return attr.getValue();
            }
        }
        return null;
    }

    @Override
    public float calculateFitness() {
        return 0;
    }

    @Override
    public DNA mate(DNA partner) {
         List<AIAttribute> newAttributes = new ArrayList<AIAttribute>();
        for (int i = 0; i < attributes.size(); i++) {
            AIAttribute current = attributes.get(i);
            AIAttribute partnerAttr = ((TenDNA)partner).getAttributes().get(i);
            AIAttribute newAttribute  = current.clone();
            newAttribute.setValue((current.getValue()+partnerAttr.getValue())/2);
            newAttributes.add(newAttribute);
        }
        return new TenDNA(newAttributes);
    }

    public List<AIAttribute> getAttributes(){
        return attributes;
    }

    @Override
    public void mutate(float mutationRate) {
        for (int i = 0; i < attributes.size(); i++) {
            if(randomGenerator.nextFloat()<mutationRate){
                attributes.get(i).setValueToRandom();
            }
        }
    }
}

class AIAttribute implements Serializable{
    private String name;
    private int value, minValue,maxValue;
    private Random randomGenerator;


    public AIAttribute(String name, int minValue,int maxValue, Random randomGenerator) {
        this.name = name;
        this.maxValue = maxValue;
        this.minValue = minValue;
        this.randomGenerator = randomGenerator;
        setValueToRandom();
    }
    public AIAttribute(String name, int value, int minValue,int maxValue, Random randomGenerator){
        this.name=name;
        this.maxValue=maxValue;
        this.minValue=minValue;
        this.value=value;
        this.randomGenerator=randomGenerator;

    }

    @Override
    protected AIAttribute clone(){
        return new AIAttribute(name,value,minValue,maxValue,randomGenerator);
    }

    public String getName() {
        return name;
    }

    public int getValue() {

        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setValueToRandom() {
        this.value = minValue+randomGenerator.nextInt(maxValue-minValue);
    }
}
