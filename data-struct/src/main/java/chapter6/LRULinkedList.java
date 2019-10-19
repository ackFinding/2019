package chapter6;

/**
 * 基于单链表LRU算法
 *
 * @param <T>
 */
public class LRULinkedList<T> {

    private Node head;//头结点

    private int size;//链表长度

    private final static int DEFAULT_CAPACITY = 16;

    private int capacity;//容量


    public LRULinkedList() {
        this.capacity = DEFAULT_CAPACITY;
        this.size = 0;
        this.head = new Node();
    }

    public LRULinkedList(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.head = new Node();
    }

    public void add(T element) {
        if (size == capacity) {
            removeTail();
        }
        Node node = new Node(element);
        node.next = head.next;
        head.next = node;
        size++;
    }

    public void removeTail() {
        if (head.next == null) {
            return;
        }
        Node prev = head;
        Node tmp = prev.next;
        while (tmp.next != null) {
            prev = tmp;
            tmp = tmp.next;
        }
        prev.next = null;
        size--;
    }

    //模拟访问某个值
    public void offer(T element) {
        Node tmp = head.next;
        Node prev = head;
        while (tmp != null && tmp.val != element) {
            prev = tmp;
            tmp = tmp.next;
        }
        //为空直接插在链表头
        if (tmp == null) {
            add(element);
        } else {//否则，删除结点，并插入到链表头
            prev.next = tmp.next;
            tmp.next = head.next;
            head.next = tmp;
        }

    }

    private class Node<T> {
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

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        Node tmp = head.next;
        while (tmp != null) {
            builder.append(tmp.val);
            if (tmp.next != null) {
                builder.append(",");
            }
            tmp = tmp.next;
        }
        builder.append("]");
        return builder.toString();
    }

    public static void main(String[] args) {
        LRULinkedList<Integer> linkedList = new LRULinkedList<>(2);
        linkedList.offer(5);
        linkedList.offer(3);
        linkedList.offer(1);
        System.out.println(linkedList);
        //================
        linkedList.offer(3);
        System.out.println(linkedList);
    }
}
