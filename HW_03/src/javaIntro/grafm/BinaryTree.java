package javaIntro.grafm;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

import javaIntro.grafm.Node;

public class BinaryTree implements Iterable<Node> {

    public Node rootNode;

    public BinaryTree(int initialData) {
        rootNode = new Node(initialData);
    }

    public BinaryTree() {};

    public Iterator<Node> iterator() { 
        return new BinaryTreeIterator(this.rootNode); 
    } 

    public void add(int data) {
        if (rootNode == null) {
            rootNode = new Node(data);
        } else {
            Node currNode = this.rootNode;
            
            while(currNode != null) {

                if (data < currNode.data) {
                    if (currNode.left == null) {
                        currNode.left = new Node(data);
                        return;
                    } else {
                        currNode = currNode.left;
                    }
                } else {
                    if (currNode.right == null) {
                        currNode.right = new Node(data);
                        return;
                    } else {
                        currNode = currNode.right;
                    }
                }
            }
        }
    }
}
