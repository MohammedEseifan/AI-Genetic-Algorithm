package TenImplementation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mohammed on 23-Jan-17.
 */
public class XOGrid {

    //Enum used for Grid pieces
    public enum GridPiece{
        X("X"),
        O("O"),
        NONE(" ");

        String value;
        GridPiece(String v){
            this.value=v;
        }

        @Override
        public String toString(){
            return value;
        }
    }

    private GridPiece grid[] = new GridPiece[9];
    private GridPiece winner = GridPiece.NONE;

    public XOGrid(){
        for (int i = 0; i < 9; i++) {
            grid[i]=GridPiece.NONE;
        }
    }

    public void setPiece(int index, GridPiece value) throws IllegalAccessException {
        if(grid[index]!=GridPiece.NONE || value == GridPiece.NONE || winner!=GridPiece.NONE){
            throw  new IllegalAccessException("You cant place a piece here");
        }
        grid[index] = value;
        checkWinner();
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
            if(grid[combo[0]]== grid[combo[1]] && grid[combo[1]]== grid[combo[2]] && grid[combo[0]] !=GridPiece.NONE){
                winner = grid[combo[0]];
                return;
            }
        }
    }

    public List<Integer> getOpenSpots(){
        List<Integer> spots = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            if(grid[i]==GridPiece.NONE){
                spots.add(i);
            }
        }
        return spots;
    }

    public GridPiece getWinner() {
        return winner;
    }

    public String getString(int row){

        return String.format("    %s|%s|%s    ",grid[row*3].toString(),grid[row*3+1].toString(),grid[row*3+2].toString());
    }
}
