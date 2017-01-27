package GeneticImplemenations.Ten.AI;

import GeneticImplemenations.Ten.Implementation.MainGrid;
import GeneticImplemenations.Ten.Implementation.XOGrid;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mohammed on 26-Jan-17.
 */
public class GameTree {
    private Node root;
    private TenDNA dna;
    public GameTree(MainGrid startGrid, int maxDepth, XOGrid.GridPiece startType, TenDNA dna) {
        root = new Node(null, startGrid, startType);
        this.dna=dna;
        generateTree(root,maxDepth);
        System.out.println("T");
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
                newNode.calculateValue(root.getNodeType(), dna);
                parentNode.addChild(newNode);
            }
        }



    }

    public int[] getBestMove(){
        int[] returnValue = new int[2];
        for(Node child: root.getChildren()){
            if (child.getValue()==root.getValue()){
                root=child;
                generateExtraLevel(root);
                return child.getMoveDirections();
            }
        }

        return null;
    }

    private void generateExtraLevel(Node currentNode) {
        if(currentNode.getChildren().size()>0){
            for (Node child: currentNode.getChildren()){
                generateExtraLevel(child);
            }
        }else{
            generateTree(currentNode,1);
        }
    }



    private XOGrid.GridPiece inverseGridPiece(XOGrid.GridPiece x) {
        return x == XOGrid.GridPiece.X ? XOGrid.GridPiece.O : XOGrid.GridPiece.X;
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

    public void calculateValue(XOGrid.GridPiece rootType, TenDNA dna) {
        this.value=0;
    }
}