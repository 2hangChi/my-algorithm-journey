package class046;

public class lc303 {
    class NumArray {

        public NumArray(int[] nums) {
            pre = new int[nums.length + 1];
            for (int i = 0; i < nums.length; i++) { // 或者 i = 1; i <= ， 之前这样写把<=写成<，注意i的范围
                pre[i + 1] = pre[i] + nums[i];
            }
        }

        public int sumRange(int left, int right) {
            return pre[right + 1] - pre[left];
        }

        public static int[] pre;
    }
}
