package GeneticImplemenations.Ten.AI;

import GeneticImplemenations.Ten.Implementation.MainGrid;
import GeneticImplemenations.Ten.Implementation.XOGrid;

import java.util.Random;

/**
 * Created by Mohammed on 23-Jan-17.
 */
public class TenAI {

    private Random randomGenerator= new Random();
    private TenDNA data;
    private XOGrid.GridPiece AIPiece;
    private GameTree tree;

    public void playTurn(MainGrid grid){
        int[] move = tree.getBestMove(grid);
        try {
            grid.setPiece(move[0],move[1],AIPiece);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }


    public TenAI(TenDNA data, XOGrid.GridPiece piece){
        this.data=data;
        this.AIPiece=piece;
        tree= new GameTree(new MainGrid(),4,piece,data);
    }

}
