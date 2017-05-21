package model;

/**
 * Created by Ka Kyen Lam on 21/05/2017.
 */
public class Node {

    Grid[][] grid;
    Grid root;

    public Node(Grid[][] grid, Grid root) {
        this.grid = grid;
        this.root = root;
    }

    public Node left() {
        Grid left = grid[root.getX() - 1][root.getY()];
        return new Node(grid, left);
    }

    public Node right() {
        Grid right = grid[root.getX() + 1][root.getY()];
        return new Node(grid, right);
    }

    public Node up() {
        Grid up = grid[root.getX()][root.getY() + 1];
        return new Node(grid, up);
    }

    public Node down() {
        Grid down = grid[root.getX()][root.getY() - 1];
        return new Node(grid, down);
    }


}
