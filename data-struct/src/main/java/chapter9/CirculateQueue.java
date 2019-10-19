package chapter9;

public class CirculateQueue<T> {

    private final static int DEFAULT_CAPACITY = 10;

    private T[] datas;

    private int capacity;

    private int head;

    private int tail;

    public CirculateQueue(int capacity) {
        this.capacity = capacity;
        this.head = 0;
        this.tail = 0;
        datas = (T[]) new Object[capacity];
    }

    public CirculateQueue() {
        this.capacity = DEFAULT_CAPACITY;
        this.head = 0;
        this.tail = 0;
        datas = (T[]) new Object[capacity];
    }

    public boolean enqueue(T e) {
        if ((tail + 1) % capacity == head) {
            System.out.println("Queue has full!");
            return false;
        }
        datas[tail] = e;
        tail = (tail + 1) % capacity;
        return true;
    }

    public T dequeue() {
        if (head == tail) {
            System.out.println("Queue is empty!");
            return null;
        }
        T t = datas[head];
        head = (head + 1) % capacity;
        return t;
    }


    public void printAll() {
        if (0 == capacity) return;
        for (int i = head; i % capacity != tail; i = (i + 1) % capacity) {
            System.out.print(datas[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        CirculateQueue<Integer> queue = new CirculateQueue<>(4);
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.printAll();
        System.out.println("dequeue:" + queue.dequeue());
        queue.enqueue(4);
        queue.printAll();
        System.out.println("dequeue:" + queue.dequeue());
        queue.enqueue(222);
        queue.printAll();
    }
}
