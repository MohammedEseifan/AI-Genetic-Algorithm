package GeneticImplemenations.Ten.AI;

import GeneticImplemenations.Ten.Implementation.XOGrid;

/**
 * Created by Mohammed on 27-Jan-17.
 */
public class Move {
    private int gridIndex;
    private int innerIndex;
    private XOGrid.GridPiece piece;

    public Move(int gridIndex, int innerIndex, XOGrid.GridPiece piece) {
        this.gridIndex = gridIndex;
        this.innerIndex = innerIndex;
        this.piece = piece;
    }

    public int getGridIndex() {
        return gridIndex;
    }

    public int getInnerIndex() {
        return innerIndex;
    }

    public XOGrid.GridPiece getPiece() {
        return piece;
    }
}
