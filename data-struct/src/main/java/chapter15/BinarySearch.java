package chapter15;

/**
 * 二分查找
 */
public class BinarySearch {

    //非递归二分查找
    public static int search(int[] nums, int target) {
        if (nums == null) {
            return -1;
        }
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = left + ((right - left) >> 1);
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] > target) {
                right = mid - 1;
            } else if (nums[mid] < target) {
                left = mid + 1;
            }
        }
        return -1;
    }

    //递归二分查找
    public static int recursiveSearch(int[] nums, int target) {
        if (nums == null) {
            return -1;
        }
        return recursiveSearch(nums, 0, nums.length - 1, target);
    }

    private static int recursiveSearch(int[] nums, int left, int right, int target) {
        if (left > right) {
            return -1;
        }
        int mid = left + ((right - left) >> 1);
        if (nums[mid] == target) {
            return mid;
        } else if (nums[mid] > target) {
            return recursiveSearch(nums,left,mid-1,target);
        } else  {
            return recursiveSearch(nums,mid+1,right,target);
        }
    }


    public static void main(String[] args) {
        int[] nums = {1,3,4,5,6,7};
        System.out.println("normal search:"+search(nums,3));
        System.out.println("normal search:"+search(nums,0));
        System.out.println("recursive search:"+recursiveSearch(nums,3));
        System.out.println("recursive search:"+recursiveSearch(nums,0));
    }
}
