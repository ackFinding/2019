package chapter9;

public class ArrayQueue<T> {

    private final static int DEFAULT_CAPACITY = 10;

    private T[] datas;

    private int capacity;

    private int head;

    private int tail;

    public ArrayQueue(int capacity) {
        this.capacity = capacity;
        this.head = 0;
        this.tail = 0;
        datas = (T[]) new Object[capacity];
    }

    public ArrayQueue() {
        this.capacity = DEFAULT_CAPACITY;
        this.head = 0;
        this.tail = 0;
        datas = (T[]) new Object[capacity];
    }

    public boolean enqueue(T e) {
        if (tail == capacity) {
            if (head == 0) {
                System.out.println("Queue has full!");
                return false;
            }
            compact();
        }
        datas[tail++] = e;
        return true;
    }

    private void compact() {
        for (int i = head; i < tail; i++) {
            datas[i - head] = datas[i];
        }
        head = 0;
        tail = tail - head;
    }

    public T dequeue() {
        if (head == tail) {
            System.out.println("Queue is empty...");
            return null;
        }
        T t = datas[head++];
        return t;
    }

    public int size() {
        return tail - head;
    }

    public void printAll() {
        for (int i = head; i < tail; ++i) {
            System.out.print(datas[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        ArrayQueue<Integer> queue = new ArrayQueue<>(3);
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.printAll();
        queue.enqueue(4);
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        System.out.println(queue.dequeue());
        queue.printAll();
    }
}
