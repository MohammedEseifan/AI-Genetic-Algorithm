package GeneticImplemenations.Ten.Implementation;

import GeneticImplemenations.Ten.AI.TenAI;
import GeneticImplemenations.Ten.AI.TenDNA;

/**
 * Created by Mohammed on 23-Jan-17.
 */
public class Game {
    private XOGrid.GridPiece currentTurn;
    private MainGrid gameGrid;
    private TenAI players[] = new TenAI[2];
    private GameVizualizer gameVizualizer;


    public Game(TenDNA player1DNA, TenDNA player2DNA) {
        players[0] = new TenAI(player1DNA, XOGrid.GridPiece.X);
        players[1] =  new TenAI(player2DNA, XOGrid.GridPiece.O);
    }

    public Game(TenDNA player1DNA, TenDNA player2DNA, boolean visualizeGame) {
        players[0] = new TenAI(player1DNA, XOGrid.GridPiece.X);
        players[1] =  new TenAI(player2DNA, XOGrid.GridPiece.O);
        if (visualizeGame) {
            gameVizualizer = new GameVizualizer(gameGrid, 800);
        }
    }

    public XOGrid.GridPiece beginGame() {
        resetGame();
        while (gameGrid.getWinner() == XOGrid.GridPiece.NONE && gameGrid.getValidSpots().size() > 0) {
            doTurn();
        }
        return gameGrid.getWinner();
    }

    private void resetGame() {
        currentTurn = XOGrid.GridPiece.X;
        gameGrid = new MainGrid();
    }

    private void doTurn() {
        players[currentTurn.ordinal()].playTurn(gameGrid);

        //Flipping the current turn
        currentTurn = currentTurn == XOGrid.GridPiece.X ? XOGrid.GridPiece.O : XOGrid.GridPiece.X;
    }


}
