package class050;

public class lc922 {
    class Solution {
        public int[] sortArrayByParityII(int[] nums) {
            int n = nums.length;
            for (int fix = n - 1, odd = 1, even = 0; odd < n && even < n;) {
                if (nums[fix] % 2 == 0) { // 用 (nums[fix] & 1) == 1/0 判断奇偶
                    swap(nums, fix, even);
                    even += 2;
                } else {
                    swap(nums, fix, odd);
                    odd += 2;
                }
            }
            return nums;
        }

        public static void swap(int[] arr, int l, int r) {
            int tmp = arr[l];
            arr[l] = arr[r];
            arr[r] = tmp;
        }
    }
}
