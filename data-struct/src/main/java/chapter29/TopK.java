package chapter29;


public class TopK {

    private int[] nums;//原始静态数据

    private int[] smallHeap;//小根堆

    public TopK(int[] origin) {
        this.nums = origin;
    }

    //数组从1开始存储数据，0位置废弃
    public int[] getTopK(int k) {
        if (nums == null || nums.length < 2) {
            return null;
        }
        this.smallHeap = new int[k + 1];
        buildHeap(k);
        return smallHeap;
    }

    //构建大小为k的小根堆
    private void buildHeap(int k) {
        //初始化
        for (int i = 1; i <= k; i++) {
            smallHeap[i] = nums[i];
        }
        //构建初始小根堆
        for (int i = smallHeap.length / 2; i >= 1; i--) {
            heapify(smallHeap, i, smallHeap.length - 1);
        }
        //继续插入其他元素完成小根堆构建
        for (int i = k; i < nums.length; i++) {
            if (nums[i] > getTop()) {//如果数据大于小根堆的最小值,插入
                insert(nums[i]);
            }
        }
    }


    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private void heapify(int[] nums, int i, int n) {
        int left = 2 * i;
        int right = 2 * i + 1;
        while (true) {
            int min = i;
            min = left <= n && nums[left] < nums[i] ? left : i;
            min = right <= n && nums[right] < nums[min] ? right : min;
            if (min == i) {
                break;
            }
            swap(smallHeap, i, min);
            i = min;
            left = 2 * i;
            right = 2 * i + 1;
        }
    }


    public void insert(int value) {
        smallHeap[1] = value;//删除堆顶元素，插入新元素
        heapify(smallHeap, 1, smallHeap.length - 1);
    }

    /**
     * 返回堆顶元素
     *
     * @return
     */
    public int getTop() {
        return smallHeap[1];
    }

    public static void main(String[] args) {
        int[] nums = {-1, 3, 4, 1, 2, 5, 10, 11, 99, 88, 22, 20, -11};
        TopK topK = new TopK(nums);
        int[] res = topK.getTopK(5);
        for (int i = 1; i < res.length; i++) {
            System.out.println(res[i]);
        }
    }

}
