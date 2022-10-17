package main.grafm;

import java.util.Iterator;

public class BinaryTreeIterator implements Iterator<Node> {

    private Node rootNode;

    public BinaryTreeIterator(BinaryTree binaryTree) {
        rootNode = binaryTree.rootNode;
    }

    @Override
    public boolean hasNext() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public Node next() {
        // TODO Auto-generated method stub
        return null;
    }
    
}
