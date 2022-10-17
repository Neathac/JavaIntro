package main.grafm;

import java.util.Iterator;

public class Node {
    int data;
    Node left;
    Node right;
 
    Node(int data)
    {
        this.data = data;
        left = right = null;
    }

    public Node getRightNode() {
        return right;
    }

    public Node getLeftNode() {
        return left;
    }
}
