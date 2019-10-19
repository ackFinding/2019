package chapter8;

public class ArrayBaseStack<T> {

    private T[] datas;

    private int size;

    private int capacity;

    private final static int DFAULT_CAPACITY = 10;

    public ArrayBaseStack() {
        this.capacity = DFAULT_CAPACITY;
        this.datas = (T[]) new Object[capacity];
        this.size = 0;
    }

    public ArrayBaseStack(int capacity) {
        this.capacity = capacity;
        this.datas = (T[]) new Object[capacity];
        this.size = 0;
    }

    public void push(T e) {
        if (size == capacity) {
            ensureCapacity();
        }
        datas[size++] = e;
    }

    private void ensureCapacity() {
        capacity = 2 * capacity;
        T[] newArr = (T[]) new Object[capacity];
        for (int i = 0; i < datas.length; i++) {
            newArr[i] = datas[i];
        }
        datas = newArr;
    }

    public T pop() {
        if (size == 0) {
            return null;
        }
        return datas[--size];
    }

    public T peek() {
        if (size == 0) {
            return null;
        }
        return datas[size - 1];
    }

    public static void main(String[] args) {
        ArrayBaseStack<Integer> stack = new ArrayBaseStack<>(3);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println(stack.capacity);
        stack.push(4);
        System.out.println(stack.capacity);
        System.out.println(stack.size);
        System.out.println(stack.peek()+",size:"+stack.size);
    }
}
