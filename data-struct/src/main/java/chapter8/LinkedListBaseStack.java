package chapter8;

/**
 * 头部插入，头部取出
 *
 * @param <T>
 */
public class LinkedListBaseStack<T> {

    private Node top = null;

    private int size = 0;

    public void push(T e) {
        Node node = new Node(e);
        if (top == null) {
            top = node;
        } else {
            node.next = top;
            top = node;
        }
        size++;
    }

    public T pop() {
        if (top == null) {
            return null;
        } else {
            size--;
            T val = (T)top.val;
            top = top.next;
            return val;
        }
    }

    public T peek() {
        if (top == null) {
            return null;
        } else {
            T val = (T)top.val;
            return val;
        }
    }

    public void clear() {
        this.size = 0;
        top = null;
    }

    public int size() {
        return this.size;
    }

    class Node<T> {
        T val;
        Node next;

        /**
         * for dummy node
         */
        public Node() {
            next = null;
        }

        public Node(T val) {
            this.val = val;
            this.next = null;
        }
    }

    public void printAll(Node<T> list) {
        Node<T> p = list;
        while (p != null) {
            System.out.print(p.val + " ");
            p = p.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        LinkedListBaseStack<Integer> stack = new LinkedListBaseStack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.printAll(stack.top);
        stack.pop();
        stack.printAll(stack.top);
        stack.peek();
        stack.printAll(stack.top);
    }
}
