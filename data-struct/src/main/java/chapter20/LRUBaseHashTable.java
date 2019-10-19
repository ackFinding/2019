package chapter20;

import java.util.HashMap;

public class LRUBaseHashTable<K, V> {


    private Node<V> head;

    private Node<V> tail;

    private HashMap<K, Node<V>> table;

    private static final int DEFAULT_CAPACITY = 10;

    private int capacity;

    private int size;


    public LRUBaseHashTable() {
        this(DEFAULT_CAPACITY);
    }

    public LRUBaseHashTable(int capacity) {
        this.capacity = capacity;
        this.table = new HashMap<>();
        this.head = new Node<>();
        this.tail = new Node<>();
        head.next = tail;
        tail.prev = head;
        this.size = 0;
    }


    public void add(K key, V value) {
        Node<V> node = table.get(key);
        if (node == null) {
            table.put(key, node);
            addNode(node);
        } else {
            node.value = value;
            removeNode(node);
            addNode(node);
        }
    }

    public Node popTail(){
        Node<V> del = tail.prev;
        tail = tail.prev;
        removeNode(del);
        return del;
    }

    private void addNode(Node<V> node) {
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
        node.prev = head;
        size++;
    }

    private void removeNode(Node<V> node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
        node.prev = null;
        size--;
    }

    class Node<V> {
        public V value;
        public Node<V> prev;
        public Node<V> next;

        public Node() {
        }

        public Node(V value) {
            this.value = value;
        }
    }
}
