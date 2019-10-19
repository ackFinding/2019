package chapter28;

/**
 * 大根堆
 */
public class Heap {

    private int[] datas;//数组，从下标 1 开始存储数据

    private int capacity;//堆可以存储的最大数据个数

    private int count;//堆中已经存储的数据个数

    public Heap(int n) {
        this.datas = new int[n];
        this.capacity = n;
        this.count = 0;
    }

    public void insert(int val) {
        if (count >= capacity) {
            return;
        }
        datas[++count] = val;
        int i = count;
        while (i / 2 > 0 && datas[i] > datas[i / 2]) {
            swap(datas, i, i / 2);
            i = i / 2;
        }
    }

    /**
     * 删除堆顶元素(最大)
     */
    public int removeMax() {
        if (count == 0) {
            return -1;
        }
        int del = datas[1];
        datas[1] = datas[count];
        --count;
        heapify(datas, 1, count);
        return del;
    }

    /**
     * 自上往下堆化
     */
    private void heapify(int[] datas, int i, int count) {
        int left = 2 * i;
        while (left < count) {
            int maxChildIndex = left + 1 < count && datas[left + 1] > datas[left] ? left + 1 : left;
            int largestIndex = datas[maxChildIndex] > datas[i] ? maxChildIndex : i;
            if (largestIndex == i) {
                break;
            }
            swap(datas, i, largestIndex);
            i = largestIndex;
            left = 2 * i;
        }
    }


    private void swap(int[] datas, int a, int b) {
        int temp = datas[a];
        datas[a] = datas[b];
        datas[b] = temp;
    }

}
