package class049;

public class lc209 {
    class Solution {
        public int minSubArrayLen(int target, int[] nums) {
            int l = 0, r = 0;
            int ans = Integer.MAX_VALUE;
            for (int sum = 0; l < nums.length; l++) {
                while (r < nums.length && sum < target) {
                    sum += nums[r++];
                }
                if (sum >= target) {
                    ans = Math.min(ans, r - l); // 想清楚定义的窗口的左右的开闭 一开始错写成r-1+1
                    // 我定义的是[l, r) 长度是r-l
                }
                sum -= nums[l];
            }
            return ans == Integer.MAX_VALUE ? 0 : ans;
        }
    }
    /*
    * 关键应该在于关注写法里 l++和r++能否连续发生 能的话则说明窗口移动是正常的
    * */

    class Solution2 { // 老师这样写貌似思路更顺（for循环不一样，一个l++，一个r++）
        public int minSubArrayLen(int target, int[] nums) {
            int ans = Integer.MAX_VALUE;
            for (int l = 0, r = 0, sum = 0; r < nums.length; r++) {
                sum += nums[r];
                while (l < nums.length && sum - nums[l] >= target) { // l < nums.length && 可以省略
                                            // 因为target是正整数 一定不会把sum减到0 所以r一定在右边卡着l 不用判断l越界
                    sum -= nums[l++];
                }
                if(sum >= target) {
                    ans = Math.min(ans, r - l + 1); // 这种写法窗口定义[l, r]
                }
            }
            return ans == Integer.MAX_VALUE ? 0 : ans;
        }
    }
}
