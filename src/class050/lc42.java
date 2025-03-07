package class050;

public class lc42 {
    class Solution {
        public int trap(int[] height) {
            int l = 1, r = height.length - 2;
            int lmax = height[0], rmax = height[r + 1];
            int ans = 0;
            while (l <= r) {
                if (lmax <= rmax) {
                    ans += Math.max(0, lmax - height[l]);
                    lmax = Math.max(lmax, height[l++]);
                } else {
                    ans += Math.max(0, rmax - height[r]);
                    rmax = Math.max(rmax, height[r--]);
                }
            }
            return ans;
        }
    }
}
