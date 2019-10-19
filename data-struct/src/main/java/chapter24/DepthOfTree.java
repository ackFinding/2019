package chapter24;

public class DepthOfTree {

    public int maxDepth(Node node) {
        if (node == null) return 0;
        int leftDepth = 0;
        Node n = node;
        while (n.left != null) {
            leftDepth++;
            n = n.left;
        }
        n = node;
        int rightDepth = 0;
        while (n.right != null) {
            rightDepth++;
            n = n.right;
        }
        return Math.max(leftDepth, rightDepth);
    }

    public int maxDepth2(Node node) {
        if (node == null) return 0;
        return Math.max(maxDepth2(node.left), maxDepth2(node.right)) + 1;
    }


    public static class Node {
        private int data;
        private Node left;
        private Node right;

        public Node(int data) {
            this.data = data;
        }
    }
}
