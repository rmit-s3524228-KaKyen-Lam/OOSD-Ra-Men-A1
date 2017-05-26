package model.board;

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
        if (root.getX() - 1 >= 0) {
            Grid left = grid[root.getX() - 1][root.getY()];
            return new Node(grid, left);
        } else {
            return null;
        }
    }

    public Node right() {
        if (root.getX() + 1 < Board.gridMaxWidth) {
            Grid right = grid[root.getX() + 1][root.getY()];
            return new Node(grid, right);
        } else {
            return null;
        }
    }

    public Node up() {
        if (root.getY() - 1 >= 0) {
            Grid up = grid[root.getX()][root.getY() - 1];
            return new Node(grid, up);
        } else {
            return null;
        }
    }

    public Node down() {
        if (root.getY() + 1 < Board.gridMaxHeight) {
            Grid down = grid[root.getX()][root.getY() + 1];
            return new Node(grid, down);
        } else {
            return null;
        }
    }

}
