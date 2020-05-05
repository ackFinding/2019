package chapter38;

/**
 * 求逆序对
 */
public class ReversePair {


    private int count = 0;

    public int countReversePair(int[] nums) {
        if (nums == null || nums.length < 2) return 0;
        mergeSort(nums, 0, nums.length - 1);
        return count;
    }

    private void mergeSort(int[] nums, int l, int r) {
        if (l >= r) return;
        int mid = l + ((r - l) >> 1);
        mergeSort(nums, l, mid);
        mergeSort(nums, mid + 1, r);
        merge(nums, l, mid, r);
    }

    public void merge(int[] nums, int l, int mid, int r) {
        int[] helper = new int[r - l + 1];
        int k = 0;
        int left = l;
        int right = mid + 1;
        while (left <= mid && right <= r) {
            if (nums[left] <= nums[right]) {
                helper[k++] = nums[left++];
            } else {
                count += (mid - left + 1);
                System.out.println(count);
                helper[k++] = nums[right++];
            }
        }
        while (left <= mid) {
            helper[k++] = nums[left++];
        }
        while (right <= r) {
            helper[k++] = nums[right++];
        }
        for (int i = 0; i < helper.length; i++) {
            nums[l + i] = helper[i];
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 5, 6, 2, 3, 4};
        ReversePair reversePair = new ReversePair();
        System.out.println("逆序对：" + reversePair.countReversePair(nums));
    }
}
