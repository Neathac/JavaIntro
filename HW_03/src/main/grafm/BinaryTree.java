package main.grafm;

import java.util.Iterator;

import main.grafm.Node;

public class BinaryTree implements Iterable<Node> {

    public Node rootNode;

    public BinaryTree(int initialData) {
        rootNode = new Node(initialData);
    }

    public BinaryTree() {};

    public Iterator<Node> iterator() { 
        return new BinaryTreeIterator(this); 
    } 

    public void add(int data) {
        if (rootNode == null) {
            rootNode = new Node(data);
        } else {
            Node currentNode = rootNode;
            boolean wasSet = false;
            while (!wasSet) {
                if (data > currentNode.data) {

                } else if (data < currentNode.data) {
                    
                }
            }
        }
    }
}
