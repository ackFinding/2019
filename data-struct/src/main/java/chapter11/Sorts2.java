package chapter11;

import java.util.Arrays;

public class Sorts2 {


    public static void QuickSort(int[] nums) {
        if (nums == null || nums.length < 2) {
            return;
        }
        int len = nums.length;
        quickSort(nums, 0, len - 1);
    }

    private static void quickSort(int[] nums, int l, int r) {
        if (l >= r) {
            return;
        }
        int pivot = (int) (Math.random() * (r - l));
        swap(nums, pivot, r);
        int[] range = partition(nums, l, r);
        quickSort(nums, l, range[0] - 1);
        quickSort(nums, range[1] + 1, r);
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

    //========================================================
    public static void MergeSort(int[] nums) {
        if (nums == null || nums.length < 2) {
            return;
        }
        int len = nums.length;
        mergeSort(nums, 0, len - 1);
    }

    private static void mergeSort(int[] nums, int l, int r) {
        if (l >= r) {
            return;
        }
        int mid = l + ((r - l) >> 1);
        mergeSort(nums, l, mid);
        mergeSort(nums, mid + 1, r);
        merge(nums, l, mid, r);
    }

    private static void merge(int[] nums, int l, int mid, int r) {
        int[] helper = new int[r - l + 1];
        int p = l;
        int q = mid + 1;
        int c = 0;
        while (p <= mid && q <= r) {
            if (nums[p] <= nums[q]) {
                helper[c++] = nums[p++];
            } else {
                helper[c++] = nums[q++];
            }
        }
        while (p <= mid) {
            helper[c++] = nums[p++];
        }
        while (q <= r) {
            helper[c++] = nums[q++];
        }
        for (int i = 0; i < helper.length; i++) {
            nums[l + i] = helper[i];
        }
    }

    public static void main(String[] args) {
        int[] array = new int[]{3, 4, 2, 1, 5, 6, 7, 8};
        QuickSort(array);
        System.out.println(Arrays.toString(array));
    }
}
