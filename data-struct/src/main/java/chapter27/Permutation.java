package chapter27;

/**
 * 全排列
 */
public class Permutation {


    /**
     *
     *
     * @param data
     * @param n
     * @param k  要处理的子数组的数据个数
     */
    public static void printPermutations(int[] data, int n, int k) {
        if (k == 1) {
            for (int i = 0; i < n; i++) {
                System.out.print(data[i]);
            }
            System.out.println();
        }
        for (int i = 0; i < k; i++) {
            swap(data, k-1, i);
            printPermutations(data, n, k - 1);
            swap(data, k-1, i);
        }
    }

    public static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        int[] a = {1, 2, 3, 4};
        printPermutations(a, a.length, 4);
    }
}
