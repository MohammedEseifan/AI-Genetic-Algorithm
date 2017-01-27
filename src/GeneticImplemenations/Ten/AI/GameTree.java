package GeneticImplemenations.Ten.AI;

import GeneticImplemenations.Ten.Implementation.MainGrid;
import GeneticImplemenations.Ten.Implementation.XOGrid;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

import static GeneticImplemenations.Ten.Implementation.XOGrid.GridPiece.NONE;
import static GeneticImplemenations.Ten.Implementation.XOGrid.GridPiece.O;
import static GeneticImplemenations.Ten.Implementation.XOGrid.GridPiece.X;

/**
 * Created by Mohammed on 26-Jan-17.
 */
public class GameTree {
    private Node root;
    private TenDNA dna;
    private int maxDepth;

    public GameTree(MainGrid startGrid, int maxDepth, XOGrid.GridPiece startType, TenDNA dna) {
        root = new Node(null, startGrid, X);
        this.dna=dna;
        this.maxDepth=maxDepth;
        generateTree(root,maxDepth);
        root.calculateValue(dna);
    }

    private void generateTree(Node parentNode, int level) {
        MainGrid currentMainGrid = parentNode.getGrid();
        List<Integer> validMoves = currentMainGrid.getValidSpots();
        if (level <= 0 || validMoves.size() == 0) return;


        //Flipping node type if the 'parentNode' isnt the root node
        XOGrid.GridPiece childType = parentNode.getParent() == null ? parentNode.getNodeType() : inverseGridPiece(parentNode.getNodeType());

        for (Integer mainGridIndex: validMoves) {
            List<Integer> xoGridMoves = currentMainGrid.getXOGridOpenSpots(mainGridIndex);
            for (Integer innerGridIndex: xoGridMoves){
                Node newNode = new Node(parentNode,mainGridIndex,innerGridIndex,childType);
                generateTree(newNode,level-1);
                newNode.calculateValue(dna);
                parentNode.addChild(newNode);
            }
        }
    }


    public int[] getBestMove(MainGrid newGrid){
        Queue<Node> nodeBuffer = new ArrayDeque<>();
        Node currentNode=root;
        if(root.getValue()==248){
            System.out.println("df");
        }
        while(!currentNode.getGrid().equals(newGrid)){
            nodeBuffer.addAll(currentNode.getChildren());
            currentNode=nodeBuffer.poll();

            if(currentNode == null){
                currentNode=root;
                while(!currentNode.getGrid().equals(newGrid)) {
                    nodeBuffer.addAll(currentNode.getChildren());
                    currentNode = nodeBuffer.poll();
                }
            }
        }


        Node bestChild = currentNode.getChildren().get(0);
        boolean max=currentNode.getNodeType()==O;
        for (Node child:currentNode.getChildren()){
            if((max&& bestChild.getValue()<child.getValue()) || (!max && bestChild.getValue()>child.getValue())){ //If we're returning the max child and current child is greater OR if we're returning min and current child is less than
                bestChild = child;
            }
        }
        root=bestChild;
        root.clearParent();
        generateExtraLevel(root, getTreeDepth());
        return bestChild.getMoveDirections();


    }

    public int getTreeDepth(){
        int i=0;
        Node currentNode = root;
        while(currentNode.getChildren().size()>0){
            currentNode = currentNode.getChildren().get(0);
            i++;
        }
        return i;
    }

    private void generateExtraLevel(Node currentNode, int currentDepth) {
        if(currentNode.getChildren().size()>0){
            for (Node child: currentNode.getChildren()){
                generateExtraLevel(child, currentDepth);
                child.calculateValue(dna);
            }
        }else{
            generateTree(currentNode,maxDepth-currentDepth);
        }
    }



    private XOGrid.GridPiece inverseGridPiece(XOGrid.GridPiece x) {
        return x == X ? XOGrid.GridPiece.O : X;
    }


    public Node getRoot() {
        return root;
    }


}

class Node {
    private Node parent;
    private List<Node> children;
    private MainGrid grid;
    private int value;
    private int mainGridIndex;
    private int innerGridIndex;
    private XOGrid.GridPiece nodeType;

