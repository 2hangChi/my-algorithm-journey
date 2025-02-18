package class046;

public class lc303 {
    class NumArray {

        public NumArray(int[] nums) {
            pre = new int[nums.length + 1];
            for (int i = 0; i < nums.length; i++) {
                pre[i + 1] = pre[i] + nums[i];
            }
        }

        public int sumRange(int left, int right) {
            return pre[right + 1] - pre[left];
        }

        public static int[] pre;
    }
}
