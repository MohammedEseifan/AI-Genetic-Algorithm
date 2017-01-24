package TenAI;

import GeneticAlgorithm.DNA;
import TenImplementation.MainGrid;
import TenImplementation.XOGrid;

import java.util.List;
import java.util.Random;

/**
 * Created by Mohammed on 23-Jan-17.
 */
public class TenAI {

    private Random randomGenerator= new Random();
    private DNA data;
    private XOGrid.GridPiece AIPiece;


    public void playTurn(MainGrid grid){
        List<Integer> gridValidSpots  = grid.getValidSpots();
        int gridIndex= gridValidSpots.get(randomGenerator.nextInt(gridValidSpots.size()));
        List<Integer> openSpots  = grid.getXOGridOpenSpots(gridIndex);
        try {
            if(openSpots.size()==0){
                System.out.println("T");
            }
            grid.setPiece(gridIndex,openSpots.get(randomGenerator.nextInt(openSpots.size())),AIPiece);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public TenAI(DNA data, XOGrid.GridPiece piece){
        this.data=data;
        this.AIPiece=piece;

    }
}
