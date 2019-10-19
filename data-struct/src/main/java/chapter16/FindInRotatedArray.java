package chapter16;

public class FindInRotatedArray {

    public static void main(String[] args) {
        int[] array = new int[]{1, 2};

        System.out.println(findInRotatedArray(array, 2));//5

    }

    private static int findInRotatedArray(int[] nums, int target) {
        if (nums == null || nums.length < 1) {
            return -1;
        }
        int low = 0;
        int high = nums.length - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (nums[mid] == target) {//等于直接返回
                return mid;
            }
            if (nums[low] <= nums[mid]) {//low~mid有序
                if (nums[low] <= target && target < nums[mid]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            } else {//mid~high有序
                if (target > nums[mid] && target <= nums[high]) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
        }
        return -1;
    }
}
