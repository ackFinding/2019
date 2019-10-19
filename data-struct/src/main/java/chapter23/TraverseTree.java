package chapter23;

import java.util.PriorityQueue;
import java.util.Queue;

public class TraverseTree {


    public static void traverseByLevel(Node root) {
        if (root == null) return;
        Queue<Node> stack = new PriorityQueue<>();
        stack.add(root);
        while (!stack.isEmpty()) {
            Node node = stack.poll();
            System.out.println(node.value);
            if (node.left != null) {
                stack.add(node.left);
            }
            if (node.right != null) {
                stack.add(node.right);
            }
        }
    }

    class Node<T> {
        Node left;
        Node right;
        T value;

    }
}
