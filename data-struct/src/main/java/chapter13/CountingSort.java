package chapter13;

import java.util.Arrays;

public class CountingSort {


    public static void bucketSort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            max = arr[i] > max ? arr[i] : max;
        }
        int[] buckets = new int[max + 1];
        for (int i = 0; i < arr.length; i++) {
            buckets[arr[i]]++;
        }
        int j = 0;
        for (int i = 0; i < buckets.length; i++) {
            while (buckets[i]-- > 0) {
                arr[j++] = i;
            }
        }
    }

    public static void countingSort(int[] arr) {
        long curr = System.currentTimeMillis();
        System.out.println(curr);
        if (arr.length <= 1) {
            return;
        }
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            max = arr[i] > max ? arr[i] : max;
        }
        int[] buckets = new int[max + 1];
        for (int i = 0; i < arr.length; i++) {
            buckets[arr[i]]++;
        }
        for (int i = 1; i < buckets.length; i++) {
            buckets[i] += buckets[i - 1];
        }

        int[] help = new int[arr.length];
        //从尾部开始，保证是稳定排序
        for (int i = arr.length - 1; i >= 0; i--) {
            int index = buckets[arr[i]] - 1;
            help[index] = arr[i];
            buckets[arr[i]]--;
        }
        for (int i = 0; i < help.length; i++) {
            arr[i] = help[i];
        }
        System.out.println("method 1_total spent time:" + (System.currentTimeMillis() - curr));
    }


    public static int[] generateRandomArray(int maxSize, int maxValue) {
        int[] arr = new int[(int) ((maxSize + 1) * Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int) ((maxValue + 1) * Math.random());
        }
        return arr;
    }

    // for test
    public static void comparator(int[] arr) {
        Arrays.sort(arr);
    }

    // for test
    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    // for test
    public static int[] copyArray(int[] arr) {
        if (arr == null) {
            return null;
        }
        int[] res = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            res[i] = arr[i];
        }
        return res;
    }

    // for test
    public static boolean isEqual(int[] arr1, int[] arr2) {
        if ((arr1 == null && arr2 != null) || (arr1 != null && arr2 == null)) {
            return false;
        }
        if (arr1 == null && arr2 == null) {
            return true;
        }
        if (arr1.length != arr2.length) {
            return false;
        }
        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] != arr2[i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int testTime = 500000;
        int maxSize = 100;
        int maxValue = 100;
        boolean succeed = true;
        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(maxSize, maxValue);
            int[] arr2 = copyArray(arr1);
            bucketSort2(arr1);
            comparator(arr2);
            if (!isEqual(arr1, arr2)) {
                succeed = false;
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");

        int[] arr = generateRandomArray(maxSize, maxValue);
        printArray(arr);
        bucketSort2(arr);
        printArray(arr);
    }
}
