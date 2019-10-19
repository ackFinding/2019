package chapter12;

import java.util.Arrays;

public class KthSmallest {

    public static int getKthSmallest(int[] arr, int k) {
        if (arr == null || arr.length < 2) {
            return -1;
        }
        return quickSort(arr, 0, arr.length - 1, k);
    }

    private static int quickSort(int[] arr, int l, int r, int k) {
        if (l == r) return arr[l];
        swap(arr, l + (int) (Math.random() * (r - l) + 1), r);
        int[] p = partition(arr, l, r);
        if (p[0] <= k && k <= p[1]) {
            return arr[p[0]];
        } else if (k > p[1]) {
            return quickSort(arr, p[1] + 1, r, k);
        }else {
            return quickSort(arr, l, p[0] - 1, k);
        }
    }

    private static int[] partition(int[] arr, int l, int r) {
        int less = l - 1;
        int more = r;
        while (l < more) {
            if (arr[l] < arr[r]) {
                swap(arr, ++less, l++);
            } else if (arr[l] > arr[r]) {
                swap(arr, --more, l);
            } else {
                l++;
            }
        }
        swap(arr, more, r);
        return new int[]{less + 1, more};
    }

    public static void swap(int[] arr1, int i, int j) {
        int temp = arr1[i];
        arr1[i] = arr1[j];
        arr1[j] = temp;
    }

    public static void main(String[] args) {
        int[] arr = { 6, 9, 1, 3, 1, 2, 2, 5, 6, 1, 3, 5, 9, 7, 2, 5, 6, 1, 9 };
//        int[] arr = { 6, 9, 1, 3, 1, 2, 2, 5, 6, 1, 3, 5, 9, 7, 2, 5, 6, 1, 9,2,3,4,6,8,9,0,11,23,45,76,34,45,22,11,23,43,68,98,27,43,24,27,78,89,20,10,2,6,8,9,2,5,71};
        // sorted : { 1, 1, 1, 1, 2, 2, 2, 3, 3, 5, 5, 5, 6, 6, 6, 7, 9, 9, 9 }
        System.out.println(getKthSmallest(arr, 3));
        System.out.println(getKthSmallest(arr, 5));
        Arrays.sort(arr);
        printArray(arr);

    }

    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

}
