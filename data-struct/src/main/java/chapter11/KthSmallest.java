package chapter11;

public class KthSmallest {


    public static int getKth(int[] nums, int k) {
        if (nums == null || k < 0 || k > nums.length) {
            return -1;
        }
        int len = nums.length;
        return quickSort(nums, 0, len - 1, k - 1);
    }


    private static int quickSort(int[] nums, int l, int r, int k) {
        if (l > r) {
            return -1;
        }
        int[] range = partition(nums, l, r);
        if (k == range[0]) {
            return nums[range[0]];
        } else if (k < range[0]) {
            return quickSort(nums, l, range[0] - 1, k);
        } else {
            return quickSort(nums, range[1] + 1, r, k);
        }
    }


    private static int[] partition(int[] nums, int l, int r) {
        int less = l - 1;
        int more = r;
        while (l < more) {
            if (nums[l] < nums[r]) {
                swap(nums, ++less, l++);
            } else if (nums[l] > nums[r]) {
                swap(nums, --more, l);
            } else {
                l++;
            }
        }
        swap(nums, more, r);
        return new int[]{less + 1, more};
    }

    public static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        int[] array = new int[]{3, 3, 3, 3, 4, 2, 1, 5, 6, 7, 8};//不支持重复数
        System.out.println(getKth(array, 7));
    }
}
