package class050;

public class lc11 {
    class Solution {
        public int maxArea(int[] height) {
            int ans = 0;
            for (int l = 0, r = height.length - 1; l < r;) {
                ans = Math.max(ans, (r - l) * Math.min(height[l], height[r]));
                if (height[l] < height[r]) {
                    l++;
                } else {
                    r--;
                }
            }
            return ans;
        }
    }

    class Solution1 { // 最优解
        public int maxArea(int[] height) {
            int ans = 0;
            for (int l = 0, r = height.length - 1, min; l < r;) {
                min = Math.min(height[l], height[r]);
                ans = Math.max(ans, min * (r - l));
                while (l < r && height[l] <= min) { // 小的那一边可以一直跳 不然一定比之前这一步取的ans小
                    l++;
                }
                while (l < r && height[r] <= min) { // 同理。如果两边一样大，那两边都能跳
                    r--;
                }
            }
            return ans;
        }
    }
}
