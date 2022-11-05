package javaIntro.grafm;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Iterator;
import java.util.Stack;

public class BinaryTree implements Iterable<BinaryTree.Node> {
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

    class Node {
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

    public Node rootNode;

    public BinaryTree(int initialData) {
        rootNode = new Node(initialData);
    }

    public BinaryTree() {};

    public Iterator<Node> iterator() { 
        return this.new BinaryTreeIterator(this.rootNode); 
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

