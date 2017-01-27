package Main;

import GeneticImplemenations.Ten.AI.TenDNA;
import GeneticImplemenations.Ten.Implementation.Game;

/**
 * Created by Mohammed on 23-Jan-17.
 */
public class Main {

    public static void main(String[] args){
//        TextPopulation population = new TextPopulation(500,0.01f,"This is a test!");
//        Evolver e = new Evolver(population,1f,-1);
//        e.begin();

//        MainGrid a = new MainGrid();
//        System.out.println(a.toString());
//        try {
//            a.setPiece(1,0, XOGrid.GridPiece.O);
//            a.setPiece(0,0, XOGrid.GridPiece.X);
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        }
//        System.out.println(a.toString());
//        GameVizualizer v = new GameVizualizer(a,800);

        Game game = new Game(new TenDNA(),new TenDNA());
        System.out.println(game.beginGame());
    }

}
