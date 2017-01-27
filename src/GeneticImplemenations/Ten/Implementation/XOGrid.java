package GeneticImplemenations.Ten.Implementation;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Mohammed on 23-Jan-17.
 */
public class XOGrid {


    public List<Integer> getIndexOfPiece(GridPiece gridPiece) {
        List<Integer> returnValue = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            if (grid[i] == gridPiece) {
                returnValue.add(i);
            }
        }
        return returnValue;
    }

    //Enum used for Grid pieces
    public enum GridPiece {
        X("X"),
        O("O"),
        NONE(" ");

        String value;

        GridPiece(String v) {
            this.value = v;
        }

        @Override
        public String toString() {
            return value;
        }
    }

    private GridPiece grid[] = new GridPiece[9];
    private GridPiece winner = GridPiece.NONE;
    private int winCombinations[][] = {
            {0, 1, 2}, //Row 1 (from top)
            {3, 4, 5}, //Row 2
            {6, 7, 8}, //Row 3
            {0, 3, 6}, //Column 1 (from left)
            {1, 4, 7}, //Column 2
            {2, 5, 8}, //Column 3
            {0, 4, 8}, //Left Diagonal
            {2, 4, 6}};//Right Diagonal

    public XOGrid() {
        for (int i = 0; i < 9; i++) {
            grid[i] = GridPiece.NONE;
        }
    }


    public void setPiece(int index, GridPiece value) throws IllegalAccessException {
        if (grid[index] != GridPiece.NONE || value == GridPiece.NONE || winner != GridPiece.NONE) {
            throw new IllegalAccessException("You cant place a piece here");
        }
        grid[index] = value;
        checkWinner();
    }

    public GridPiece getPiece(int index) {
        return grid[index];
    }

    private void checkWinner() {


        //Check each combination to see if there's a winner
        for (int i = 0; i < 8; i++) {
            int[] combo = winCombinations[i];
            //If all three combo locations are the same and they arent blank then there is a winner
            if (grid[combo[0]] == grid[combo[1]] && grid[combo[1]] == grid[combo[2]] && grid[combo[0]] != GridPiece.NONE) {
                winner = grid[combo[0]];
                return;
            }
        }
    }

    public List<Integer> getOpenSpots() {
        List<Integer> spots = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            if (grid[i] == GridPiece.NONE) {
                spots.add(i);
            }
        }
        return spots;
    }

    public GridPiece getWinner() {
        return winner;
    }

    public String getString(int row) {

        return String.format("    %s|%s|%s    ", grid[row * 3].toString(), grid[row * 3 + 1].toString(), grid[row * 3 + 2].toString());
    }

    public String toString() {
        String s = "";
        for (int i = 0; i < 3; i++) {
            s += getString(i) + "\n";
        }
        return s;
    }

    public void draw(int x, int y, int width, int height, int padding, Graphics2D g) {
        g.setStroke(new BasicStroke(1));
        int pieceSize = (width - 2 * padding) / 3;
        int spacing = 5;

        if (winner != GridPiece.NONE) {
            if (winner == GridPiece.X) {
                g.setColor(new Color(52, 73, 255));
                g.fillRect(x + padding, y + padding, width - 2 * spacing, height - 2 * spacing);
            } else { //If 'O' won
                g.setColor(new Color(255, 51, 46));
                g.fillOval(x + padding, y + padding, width - 2 * spacing, height - 2 * spacing);
            }
            return;
        }

        //Drawing pieces
        for (int dx = 0; dx < 3; dx++) {
            for (int dy = 0; dy < 3; dy++) {
                if (grid[dy * 3 + dx] == GridPiece.X) {
                    g.setColor(new Color(52, 73, 255));
                    g.fillRect(x + padding + pieceSize * dx + spacing, y + padding + pieceSize * dy + spacing, pieceSize - 2 * spacing, pieceSize - 2 * spacing);
                } else if (grid[dy * 3 + dx] == GridPiece.O) {
                    g.setColor(new Color(255, 51, 46));
                    g.fillOval(x + padding + pieceSize * dx + spacing, y + padding + pieceSize * dy + spacing, pieceSize - 2 * spacing, pieceSize - 2 * spacing);
                } else {
                    g.setColor(Color.white);
                    g.fillRect(x + padding + pieceSize * dx + spacing, y + padding + pieceSize * dy + spacing, pieceSize - 2 * spacing, pieceSize - 2 * spacing);
                }
            }
        }

    }

    public boolean isGridFull() {
        for (int i = 0; i < 9; i++) {
            if (grid[i] == GridPiece.NONE) {
                return false;
            }
        }
        return true;
    }

    @Override
    protected XOGrid clone() {
        XOGrid newGrid = new XOGrid();
        newGrid.grid = Arrays.copyOf(grid, 9);
        newGrid.winner = winner;
        return newGrid;
    }


    public boolean equals(XOGrid otherGrid) {
        for (int i = 0; i < 9; i++) {
            if (grid[i] != otherGrid.grid[i]) return false;
        }


        return true;
    }

}
