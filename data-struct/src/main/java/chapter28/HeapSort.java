package chapter28;

public class HeapSort {

    //从1开始存储数据，0位置废弃
    public static void heapSort(int[] nums) {
        if (nums == null || nums.length < 2) {
            return;
        }
        //建堆
        for (int i = nums.length / 2; i >= 1; i--) {
            heapify(nums, i, nums.length - 1);
        }
        int count = nums.length - 1;
        for (int i = 1; i < nums.length; i++) {
            //排序
            swap(nums, 1, count);
            heapify(nums, 1, --count);
        }
    }


    private static void heapify(int[] nums, int k, int n) {
        int left = k * 2;
        int right = k * 2 + 1;
        while (true) {
            int max = k;
            max = left <= n && nums[k] < nums[left] ? left : k;
            max = right <= n && nums[right] > nums[max] ? right : max;
            if (max == k) {
                break;
            }
            swap(nums, k, max);
            k = max;
            left = 2 * k;
            right = 2 * k + 1;
        }

    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }


    public static void main(String[] args) {
        int[] nums = {-1, 3, 4, 1, 2, -11};
        heapSort(nums);
        for (int i = 1; i < nums.length; i++) {
            System.out.println(nums[i]);
        }
    }
}
