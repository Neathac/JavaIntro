package javaIntro.grafm;

import java.util.Iterator;

import javaIntro.grafm.BinaryTree;
import javaIntro.grafm.Node;

public class Main {
    public static void main(String[] args) throws Exception {
        BinaryTree tree = new BinaryTree();
        for (int i = 0; i < args.length; ++i) {
            tree.add(Integer.parseInt(args[i]));
        }
        Iterator<Node> iter = tree.iterator();
        while(iter.hasNext()) {
            System.out.println(iter.next().data);
        }
    }
}
