package chapter9;

public class LinkedListBaseQueue<T> {

    private Node<T> head;

    private Node<T> tail;

    private int size;


    public void enqueue(T t) {
        Node<T> newNode = new Node<>(t);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }


    public T dequeue() {
        if (head == null) {
            System.out.println("Queue is empty!");
            return null;
        }
        T t = head.val;
        head = head.next;
        return t;
    }

    public int size() {
        return this.size;
    }

    public void printAll() {
        Node p = head;
        while (p != null) {
            System.out.print(p.val + " ");
            p = p.next;
        }
        System.out.println();
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

    public static void main(String[] args) {
        LinkedListBaseQueue<Integer> queue = new LinkedListBaseQueue<>();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.printAll();
        queue.enqueue(4);
        System.out.println("dequeue:"+queue.dequeue());
        System.out.println("dequeue:"+queue.dequeue());
        System.out.println("dequeue:"+queue.dequeue());
        queue.printAll();
    }
}
