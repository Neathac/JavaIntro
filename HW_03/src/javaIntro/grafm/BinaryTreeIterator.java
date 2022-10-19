package javaIntro.grafm;

import java.util.Iterator;
import java.util.Stack;

public class BinaryTreeIterator implements Iterator<Node> {

    Stack<Node> stack;

    public BinaryTreeIterator(Node root) {
        stack = new Stack<Node>();

        Node currentNode = root;
        while (currentNode != null) {
            stack.push(currentNode);
            currentNode = currentNode.left;
        }
    }

    @Override
    public boolean hasNext() {
        if (stack.empty()) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public Node next() {
        Node result = stack.pop();
        Node currentNode = result.right;
     
        while (currentNode != null)
        {
            stack.push(currentNode);
            currentNode = currentNode.left;
        }

        return result;
    }
    
}
