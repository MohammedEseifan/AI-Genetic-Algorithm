package GeneticImplemenations.Ten.AI;

import GeneticImplemenations.Ten.Implementation.MainGrid;
import GeneticImplemenations.Ten.Implementation.XOGrid;

import java.util.List;
import java.util.Random;

/**
 * Created by Mohammed on 23-Jan-17.
 */
public class TenAI {

    private Random randomGenerator= new Random();
    private TenDNA data;
    private XOGrid.GridPiece AIPiece;


    public void playTurn(MainGrid grid){
        List<Integer> gridValidSpots  = grid.getValidSpots();
        int gridIndex= gridValidSpots.get(randomGenerator.nextInt(gridValidSpots.size()));
        List<Integer> openSpots  = grid.getXOGridOpenSpots(gridIndex);
        try {
            grid.setPiece(gridIndex,openSpots.get(randomGenerator.nextInt(openSpots.size())),AIPiece);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }


    public TenAI(TenDNA data, XOGrid.GridPiece piece){
        this.data=data;
        this.AIPiece=piece;
    }

}
