package chapter6;

import java.util.HashMap;
import java.util.Map;

/**
 * 基于数组实现的LRU缓存
 * <p>
 * 不支持null的缓存
 */
public class LRUBasedArray<T> {

    private final static int DEFAULT_CAPACITY = 16;

    private int capacity;//容量

    private T[] objects;

    private int size;

    private Map<T, Integer> holder = new HashMap<>();

    public LRUBasedArray() {
        this.capacity = DEFAULT_CAPACITY;
        this.size = 0;
        this.objects = (T[]) new Object[capacity];
    }

    public LRUBasedArray(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.objects = (T[]) new Object[capacity];
    }


    public void offer(T element) {
        if (element == null) {
            throw new IllegalArgumentException("该缓存容器不支持null!");
        }
        Integer index = holder.get(element);
        if (index == null) {
            if (isFull()) {
                removeAndCache(element);
            } else {
                cache(element, size);
            }
        } else {
            update(index);
        }
    }

    private void update(Integer index) {
        T obj = objects[index];
        shiftRight(index);
        objects[0] = obj;
        holder.put(obj, 0);
    }

    private void removeAndCache(T element) {
        T obj = objects[--size];
        holder.remove(obj);
        cache(element, size);
    }

    private void cache(T element, int right) {
        shiftRight(right);
        objects[0] = element;
        holder.put(element, 0);
        size++;
    }

    private void shiftRight(int right) {
        for (int i = right - 1; i >= 0; i--) {
            objects[i + 1] = objects[i];
            holder.put(objects[i], i + 1);
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append(objects[i]);
            sb.append(" ");
        }
        return sb.toString();
    }

    static class TestLRUBasedArray {

        public static void main(String[] args) {
            testDefaultConstructor();
            testSpecifiedConstructor(4);
//            testWithException();
        }

        private static void testWithException() {
            LRUBasedArray<Integer> lru = new LRUBasedArray<>();
            lru.offer(null);
        }

        public static void testDefaultConstructor() {
            System.out.println("======无参测试========");
            LRUBasedArray<Integer> lru = new LRUBasedArray<>();
            lru.offer(1);
            lru.offer(2);
            lru.offer(3);
            lru.offer(4);
            lru.offer(5);
            System.out.println(lru);
            lru.offer(6);
            lru.offer(7);
            lru.offer(8);
            lru.offer(9);
            System.out.println(lru);
        }

        public static void testSpecifiedConstructor(int capacity) {
            System.out.println("======有参测试========");
            LRUBasedArray<Integer> lru = new LRUBasedArray<>(capacity);
            lru.offer(1);
            System.out.println(lru);
            lru.offer(2);
            System.out.println(lru);
            lru.offer(3);
            System.out.println(lru);
            lru.offer(4);
            System.out.println(lru);
            lru.offer(2);
            System.out.println(lru);
            lru.offer(4);
            System.out.println(lru);
            lru.offer(7);
            System.out.println(lru);
            lru.offer(1);
            System.out.println(lru);
            lru.offer(2);
            System.out.println(lru);
        }
    }
}
