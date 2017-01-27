package GeneticImplemenations.Ten.Implementation;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mohammed on 23-Jan-17.
 */
public class MainGrid {

    private XOGrid grid[] = new XOGrid[9];
    private List<Integer> validSpots = new ArrayList<>();
    private XOGrid.GridPiece winner = XOGrid.GridPiece.NONE;

    public MainGrid() {

        for (int i = 0; i < 9; i++) {
            grid[i] = new XOGrid();
            validSpots.add(i);
        }

    }

    public void setPiece(int gridIndex, int pieceIndex, XOGrid.GridPiece piece) throws IllegalAccessException {
        grid[gridIndex].setPiece(pieceIndex, piece);
        validSpots.clear();

        if (grid[pieceIndex].getWinner() == XOGrid.GridPiece.NONE) {
            validSpots.add(pieceIndex);
        } else {
            for (int i = 0; i < 9; i++) {
                if (grid[i].getWinner() == XOGrid.GridPiece.NONE && grid[i].isGridFull() == false) { //If the grid isnt already won and it isnt full
                    validSpots.add(i);
                }
            }
        }

        checkWinner();
    }

    public List<Integer> getValidSpots() {
        return validSpots;
    }

    public XOGrid.GridPiece getInnerGridWinner(int index) {
        return grid[index].getWinner();
    }

    @Override
    /**
     * Very crude toString function for debugging purposes
     */
    public String toString() {
        String s = "Grid:\n";


        for (int y = 0; y < 3; y++) {
            for (int y2 = 0; y2 < 3; y2++) {
                s += String.format("%s%%%s%%%s\n", grid[3 * y].getString(y2), grid[3 * y + 1].getString(y2), grid[3 * y + 2].getString(y2));

            }

            s += y < 2 ? "%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%\n" : "";
        }

        return s;
    }

    public XOGrid.GridPiece getWinner() {
        return winner;
    }

    private void checkWinner() {
        int winCombinations[][] = {
                {0, 1, 2}, //Row 1 (from top)
                {3, 4, 5}, //Row 2
                {6, 7, 8}, //Row 3
                {0, 3, 6}, //Column 1 (from left)
                {1, 4, 7}, //Column 2
                {2, 5, 8}, //Column 3
                {0, 4, 8}, //Left Diagonal
                {2, 4, 6}};//Right Diagonal

        //Check each combination to see if there's a winner
        for (int i = 0; i < 8; i++) {
            int[] combo = winCombinations[i];
            //If all three combo locations are the same and they arent blank then there is a winner
            if (grid[combo[0]].getWinner() == grid[combo[1]].getWinner() && grid[combo[1]].getWinner() == grid[combo[2]].getWinner() && grid[combo[0]].getWinner() != XOGrid.GridPiece.NONE) {
                winner = grid[combo[0]].getWinner();
                return;
            }
        }

    }

    public List<Integer> getXOGridOpenSpots(int gridIndex) {
        return grid[gridIndex].getOpenSpots();
    }

    public void draw(int x, int y, int width, int height, int padding, Graphics2D g) {
        int spacing = (width - 2 * padding) / 3;

        g.setStroke(new BasicStroke(3));
        g.setColor(new Color(67, 67, 67));

        if (winner != XOGrid.GridPiece.NONE) {
            if (winner == XOGrid.GridPiece.X) {
                g.setColor(new Color(52, 73, 255));
                g.fillRect(x + padding, y + padding, width - 2 * padding, height - 2 * padding);
            } else { //If 'O' won
                g.setColor(new Color(255, 51, 46));
                g.fillOval(x + padding, y + padding, width - 2 * padding, height - 2 * padding);
            }
            return;
        }

        //Drawing grid lines
        for (int i = 1; i < 3; i++) {
            g.drawLine(x + padding + spacing * i, y + padding, x + padding + spacing * i, y + height - padding); //Vertical line
            g.drawLine(x + padding, y + padding + spacing * i, x + width - padding, y + padding + spacing * i); //Horizontal line
        }

        //Drawing grids
        for (int dx = 0; dx < 3; dx++) {
            for (int dy = 0; dy < 3; dy++) {
                grid[dy * 3 + dx].draw(dx * spacing + padding, dy * spacing + padding, spacing, spacing, 5, g);
            }
        }
    }


    @Override
    public MainGrid clone() {
        MainGrid newGrid = new MainGrid();
        for (int i = 0; i < 9; i++) {
            newGrid.grid[i] = grid[i].clone();
            newGrid.validSpots.addAll(validSpots);
        }
        return newGrid;
    }

    public boolean equals(MainGrid otherGrid) {
        for (int i = 0; i < 9; i++) {
            if (!grid[i].equals(otherGrid.getInnerGrid(i))) return false;
        }

        return winner.equals(otherGrid.winner);
    }

    public XOGrid getInnerGrid(int x) {
        return grid[x];
    }
}
