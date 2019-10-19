package chapter29;

//求中位数
public class GetMedianByHeap {

    private int[] nums;//原始静态数据

    private int smallHeapCount = 0;//小根堆元素总数

    private int bigHeapCount = 0;//大根堆元素总数

    private int[] smallHeap;//小根堆

    private int[] bigHeap;//大根堆

    private int capacity;

    public GetMedianByHeap(int[] nums, int capacity) {
        this.nums = nums;
        int len = nums.length;
        //构建初始堆
        bigHeapCount = (len / 2) - 1 + 2;
        smallHeapCount = (len - 1) - (len / 2 + 1) + 2;
        if (capacity < bigHeapCount || capacity < smallHeapCount) {
            throw new IllegalArgumentException("capacity not enough");
        }
        this.capacity = capacity;
        bigHeap = new int[capacity];
        smallHeap = new int[capacity];//要留一个位置作占位
        buildBigHeap(1, len / 2);
        buildSmallHeap(len / 2 + 1, len - 1);
    }

    //ps:偶数则取上中位数
    public int getMedian() {
        return bigHeap[1];
    }

    private void buildSmallHeap(int start, int end) {
        int count = 1;
        for (int i = start; i <= end; i++) {
            smallHeap[count++] = nums[i];
        }
        for (int i = smallHeap.length / 2; i >= 1; --i) {
            heapifySmall(smallHeap, i, smallHeapCount - 1);
        }
    }

    private void heapifySmall(int[] smallHeap, int i, int n) {
        int left = 2 * i;
        int right = 2 * i + 1;
        while (true) {
            int min = i;
            min = left <= n && smallHeap[left] < smallHeap[i] ? left : i;
            min = right <= n && smallHeap[right] < smallHeap[min] ? right : min;
            if (min == i) {
                break;
            }
            swap(smallHeap, i, min);
            i = min;
            left = 2 * i;
            right = 2 * i + 1;
        }
    }


    private void buildBigHeap(int start, int end) {
        int count = 1;
        for (int i = start; i <= end; i++) {
            bigHeap[count++] = nums[i];
        }
        for (int i = bigHeap.length / 2; i >= 1; --i) {
            heapifyBig(bigHeap, i, bigHeapCount - 1);
        }
    }

    private void heapifyBig(int[] bigHeap, int i, int n) {
        int left = 2 * i;
        int right = 2 * i + 1;
        while (true) {
            int max = i;
            max = left <= n && bigHeap[left] > bigHeap[i] ? left : i;
            max = right <= n && bigHeap[right] > bigHeap[max] ? right : max;
            if (max == i) {
                break;
            }
            swap(bigHeap, i, max);
            i = max;
            left = 2 * i;
            right = 2 * i + 1;
        }
    }

    public void insert(int value) {
        //1.如果新加入的数据小于等于大顶堆的堆顶元素，将这个新数据插入到大顶堆（这样不会改变中位数位置）；否则，将这个新数据插入到小顶堆
        if (value <= bigHeap[1]) {
            if (bigHeapCount >= capacity) {
                //可进行扩容处理
                return;
            }
            bigHeap[bigHeapCount] = value;
            int i = bigHeapCount;
            while (i / 2 > 0 && bigHeap[i] > bigHeap[i / 2]) {
                swap(bigHeap, i / 2, i);
                i = i / 2;
            }
            bigHeapCount++;
        } else {
            if (smallHeapCount > capacity) {
                //可进行扩容处理
                return;
            }
            smallHeap[smallHeapCount] = value;
            int i = smallHeapCount;
            while (i / 2 > 0 && smallHeap[i] < smallHeap[i / 2]) {
                swap(smallHeap, i / 2, i);
                i = i / 2;
            }
            smallHeapCount++;
        }
        //2.重新调整使两个堆元素个数符合要求
        int n = bigHeapCount + smallHeapCount - 1;//因为多出两个占位
        //判断大根堆
        int expectBigCount = n / 2;
        if (bigHeapCount - 1 < expectBigCount) {
            int data = removeSmallHeapTop();
            bigHeap[bigHeapCount] = data;//将小根堆堆顶元素插入大根堆
            int i = bigHeapCount;
            while (i / 2 > 0 && bigHeap[i] > bigHeap[i / 2]) {
                swap(bigHeap, i / 2, i);
                i = i / 2;
            }
            bigHeapCount++;
        } else if (bigHeapCount - 1 > expectBigCount) {
            int data = removeBigHeapTop();
            smallHeap[smallHeapCount] = data;//将大根堆堆顶元素插入小根堆
            int i = smallHeapCount;
            while (i / 2 > 0 && smallHeap[i] < smallHeap[i / 2]) {
                swap(smallHeap, i / 2, i);
                i = i / 2;
            }
            smallHeapCount++;
        }
    }

    private int removeBigHeapTop() {
        int res = bigHeap[1];
        swap(bigHeap, 1, bigHeapCount - 1);
        bigHeapCount--;
        heapifyBig(bigHeap, 1, bigHeapCount - 1);
        return res;
    }

    private int removeSmallHeapTop() {
        int res = smallHeap[1];
        swap(smallHeap, 1, smallHeapCount - 1);
        smallHeapCount--;
        heapifySmall(smallHeap, 1, smallHeapCount - 1);
        return res;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        int[] nums = {-1, 1, 2, 3, 4, 5, 6, 7};
        int[] nums2 = {-1, 1, 2, 3, 4, 5, 6, 7, 8};
        GetMedianByHeap getMedianByHeap = new GetMedianByHeap(nums2, 20);
        getMedianByHeap.printHeap();
        System.out.println("中位数: " + getMedianByHeap.getMedian());

        System.out.println("------------------");
        getMedianByHeap.insert(0);
        getMedianByHeap.printHeap();
        System.out.println("中位数: " + getMedianByHeap.getMedian());

        System.out.println("------------------");
        getMedianByHeap.insert(5);
        getMedianByHeap.printHeap();
        System.out.println("中位数: " + getMedianByHeap.getMedian());
    }

    private void printHeap() {
        System.out.println("=====大根堆======");
        for (int i = 1; i < bigHeapCount; i++) {
            System.out.print(bigHeap[i] + " ");
        }
        System.out.println();
        System.out.println("=====小根堆======");
        for (int i = 1; i < smallHeapCount; i++) {
            System.out.print(smallHeap[i] + " ");
        }
        System.out.println();
    }

}
