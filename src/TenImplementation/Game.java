package TenImplementation;

import TenAI.TenAI;

/**
 * Created by Mohammed on 23-Jan-17.
 */
public class Game {
    private XOGrid.GridPiece currentTurn = XOGrid.GridPiece.X;
    private MainGrid gameGrid = new MainGrid();
    private TenAI players[] = new TenAI[2];

    public Game(TenAI player1,TenAI player2){
        players[0] =player1;
        players[1] = player2;
    }

    public void beginGame(){
        while(gameGrid.getWinner()== XOGrid.GridPiece.NONE){
            doTurn();
        }
    }

    private void doTurn() {
        players[currentTurn.ordinal()].playTurn(gameGrid);

        //Flipping the current turn
        currentTurn = currentTurn== XOGrid.GridPiece.X? XOGrid.GridPiece.O: XOGrid.GridPiece.X;
    }


}
