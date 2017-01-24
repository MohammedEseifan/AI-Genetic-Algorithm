package TenImplementation;

import java.util.ArrayList;
import java.util.List;
/**
 * Created by Mohammed on 23-Jan-17.
 */
public class MainGrid{

    private XOGrid grid[] = new XOGrid[9];
    private List<Integer> validSpots = new ArrayList<>();
    private XOGrid.GridPiece winner = XOGrid.GridPiece.NONE;

    public MainGrid(){

        for (int i = 0; i < 9; i++) {
            grid[i] = new XOGrid();
            validSpots.add(i);
        }

    }

    public void setPiece(int gridIndex, int pieceIndex, XOGrid.GridPiece piece) throws IllegalAccessException {
        grid[gridIndex].setPiece(pieceIndex,piece);
        validSpots.clear();

        if(grid[pieceIndex].getWinner()== XOGrid.GridPiece.NONE){
            validSpots.add(pieceIndex);
        }else{
            for (int i = 0; i < 9; i++) {
                if(grid[i].getWinner()== XOGrid.GridPiece.NONE){
                    validSpots.add(i);
                }
            }
        }

        checkWinner();
    }

    @Override
    /**
     * Very crude toString function for debugging purposes
     */
    public String toString() {
        String s ="Grid:\n";

        for (int y = 0; y < 3; y++) {
            for (int y2 = 0; y2 < 3; y2++) {
                s+= String.format("%s%%%s%%%s\n",grid[3*y].getString(y2),grid[3*y+1].getString(y2),grid[3*y+2].getString(y2));

            }

            s+=y<2?"%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n":"";
        }

        return s;
    }

    public XOGrid.GridPiece getWinner() {
        return winner;
    }

    private void checkWinner(){
        int winCombinations[][] = {
                {0,1,2}, //Row 1 (from top)
                {3,4,5}, //Row 2
                {6,7,8}, //Row 3
                {0,3,6}, //Column 1 (from left)
                {1,4,7}, //Column 2
                {2,5,8}, //Column 3
                {0,4,8}, //Left Diagonal
                {2,4,6}};//Right Diagonal

        //Check each combination to see if there's a winner
        for (int i = 0; i < 8; i++) {
            int[] combo = winCombinations[i];
            //If all three combo locations are the same and they arent blank then there is a winner
            if(grid[combo[0]].getWinner()== grid[combo[1]].getWinner() && grid[combo[1]].getWinner()== grid[combo[2]].getWinner() && grid[combo[0]].getWinner() != XOGrid.GridPiece.NONE){
                winner = grid[combo[0]].getWinner();
                return;
            }
        }

    }


}
