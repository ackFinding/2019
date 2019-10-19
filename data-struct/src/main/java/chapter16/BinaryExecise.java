package chapter16;

public class BinaryExecise {


    public static int findFistEq(int[] nums, int value) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int low = 0;
        int high = nums.length - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (nums[mid] > value) {
                high = mid - 1;
            } else if (nums[mid] < value) {
                low = mid + 1;
            } else {
                if (mid == 0 || nums[mid - 1] != value) {
                    return mid;
                }
                high = mid - 1;
            }
        }
        return -1;
    }


    public static int findLastEq(int[] nums, int value) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int low = 0;
        int len = nums.length;
        int high = len - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (nums[mid] > value) {
                high = mid - 1;
            } else if (nums[mid] < value) {
                low = mid + 1;
            } else {
                if (mid == len - 1 || nums[mid + 1] != value) {
                    return mid;
                }
                low = mid + 1;
            }
        }
        return -1;
    }

    public static int findFistBigOrEq(int[] nums, int value) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int low = 0;
        int high = nums.length - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (nums[mid] < value) {
                low = mid + 1;
            } else {
                if (mid == 0 || nums[mid - 1] < value) {
                    return mid;
                }
                high = mid - 1;
            }
        }
        return -1;
    }

    public static int findLastLessOrEq(int[] nums, int value) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int low = 0;
        int high = nums.length - 1;
        int len = nums.length;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (nums[mid] > value) {
                high = mid - 1;
            } else {
                if (mid == len - 1 || nums[mid + 1] > value) {
                    return mid;
                }
                low = mid + 1;
            }
        }
        return -1;
    }

    public static boolean binarySearch(int[] nums, int value) {
        if (nums == null || nums.length == 0) {
            return false;
        }
        int low = 0;
        int high = nums.length - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (nums[mid] == value) {
                return true;
            } else if (nums[mid] > value) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] array = new int[]{1, 5, 5, 6, 6, 6, 7, 8};
        System.out.println(findFistEq(array, 6));//3
        System.out.println(findFistBigOrEq(array, 5));//1
        System.out.println(findLastEq(array, 6));//5
        System.out.println(findLastLessOrEq(array, 6));//5
    }
}
