package model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/* http://algs4.cs.princeton.edu/41graph/BreadthFirstPaths.java.html
* https://www.cs.bu.edu/teaching/c/tree/breadth-first/*/


/**
 * Created by Ka Kyen Lam on 19/05/2017.
 */
public class BFS {

    Node start;
    Node end;

    public BFS(Node start, Node end) {
        this.start = start;
        this.end = end;
    }

    public boolean compute() {

        Queue<Node> queue = new LinkedList<>();
        ArrayList<Node> explored = new ArrayList<>();
        queue.add(this.start);
        explored.add(start);

        while(!queue.isEmpty()){
            Node current = queue.remove();
            if(current.equals(this.end)) {
                return true;
            }
            else{
                if(current.getChildren().isEmpty())
                    return false;
                else
                    queue.addAll(current.getChildren());
            }
            explored.add(current);
        }
        return false;
    }
}
