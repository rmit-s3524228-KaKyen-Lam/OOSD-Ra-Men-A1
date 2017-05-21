package model;


import java.util.ArrayList;

/**
 * Created by Ka Kyen Lam on 19/05/2017.
 */
public class Node {
    Node left;
    Node right;
    Grid grid;

    public Node (Grid grid, Node left, Node right) {
        this.grid = grid;
        this.left = left;
        this.right = right;
    }

    public ArrayList<Node> getChildren(){
        ArrayList<Node> child = new ArrayList<>();
        if(this.left != null)
        {
            child.add(left);
        }
        if(this.right != null) {
            child.add(right);
        }
        return child;
    }

    public boolean removeChild(Node n){
        return false;
    }
}