    public Node(Node parent, int mainGridIndex, int innerGridIndex, XOGrid.GridPiece nodeType) {
        this.parent = parent;
        this.grid = parent.getGrid().clone();
        this.mainGridIndex = mainGridIndex;
        this.innerGridIndex = innerGridIndex;
        this.nodeType = nodeType;
        try {
            grid.setPiece(mainGridIndex,innerGridIndex,nodeType);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        children = new ArrayList<Node>();
    }

    public Node(Node parent, MainGrid grid, XOGrid.GridPiece nodeType) {
        this.parent = parent;
        this.grid = grid;
        this.nodeType = nodeType;
        children = new ArrayList<Node>();
    }

    public Node getParent() {
        return parent;
    }

    public int[] getMoveDirections(){
        return new int[] {mainGridIndex,innerGridIndex};
    }

    public List<Node> getChildren() {
        return children;
    }

    public void addChild(Node n){
        children.add(n);
    }

    public XOGrid.GridPiece getNodeType() {
        return nodeType;
    }

    public int getValue() {
        return value;
    }

    public MainGrid getGrid() {
        return grid;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void calculateValue(TenDNA dna) {
        if(children.size()==0){ //If this is a leaf
            switch (grid.getWinner()){
                case X:
                    this.value=Integer.MAX_VALUE;
                    break;
                case O:
                    this.value = Integer.MIN_VALUE;
                    break;
                default:
                    this.value = heuristicEvaluation(dna);
                    break;
            }
        }else{
            this.value=getMinMax(nodeType== X);
        }
    }

    private int getMinMax(boolean max){
        int value = max?Integer.MIN_VALUE:Integer.MAX_VALUE;
        for (Node child:children){
            if((max&& value<child.getValue()) || (!max && value>child.getValue())){ //If we're returning the max child and current child is greater OR if we're returning min and current child is less than
                value = child.getValue();
            }
        }
        return value;
    }

    private int heuristicEvaluation(TenDNA dna){
        //Getting number of grids won
        int numGridsWon = 0;
        List<Integer> gridsWon = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            if(nodeType==grid.getInnerGridWinner(i)){
                numGridsWon++;
                gridsWon.add(i);
            }
        }


        int mainGridWinCombinations[][] = {
            {0, 1, 2}, //Row 1 (from top)
            {3, 4, 5}, //Row 2
            {6, 7, 8}, //Row 3
            {0, 3, 6}, //Column 1 (from left)
            {1, 4, 7}, //Column 2
            {2, 5, 8}, //Column 3
            {0, 4, 8}, //Left Diagonal
            {2, 4, 6}};//Right Diagonal

        int gridsNeededForWin=0;
        int possibleWinPaths =0;
        for(Integer currentGrid:gridsWon){
            for (int i = 0; i < mainGridWinCombinations.length; i++) { //Looping through each combination
                if(mainGridWinCombinations[i]==null) continue;
                int tempCount = 0;
                boolean found = false;
                for (int j = 0; j < 3; j++) {
                    int num = mainGridWinCombinations[i][j];
                    found = num ==currentGrid || found;
                    if(grid.getInnerGridWinner(num)==NONE){
                        tempCount++;
                    }
                }
                if (found){
                    gridsNeededForWin+=tempCount;
                    possibleWinPaths++;
                    mainGridWinCombinations[i]=null;
                }
            }
        }

        int innerGridWinCombinations[][] = {
            {0, 1, 2}, //Row 1 (from top)
            {3, 4, 5}, //Row 2
            {6, 7, 8}, //Row 3
            {0, 3, 6}, //Column 1 (from left)
            {1, 4, 7}, //Column 2
            {2, 5, 8}, //Column 3
            {0, 4, 8}, //Left Diagonal
            {2, 4, 6}};//Right Diagonal


        int piecesNeededForWin=0;
        int innerGridWinPaths =0;
        for(int x=0;x<9;x++){
            for(Integer currentGrid:grid.getInnerGrid(x).getIndexOfPiece(nodeType)){
                for (int i = 0; i < innerGridWinCombinations.length; i++) { //Looping through each combination
                    if(innerGridWinCombinations[i]==null) continue;
                    int tempCount = 0;
                    boolean found = false;
                    for (int j = 0; j < 3; j++) {
                        int num = innerGridWinCombinations[i][j];
                        found = num ==currentGrid || found;
                        if(grid.getInnerGrid(x).getPiece(num)==NONE){
                            tempCount++;
                        }
                    }
                    if (found){
                        piecesNeededForWin+=tempCount;
                        innerGridWinPaths++;
                        innerGridWinCombinations[i]=null;
                    }
                }
            }
        }



        int finalScore=
                (int) (Math.pow(dna.getAttribute("mainWonGrids"),numGridsWon)+
                        Math.pow(dna.getAttribute("mainWinPaths"),possibleWinPaths)-
                        Math.pow(dna.getAttribute("mainGridsNeeded"),gridsNeededForWin)-
                        dna.getAttribute("innerPiecesNeeded")*piecesNeededForWin+
                        dna.getAttribute("innerWinPaths")*innerGridWinPaths);

        return finalScore * (nodeType==X?1:-1);
    }

    public void clearParent(){
        this.parent=null;
    }
}