package class049;

import java.util.Arrays;

public class lc3 {
    class Solution {
        public int lengthOfLongestSubstring(String s) {
            int[] last = new int[256];
            int len = s.length();
            Arrays.fill(last, Integer.MIN_VALUE);
            int ans = 0;
            for (int l = 0, r = 0; r < len; r++) {
                char cha = s.charAt(r);
                l = Math.max(l, last[cha] + 1); // 一开始last[cha]忘了+1
                ans = Math.max(ans, r - l + 1);
                last[cha] = r;
            }
            return ans;
        }
    }

    class Solution1 {
        public int lengthOfLongestSubstring(String s) {
            char[] ss = s.toCharArray(); // 变成数组会更快吗？
            int n = ss.length;
            int[] last = new int[256];
            Arrays.fill(last, -1);
            int ans = 0;
            for (int l = 0, r = 0; r < n; r++) {
                l = Math.max(l, last[ss[r]] + 1);
                ans = Math.max(ans, r - l + 1);
                last[ss[r]] = r;
            }
            return ans;
        }
    }
}
