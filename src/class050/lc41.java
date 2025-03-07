package class050;

public class lc41 {
    class Solution {
        public int firstMissingPositive(int[] nums) {
            int l = 0;
            for (int r = nums.length; l < r;) {
                if (nums[l] == l + 1) {
                    l++;
                } else if (nums[l] <= l || nums[l] > r || nums[l] == nums[nums[l] - 1]) {
                    int tmp = nums[l];
                    nums[l] = nums[--r];
                    nums[r] = tmp;
                } else {
                    int tmp = nums[l];
                    nums[l] = nums[tmp - 1];
                    nums[tmp - 1] = tmp;
                }
            }
            return l + 1;
        }
    }
}
