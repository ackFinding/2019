package chapter42;

/**
 * 求出这个序列中的最长递增子序列长度:
 * 比如 2, 9, 3, 6, 5, 1, 7 这样一组数字序列，它的最长递增子序列就是 2, 3, 5, 7，所以最长递增子序列的长度是 4。
 */
public class LongestIncrSubString {


    /**
     * 递推公式:
     * a[0~i] 的最长子序列 =  a[0~i-1] 中所有比它小的元素中子序列长度最大的 + 1
     *
     * @param nums
     * @return
     */
    public static int lis(int[] nums) {
        if (nums.length < 2) return nums.length;
        int[] state = new int[nums.length];
        state[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            int max = Integer.MIN_VALUE;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i] && state[j] > max) {
                    max = state[j];
                }
            }
            state[i] = max + 1;
        }
        System.out.println(state[state.length - 1]);
        return state[state.length - 1];
    }


    public static void main(String[] args) {
        int[] num1 = {2, 9, 3, 6, 5, 1, 7};
        int[] num2 = {2, 9, 3, 6, 5, 1, 11, 3, 23, 7, 37, 7};

        lis(num1);

    }
}
