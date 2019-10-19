package chapter5;

public class Array<T> {

    private Object[] element;

    private int size;

    private static final int DEFAULT_CAPACITY = 10;

    public Array() {
        this(DEFAULT_CAPACITY);
    }

    public Array(int capacity) {
        element = new Object[capacity];
        size = 0;
    }

    public T get(int index) {
        checkBound(index);
        return (T) element[index];
    }

    public boolean contains(T e) {
        return find(e) != -1;
    }

    public void add(T e) {
        add(size, e);
    }

    public void add(int index, T e) {
        checkBound(index);
        if (size == element.length) {
            resize(element.length * 2);
        }
        for (int i = size - 1; i >= index; i--) {
            element[i + 1] = element[i];
        }
        element[index] = e;
        size++;
    }

    public void set(int index, T e) {
        checkBound(index);
        element[index] = e;
    }

    public void remove(T e) {
        int index = find(e);
        if (index != -1) {
            remove(index);
        }
    }

    public T remove(int index) {
        checkRemoveBound(index);
        T removed = (T) element[index];
        for (int i = index + 1; i < size; i++) {
            element[i - 1] = element[i];
        }
        size--;
        element[size] = null;
        return removed;
    }


    public int find(T e) {
        for (int i = 0; i < size; i++) {
            if (e.equals(element[i])) {
                return i;
            }
        }
        return -1;
    }

    private void resize(int capacity) {
        Object[] newArr = new Object[capacity];
        for (int i = 0; i < size; i++) {
            newArr[i] = element[i];
        }
        element = newArr;
    }


    private void checkBound(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("index:" + index + " ,size:" + size);
        }
    }


    private void checkRemoveBound(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("index:" + index + " ,size:" + size);
        }
    }

    public int getCapacity() {
        return element.length;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append(String.format("Array size = %d,Capacity size = %d\n", size, element.length))
                .append("[");
        for (int i = 0; i < size; i++) {
            builder.append(element[i]);
            if (i != size - 1) {
                builder.append(",");
            }
        }
        builder.append("]");
        return builder.toString();
    }
}
