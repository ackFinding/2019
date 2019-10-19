package chapter24;

import java.util.*;

public class BinarySearchTree {

    //非递归查找
    public static Node find(Node tree, int data) {
        Node p = tree;
        while (p != null) {
            if (data < p.data) p = p.left;
            else if (data > p.data) p = p.right;
            else return p;
        }
        return null;
    }

    //递归查找
    public static Node find2(Node tree, int data) {
        if (tree == null || tree.data == data) {
            return tree;
        }
        if (data < tree.data) {
            return find2(tree.left, data);
        } else {
            return find2(tree.right, data);
        }
    }
    //===========================================================

    public static Node insert(Node tree, int data) {
        if (tree == null) {
            tree = new Node(data);
            return tree;
        }
        Node p = tree;
        while (p != null) {
            if (data < p.data) {
                if (p.left == null) {
                    p.left = new Node(data);
                    break;
                }
                p = p.left;
            } else {
                if (p.right == null) {
                    p.right = new Node(data);
                    break;
                }
                p = p.right;
            }
        }
        return tree;
    }

    //===========================================================
    public static Node delete(Node tree, int data) {
        if (tree == null) {
            return tree;
        }
        Node p = tree, pp = null;
        while (p != null && p.data != data) {
            pp = p;
            if (data < p.data) {
                p = p.left;
            } else {
                p = p.right;
            }
        }
        if (p.left != null && p.right != null) {
            Node minP = p.right, minPP = p;
            while (minP.left != null) {
                minPP = minP;
                minP = minP.left;
            }
            p.data = minP.data;
            pp = minPP;
            p = minP;
        }

        Node child = null;
        if (p.left != null) {
            child = p.left;
        } else if (p.right != null) {
            child = p.right;
        }

        if (pp == null) {
            tree = child;
        } else if (pp.left == p) {
            pp.left = child;
        } else {
            pp.right = child;
        }
        return tree;
    }
    //===========================================================

    public Node findMin(Node tree) {
        Node p = tree;
        while (p.left != null) {
            p = p.left;
        }
        return p;
    }

    public Node findMax(Node tree) {
        Node p = tree;
        while (p.right != null) {
            p = p.right;
        }
        return p;
    }

    //===========================================================

    public static List<Integer> inOrderTraversalRecursive(Node root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<Integer> left = inOrderTraversalRecursive(root.left);
        left.add(root.data);
        List<Integer> right = inOrderTraversalRecursive(root.right);
        left.addAll(right);
        return left;
    }

    public static List<Integer> inorderTraversal2(Node root) {
        List<Integer> res = new ArrayList<>();
        Stack<Node> s = new Stack<>();
        Node curr = root;
        while (curr != null || !s.isEmpty()) {
            while (curr != null) {
                s.push(curr);
                curr = curr.left;
            }
            curr = s.pop();
            res.add(curr.data);
            curr = curr.right;
        }
        return res;
    }

    //===========================================================
    public static class Node {
        private int data;
        private Node left;
        private Node right;

        public Node(int data) {
            this.data = data;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "data=" + data +
                    '}';
        }
    }


    public static void main(String[] args) {
        Node root = new Node(3);
        Node l1 = new Node(2);
        Node l2 = new Node(1);
        Node l3 = new Node(4);
        Node r1 = new Node(6);
        Node r2 = new Node(7);
        Node r3 = new Node(5);
        root.left = l1;
        l1.left = l2;
        l1.right = l3;
        root.right = r1;
        r1.left = r3;
        r1.right = r2;
        System.out.println(find(root, 3));
        System.out.println(find2(root, 3));
        System.out.println(inOrderTraversalRecursive(root));

    }
}
