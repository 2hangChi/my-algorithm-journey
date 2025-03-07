package class050;

public class lc287 {
    class Solution {
        public int findDuplicate(int[] nums) { // 如果确定1~n都会出现且只有一个数字出现2次 则用异或运算很简单
            int flow = 0, fast = 0;
            flow = nums[flow];
            fast = nums[nums[fast]];
            while (flow != fast) {
                flow = nums[flow];
                fast = nums[nums[fast]];
            }
            fast = 0;
            while (flow != fast) {
                flow = nums[flow];
                fast = nums[fast];
            }
            return fast; // 一开始写成 return nums[fast];
        }
    }
}
